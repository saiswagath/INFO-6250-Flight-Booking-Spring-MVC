package com.swagath.airlinebooking.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.swagath.airlinebooking.exception.AdException;
import com.swagath.airlinebooking.pojo.PaymentProcessor;
import com.swagath.airlinebooking.pojo.Traveller;

public class TravellerDAO extends DAO {

	public Traveller createTraveller(String firstName, String lastName, String gender, String email, String dob,
			String phonenum, String address) throws AdException {
		try {
			begin();
			Traveller passenger = new Traveller(firstName, lastName, gender, email, dob, phonenum, address);
			getSession().save(passenger);
			commit();
			return passenger;
		}

		catch (HibernateException e) {
			rollback();
			throw new AdException("Exception while creating new passenger: " + e.getMessage());
		} finally {
			close();
		}

	}

	public PaymentProcessor createPaymentProcessor(long creditCardNumber, String bankName, String fullName,
			String expiration_month, String expiration_year) throws AdException {
		try {
			begin();
			PaymentProcessor p = new PaymentProcessor(creditCardNumber, bankName, fullName, expiration_month,
					expiration_year);
			getSession().save(p);
			commit();
			return p;
		}

		catch (HibernateException e) {
			rollback();
			throw new AdException("Exception while creating new payment: " + e.getMessage());
		} finally {
			close();
		}

	}

	public void updateTraveller(long passenger_id, PaymentProcessor payment) throws AdException {

		try {
			begin();
			Query query = getSession().createQuery("From Traveller where passenger_id=:passenger_id ");
			query.setLong("passenger_id", passenger_id);
			Traveller passenger = (Traveller) query.uniqueResult();
			passenger.setPaymentProcessor(payment);
			getSession().update(passenger);
			commit();

		} catch (HibernateException e) {
			rollback();
			throw new AdException("Exception while updating passenger: " + e.getMessage());
		} finally {
			close();
		}
	}

	public Traveller searchTraveller(long passenger_id) throws AdException {
		Traveller passenger;
		try {
			begin();
			Query query = getSession().createQuery("From Traveller where passenger_id=:passenger_id ");
			query.setLong("passenger_id", passenger_id);
			passenger = (Traveller) query.uniqueResult();

			commit();
		} catch (HibernateException e) {
			rollback();
			// throw new AdException("Could not create flight", e);
			throw new AdException("Exception while searching passenger: " + e.getMessage());
		} finally {
			close();
		}
		return passenger;
	}

	public List ListTravellers() throws AdException {
		try {
			begin();
			Query q = getSession().createQuery("From Traveller");
			List list = q.list();
			commit();
			return list;

		} catch (HibernateException e) {
			rollback();
			throw new AdException("Exception while listing passenger: " + e.getMessage());
		} finally {
			close();
		}

	}

}
