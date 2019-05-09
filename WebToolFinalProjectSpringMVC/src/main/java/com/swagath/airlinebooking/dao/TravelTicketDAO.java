package com.swagath.airlinebooking.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.swagath.airlinebooking.exception.AdException;
import com.swagath.airlinebooking.pojo.FlightInformation;
import com.swagath.airlinebooking.pojo.TravelTicket;
import com.swagath.airlinebooking.pojo.Traveller;

public class TravelTicketDAO extends DAO {

	public TravelTicket bookTravelTicket(Traveller passengerDetails, FlightInformation flightDetails)
			throws AdException {

		try {
			begin();
			TravelTicket ticket = new TravelTicket(passengerDetails, flightDetails);
			getSession().save(ticket);
			commit();
			return ticket;

		} catch (HibernateException e) {
			rollback();
			throw new AdException("Exception while creating ticket: " + e.getMessage());
		} finally {
			close();
		}

	}

	public void cancelTravelTicket(Traveller passengerDetails, FlightInformation flightDetails) throws AdException {
		try {
			begin();
			long passenger_id = passengerDetails.getId();
			long flight_id = flightDetails.getFlight_id();
			System.out.println("checking here passenger_id" + passenger_id);
			System.out.println("checking here flight_id" + flight_id);
			Query q = getSession()
					.createQuery("From TravelTicket where passenger_id=:passenger_id and flight_id=:flight_id");
			q.setLong("passenger_id", passenger_id);
			q.setLong("flight_id", flight_id);
			TravelTicket ticket = (TravelTicket) q.uniqueResult();
			System.out.println("here the ticket Object" + ticket);
			getSession().delete(ticket);

			/*
			 * int oldAvail = flightDetails.getAvailableSeats();
			 * flightDetails.setAvailableSeats(oldAvail + 1);
			 * getSession().update(flightDetails);
			 */

			commit();
		} catch (HibernateException e) {
			rollback();
			throw new AdException("Exception while deleting ticket: " + e.getMessage());
		} finally {
			close();
		}
	}

	public void deleteTravelTickets(long flight_id) throws AdException {
		try {
			begin();
			Query q = getSession().createQuery("From TravelTicket where flight_id=:flight_id");
			q.setLong("flight_id", flight_id);
			List<TravelTicket> list = q.list();
			commit();
			// Iterate to delete all ticket objects

			for (TravelTicket t : list) {
				begin();
				getSession().delete(t);
				commit();
			}

		} catch (HibernateException e) {
			rollback();
			throw new AdException("Exception while deleting ticket: " + e.getMessage());
		} finally {
			close();
		}
	}

	public List TravelTicketList() {
		List<TravelTicket> ticketList = new ArrayList<TravelTicket>();
		try {
			begin();
			Query q = getSession().createQuery("From TravelTicket");
			ticketList = q.list();
			commit();

		} catch (HibernateException e) {
			rollback();
			System.out.println("Exception while listing ticket: " + e.getMessage());
		} finally {
			close();
		}
		return ticketList;

	}

}
