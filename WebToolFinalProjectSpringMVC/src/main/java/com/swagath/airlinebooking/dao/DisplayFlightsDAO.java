package com.swagath.airlinebooking.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.swagath.airlinebooking.exception.AdException;

public class DisplayFlightsDAO extends DAO {

	public List listFlights(String fromPlace, String dest, String deptDate) throws AdException {

		List<String> list = null;

		try {
			begin();
			Query q = getSession().createQuery("from FlightInformation where fromPlace = :fromPlace and dest=:dest");
			q.setString("fromPlace", fromPlace);
			q.setString("dest", dest);

			list = q.list();
			commit();
			return list;
		}

		catch (HibernateException e) {
			rollback();
			throw new AdException("Flight couln't be found", e);
		} finally {
			close();
		}

	}

	public List listAllFlights() throws AdException {

		List<String> list = null;

		try {
			begin();
			Query q = getSession().createQuery("from FlightInformation");
			list = q.list();
			commit();
			return list;
		}

		catch (HibernateException e) {
			rollback();
			throw new AdException("Error while fecthing all flights", e);
		} finally {
			close();
		}

	}

}
