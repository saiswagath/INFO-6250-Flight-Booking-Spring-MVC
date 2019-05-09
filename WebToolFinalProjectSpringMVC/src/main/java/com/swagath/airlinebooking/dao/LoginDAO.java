package com.swagath.airlinebooking.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;

import com.swagath.airlinebooking.exception.AdException;
import com.swagath.airlinebooking.pojo.User;

public class LoginDAO extends DAO {

	public boolean validateAdmin(String username, String password) throws AdException {
		try {

			SQLQuery q = getSession().createSQLQuery(
					"select * from airlineuser where username=:username and password=:password and role='admin'");
			q.setString("username", username);
			q.setString("password", password);
			Object obj = q.uniqueResult();
			if (obj == null) {
				return false;
			}

		} catch (HibernateException e) {
			rollback();
			throw new AdException("Error while finidng User", e);
		} finally {
			close();
		}

		return true;

	}

	public boolean validateUser(String username, String password) throws AdException {
		try {

			SQLQuery q = getSession().createSQLQuery(
					"select * from airlineuser where username=:username and password=:password and role='customer'");
			q.setString("username", username);
			q.setString("password", password);
			Object obj = q.uniqueResult();
			if (obj == null) {
				return false;
			}

		} catch (HibernateException e) {
			rollback();
			throw new AdException("Error while finidng User", e);
		} finally {
			close();
		}

		return true;

	}

	public void addUser(String username, String password, String role) throws AdException {

		try {
			begin();
			User u = new User(username, password, role);
			getSession().save(u);
			commit();
		} catch (HibernateException e) {
			rollback();
			throw new AdException("Error while adding user", e);
		} finally {
			close();
		}

	}

	public boolean userExists(String username) {
		try {
			begin();
			Query q = getSession().createQuery("From User where username=:username");
			q.setString("username", username);
			List list = q.list();
			commit();

			if (list.size() == 0) {
				return false;
			}

		} catch (Exception e) {

			System.out.println(e.getMessage());
			return false;
		} finally {
			close();
		}
		return true;
	}

}
