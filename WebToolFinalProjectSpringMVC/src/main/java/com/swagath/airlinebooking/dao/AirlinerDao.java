package com.swagath.airlinebooking.dao;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.swagath.airlinebooking.exception.AdException;
import com.swagath.airlinebooking.pojo.Airline;

public class AirlinerDao extends DAO {

	public Airline create(String airlineName, String owner) throws AdException {
		try {
			begin();
			Airline airplane = new Airline(airlineName, owner);
			getSession().save(airplane);
			commit();
			return airplane;
		} catch (HibernateException e) {
			rollback();
			throw new AdException("Airline couldn't be added", e);
		} finally {
			close();
		}

	}

	public void updateAirplane(Airline airplane) throws AdException {
		try {
			begin();
			getSession().update(airplane);
			commit();
		} catch (HibernateException e) {
			rollback();
			throw new AdException("Airline couldn't be updated", e);
		} finally {
			close();
		}
	}

	public Airline searchAirplaneByID(long airplane_id) throws AdException {
		try {

			begin();
			Query q = getSession().createQuery("from Airline where airplane_id = :airplane_id");
			q.setLong("airplane_id", airplane_id);
			Airline airplane = (Airline) q.uniqueResult();
			commit();
			return airplane;
		} catch (HibernateException e) {
			rollback();
			throw new AdException(
					"Airplane with following Id couldn'te be found: " + airplane_id + " " + e.getMessage());
		} finally {
			close();
		}
	}

	public int deleteAirplane(long airplane_id) throws AdException {

		try {
			Airline airplane = searchAirplaneByID(airplane_id);

			if (airplane == null) {
				return 0;
			}
			begin();
			getSession().delete(airplane);
			commit();
			return 1;

		} catch (HibernateException e) {
			rollback();
			throw new AdException("Airplane with following Id couldn'te be deleted", e);

		} finally {
			close();
		}

	}

}
