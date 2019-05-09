package com.swagath.airlinebooking.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.swagath.airlinebooking.dao.FlightInformationDao;
import com.swagath.airlinebooking.pojo.Destinations;
import com.swagath.airlinebooking.pojo.FlightInformation;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping(value = "/")
public class HomeController {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(@ModelAttribute("flightInformation") FlightInformation flightInformation) {

		flightInformation.setTravelClass("Economy");
		return "index";
	}

	@RequestMapping(value = "/index.htm", method = RequestMethod.GET)
	public String homePage(@ModelAttribute("flightInformation") FlightInformation flightInformation) {

		flightInformation.setTravelClass("Economy");
		return "index";
	}

	@RequestMapping(value = "/adminHome.htm", method = RequestMethod.GET)
	public String adminHomePage() {

		return "adminHome";
	}

	@RequestMapping(value = "/fromCitieslist.htm", method = RequestMethod.POST)
	public void ajaxForCities(HttpServletRequest request, HttpServletResponse response, FlightInformationDao fdao)
			throws IOException {
		try {

			String fromCities = request.getParameter("fromCities");
			PrintWriter out = response.getWriter();

			List list = fdao.listCities(fromCities);

			List<Destinations> destinations = (ArrayList<Destinations>) list;

			JSONArray jsonArray = new JSONArray();
			for (int i = 0; i < destinations.size(); i++) {
				JSONObject obj = new JSONObject();
				obj.put("cityname", destinations.get(i).getDestinationname());
				jsonArray.put(obj);
			}

			JSONObject Obj = new JSONObject();
			Obj.put("list", jsonArray);
			out.println(Obj);

		} catch (Exception e) {
			System.out.println("Exception in listing destinations" + e.getMessage());
		}
	}

}
