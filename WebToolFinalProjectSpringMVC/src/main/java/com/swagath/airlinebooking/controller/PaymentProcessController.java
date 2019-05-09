package com.swagath.airlinebooking.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.swagath.airlinebooking.dao.FlightInformationDao;
import com.swagath.airlinebooking.dao.TravelTicketDAO;
import com.swagath.airlinebooking.dao.TravellerDAO;
import com.swagath.airlinebooking.exception.AdException;
import com.swagath.airlinebooking.pojo.FlightInformation;
import com.swagath.airlinebooking.pojo.PaymentProcessor;
import com.swagath.airlinebooking.pojo.Traveller;

@Controller
@RequestMapping(value = "/payment*.htm")
public class PaymentProcessController {

	@Autowired
	@Qualifier("paymentValidator")
	PaymentProcessValidator validator;

	@InitBinder
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(validator);
	}

	@RequestMapping(value = "/payment.htm", method = RequestMethod.GET)
	public String initialize(@ModelAttribute("payment") PaymentProcessor payment) {

		return "payment";
	}

	@RequestMapping(value = "/payment.htm", method = RequestMethod.POST)
	public String addPayment(HttpServletRequest request, HttpServletResponse response) throws AdException {

		HttpSession session = request.getSession();
		long passenger_id = ((Long) session.getAttribute("passenger_id"));

		try {
			TravellerDAO pdao = new TravellerDAO();
			TravelTicketDAO tdao = new TravelTicketDAO();
			FlightInformationDao fdao = new FlightInformationDao();
			String number = request.getParameter("creditCardNumber");
			number = number.replaceAll("[^0-9]", "");
			long creditCardNumber = Long.parseLong(number);

			String bankName = request.getParameter("bankName");
			bankName = bankName.replaceAll("[^\\dA-Za-z]", "");
			String fullName = request.getParameter("fullName");
			fullName = fullName.replaceAll("[^A-Za-z]+$", "");
			String expiration_month = request.getParameter("expiration_month");
			expiration_month = expiration_month.replaceAll("[^0-9]", "");
			Cookie exp_month = new Cookie("month", expiration_month);
			String expiration_year = request.getParameter("expiration_year");
			expiration_year = expiration_year.replaceAll("[^0-9]", "");
			Cookie exp_year = new Cookie("year", expiration_year);
			PaymentProcessor paymnt = pdao.createPaymentProcessor(creditCardNumber, bankName, fullName,
					expiration_month, expiration_year);

			pdao.updateTraveller(passenger_id, paymnt);

			Traveller passenger = pdao.searchTraveller(passenger_id);
			FlightInformation flightDetail = (FlightInformation) session.getAttribute("flightdetail");

			tdao.bookTravelTicket(passenger, flightDetail);
			int availSeats = flightDetail.getAvailableSeats();
			fdao.updateAvailableSeats(flightDetail, availSeats, availSeats - 1);
			response.addCookie(exp_month);
			response.addCookie(exp_year);

		} catch (Exception e) {
			System.out.println("Could not add payment/ticket" + e.getMessage());
		}

		return "printTicket";
	}

}
