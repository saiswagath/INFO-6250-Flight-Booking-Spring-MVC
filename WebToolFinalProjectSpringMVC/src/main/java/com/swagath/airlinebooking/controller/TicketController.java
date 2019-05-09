package com.swagath.airlinebooking.controller;

import java.io.ByteArrayOutputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lowagie.text.Document;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import com.swagath.airlinebooking.dao.TravelTicketDAO;
import com.swagath.airlinebooking.dao.TravellerDAO;
import com.swagath.airlinebooking.exception.AdException;
import com.swagath.airlinebooking.pojo.FlightInformation;
import com.swagath.airlinebooking.pojo.Traveller;

@Controller
@RequestMapping(value = "/*Ticket.*")
public class TicketController {

	@RequestMapping(value = "/downloadTicket.pdf", method = RequestMethod.GET)
	public void downloadTicket(HttpServletRequest request, HttpServletResponse response, TravellerDAO pdao)
			throws AdException {
		HttpSession session = request.getSession();
		long passenger_id = ((Long) session.getAttribute("passenger_id"));
		Traveller passenger = pdao.searchTraveller(passenger_id);
		FlightInformation flightDetail = (FlightInformation) session.getAttribute("flightdetail");

		try {

			response.setContentType("application/pdf");

			Document document = new Document();
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			PdfWriter.getInstance(document, baos);
			document.open();
			Paragraph title = new Paragraph("Below is Your Ticket Confirmation");
			Paragraph name = new Paragraph("Passenger name:" + passenger.getFirstName() + "" + passenger.getLastName());
			Paragraph flight = new Paragraph("Flight Name" + flightDetail.getFlight_name() + " From "
					+ flightDetail.getFrom() + " Destination " + flightDetail.getDest());
			Paragraph deptDetails = new Paragraph(
					"Departure Date" + flightDetail.getDeptDate() + "Departure Time :" + flightDetail.getDeptTime());
			Paragraph arrDetails = new Paragraph("Destination Arrival Date" + flightDetail.getArrDate()
					+ "Destination Arrival Time" + flightDetail.getArrivalTime());

			document.add(title);
			document.add(name);
			document.add(flight);
			document.add(deptDetails);
			document.add(arrDetails);

			document.close();

			ServletOutputStream out = response.getOutputStream();
			baos.writeTo(out);
			out.flush();

		}

		catch (Exception e) {
			System.out.println("Could not add ticket object" + e.getMessage());
		}

	}

	@RequestMapping(value = "/emailTicket.htm", method = RequestMethod.GET)
	public String emailTicket(HttpServletRequest request, TravellerDAO pdao) throws AdException {
		try {

			HttpSession session = request.getSession();
			long passenger_id = ((Long) session.getAttribute("passenger_id"));
			Traveller passenger = pdao.searchTraveller(passenger_id);
			FlightInformation flightDetail = (FlightInformation) session.getAttribute("flightdetail");

			Email email = new SimpleEmail();
			email.setHostName("smtp.googlemail.com");
			email.setSmtpPort(465);
			email.setAuthenticator(new DefaultAuthenticator("kasaiswagath95@gmail.com", "9866098204"));
			email.setSSLOnConnect(true);
			try {
				email.setFrom("kasaiswagath95@gmail.com");
				email.setSubject("Ticket Confirmation");
				email.setMsg("Hello,Passenger:" + passenger.getFirstName() + " " + passenger.getLastName() + "\n"
						+ "Thank you for booking Ticket with us. Please find your flight details below " + "\n"
						+ "Flight Name" + flightDetail.getFlight_name() + " From " + flightDetail.getFrom()
						+ " Destination " + flightDetail.getDest() + "\n" + "Departure Date"
						+ flightDetail.getDeptDate() + "Departure Time :" + flightDetail.getDeptTime() + "\n"
						+ "Destination Arrival Date" + flightDetail.getArrDate() + "Destination Arrival Time"
						+ flightDetail.getArrivalTime());
				email.addTo(passenger.getEmail());
				email.send();
			} catch (EmailException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (Exception e) {
			System.out.println("Could not send email " + e.getMessage());
		}

		return "confirmation";
	}

	@RequestMapping(value = "/deleteTicket.htm", method = RequestMethod.GET)
	public String deleteTicket(HttpServletRequest request, TravellerDAO pdao, TravelTicketDAO tdao) throws AdException {
		try {
			HttpSession session = request.getSession();
			long passenger_id = ((Long) session.getAttribute("passenger_id"));
			Traveller passenger = pdao.searchTraveller(passenger_id);
			FlightInformation flightDetail = (FlightInformation) session.getAttribute("flightdetail");
			tdao.cancelTravelTicket(passenger, flightDetail);
		} catch (Exception e) {
			System.out.println("Could not cancel Ticket " + e.getMessage());
		}

		return "confirmation";
	}
}
