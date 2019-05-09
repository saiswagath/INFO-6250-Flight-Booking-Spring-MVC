package com.swagath.airlinebooking.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.swagath.airlinebooking.dao.FlightInformationDao;
import com.swagath.airlinebooking.exception.AdException;
import com.swagath.airlinebooking.pojo.FlightInformation;

@Controller
@RequestMapping(value = "/*Cart.htm")
public class BookTicketController {

	@RequestMapping(value = "/addToCart.htm", method = RequestMethod.GET)
	public String intialize(HttpServletRequest request, HttpServletResponse response, FlightInformationDao fdao)
			throws AdException, IOException, JSONException {

		try {
			HttpSession session = request.getSession();
			System.out.println("flight id" + request.getParameter("fid"));
			Long flightid = Long.parseLong(request.getParameter("fid"));
			System.out.println("Flight ID is" + flightid);
			List<FlightInformation> cart;

			FlightInformation fd = fdao.searchFlightByID(flightid);

			System.out.println("Cart Controller:" + fd.getAvailableSeats());

			int noOfSeats = fd.getAvailableSeats();
			PrintWriter out = response.getWriter();
			if (noOfSeats > 0) {

				out.println("Seats are available");
				if (session.getAttribute("cart") != null) {
					cart = (ArrayList<FlightInformation>) session.getAttribute("cart");
				} else {
					cart = new ArrayList<FlightInformation>();
				}

				cart.add(fd);
				session.setAttribute("cart", cart);
				session.setAttribute("flightdetail", fd);

				float total = 0;
				for (FlightInformation f : cart) {
					total = total + f.getAmount();
				}

				session.setAttribute("total", total);
				fdao.updateSeats(fd, noOfSeats, 1);

				return "viewCart";

			}

			else {

				return "notAvailable";

			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}

	}

	@RequestMapping(value = "/removeFromCart.htm", method = RequestMethod.GET)
	public String removeItems(HttpServletRequest request) throws AdException {

		HttpSession session = request.getSession();
		try {

			List<FlightInformation> cart = (ArrayList<FlightInformation>) session.getAttribute("cart");
			String id = request.getParameter("id");
			long flight_id = Long.parseLong(id);

			for (FlightInformation fd : cart) {
				if (fd.getFlight_id() == flight_id) {
					cart.remove(fd);
					break;

				}
			}

			session.setAttribute("cart", cart);

			float total = 0;
			for (FlightInformation f : cart) {
				total = total + f.getAmount();
			}

			session.setAttribute("total", total);
		}

		catch (Exception e) {
			System.out.println("Could not remove flight object" + e);
		}

		return "viewCart";
	}

	@RequestMapping(value = "/viewCart.htm", method = RequestMethod.GET)
	public String viewCart(HttpServletRequest request) {
		return "viewCart";

	}

}
