package com.swagath.airlinebooking.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "paymentprocessor")
public class PaymentProcessor {

	@Id
	@Column(name = "creditCardNumber")
	long creditCardNumber;

	@Column(name = "bankName")
	String bankName;

	@Column(name = "fullName")
	String fullName;

	@Column(name = "exp_month")
	String expiration_month;

	@Column(name = "exp_year")
	String expiration_year;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "creditCardNumber")
	Traveller traveller;

	public PaymentProcessor() {

	}

	public PaymentProcessor(long creditCardNumber, String bankName, String fullName, String expiration_month,
			String expiration_year) {

		this.creditCardNumber = creditCardNumber;
		this.bankName = bankName;
		this.fullName = fullName;
		this.expiration_month = expiration_month;
		this.expiration_year = expiration_year;
	}

	public String getExpiration_month() {
		return expiration_month;
	}

	public void setExpiration_month(String expiration_month) {
		this.expiration_month = expiration_month;
	}

	public String getExpiration_year() {
		return expiration_year;
	}

	public void setExpiration_year(String expiration_year) {
		this.expiration_year = expiration_year;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public long getCreditCardNumber() {
		return creditCardNumber;
	}

	public void setCreditCardNumber(long creditCardNumber) {
		this.creditCardNumber = creditCardNumber;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public Traveller getTraveller() {
		return traveller;
	}

	public void setTraveller(Traveller traveller) {
		this.traveller = traveller;
	}

}
