package com.swagath.airlinebooking.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.swagath.airlinebooking.dao.DisplayFlightsDAO;
import com.swagath.airlinebooking.dao.FlightInformationDao;
import com.swagath.airlinebooking.dao.TravelTicketDAO;
import com.swagath.airlinebooking.exception.AdException;
import com.swagath.airlinebooking.pojo.FlightInformation;

@Controller
@RequestMapping(value = "/*light*.htm")
public class FlightBookingController {

	@Autowired
	@Qualifier("flightValidator")
	FlightBookingValidator validator;

	@InitBinder
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(validator);

	}

	@RequestMapping(value = "/addflights.htm", method = RequestMethod.POST)
	protected String doSubmitAction(@ModelAttribute("fd") FlightInformation fd, BindingResult result) throws Exception {

		validator.validate(fd, result);
		if (result.hasErrors()) {
			return "addFlights";
		}

		try {
			FlightInformationDao fdao = new FlightInformationDao();
			long airplane_id = fd.getAirplane_id();
			String from = fd.getFrom();
			from = from.replaceAll("[^A-Za-z]+$", "");
			String dest = fd.getDest();
			dest = dest.replaceAll("[^A-Za-z]+$", dest);
			String deptTime = fd.getDeptTime();
			deptTime = deptTime.replaceAll("[^\\d:]", "");
			String arrivalTime = fd.getArrivalTime();
			arrivalTime = arrivalTime.replaceAll("[^\\d:]", "");
			String travelClass = fd.getTravelClass();
			travelClass = travelClass.replaceAll("[^A-Za-z]+$", "");
			int totalSeats = fd.getTotalSeats();
			int amount = fd.getAmount();
			String deptDate = fd.getDeptDate();
			String arrDate = fd.getArrDate();
			String flight_name = fd.getFlight_name();

			FlightInformation flight = fdao.createFlight(airplane_id, from, dest, deptTime, arrivalTime, travelClass,
					totalSeats, amount, deptDate, arrDate, flight_name, totalSeats);

		} catch (AdException e) {
			System.out.println("Exception: " + e.getMessage());
		}

		return "addedFlight";
	}

	@RequestMapping(value = "/addflights.htm", method = RequestMethod.GET)
	public String initializeForm(@ModelAttribute("fd") FlightInformation fd) {
		return "addFlights";
	}

	@RequestMapping(value = "/ListFlights.htm", method = RequestMethod.GET)
	public String listForm(HttpServletRequest request) {
		HttpSession session = request.getSession();
		List<String> listOfFlights = null;

		try {
			DisplayFlightsDAO ldao = new DisplayFlightsDAO();
			listOfFlights = ldao.listAllFlights();
		} catch (AdException e) {
			System.out.println("Exception: " + e.getMessage());
		}

		session.setAttribute("listOfFlights", listOfFlights);
		return "ListFlights";
	}

	@RequestMapping(value = "/updateFlights.htm", method = RequestMethod.GET)
	public String updateFlights(@ModelAttribute("fd") FlightInformation fd, HttpServletRequest request)
			throws AdException {
		String id = request.getParameter("id");
		long flight_id = Long.parseLong(id);

		String action = request.getParameter("action");

		HttpSession session = request.getSession();

		if (action != null) {
			if (action.equalsIgnoreCase("update")) {
				FlightInformationDao fdao = new FlightInformationDao();
				FlightInformation flight = fdao.searchFlightByID(flight_id);
				request.setAttribute("flight", flight);
				return "updateFlight";

			}

		}

		return "ListFlights";

	}

	@RequestMapping(value = "/deleteFlights.htm", method = RequestMethod.GET)
	public String deleteFlights(@ModelAttribute("fd") FlightInformation fd, HttpServletRequest request,
			FlightInformationDao fdao, TravelTicketDAO tdao) throws AdException {
		try {
			String id = request.getParameter("id");

			long flight_id = Long.parseLong(id);

			HttpSession session = request.getSession();

			FlightInformation flight = fdao.searchFlightByID(flight_id);

			tdao.deleteTravelTickets(flight_id);

			fdao.deleteFlight(flight);

		} catch (AdException e) {
			System.out.println("Exception: " + e.getMessage());
		}

		return "ListFlights";
	}

	@RequestMapping(value = "/updateFlight.htm", method = RequestMethod.POST)
	public String update(@ModelAttribute("fd") FlightInformation fd, BindingResult result, HttpServletRequest request) {
		HttpSession session = request.getSession();

		try {
			FlightInformationDao fdao = new FlightInformationDao();
			fdao.updateFlight(fd);
			System.out.println("Now Seats available are" + fd.getAvailableSeats());
			System.out.println("Flight saved/updated successfully");

		} catch (AdException e) {
			System.out.println("Exception: " + e.getMessage());
		}

		List<String> listOfFlights = null;

		try {
			DisplayFlightsDAO ldao = new DisplayFlightsDAO();
			listOfFlights = ldao.listAllFlights();
		} catch (AdException e) {
			System.out.println("Exception: " + e.getMessage());
		}

		session.setAttribute("listOfFlights", listOfFlights);

		return "ListFlights";
	}

}
