package com.swagath.airlinebooking.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.swagath.airlinebooking.dao.DisplayFlightsDAO;
import com.swagath.airlinebooking.exception.AdException;
import com.swagath.airlinebooking.pojo.FlightInformation;

@Controller
@RequestMapping(value = "/listflights.htm")
public class ListFlightsController {

	@RequestMapping(value = "/listflights.htm", method = RequestMethod.POST)
	public String initializeForm(@ModelAttribute("flightDetails") FlightInformation flightDetails,
			HttpServletRequest request, DisplayFlightsDAO ldao) throws AdException {
		HttpSession session = request.getSession();
		String from = request.getParameter("from");
		from = from.replaceAll("[^A-Za-z]+$", "");
		String dest = request.getParameter("dest");
		dest = dest.replaceAll("[^A-Za-z]+$", "");
		String deptDate = request.getParameter("deptDate");
		System.out.println("From place" + from + "Dest" + dest + "Dept date" + deptDate);
		try {
			List<String> flightlist = ldao.listFlights(from, dest, deptDate);
			int length = flightlist.size();
			session.setAttribute("flightlist", flightlist);

		} catch (AdException e) {
			System.out.println("Exception: " + e.getMessage());
		}

		return "flightList";
	}
}
