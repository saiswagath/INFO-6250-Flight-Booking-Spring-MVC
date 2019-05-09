package com.swagath.airlinebooking.pojo;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "travelticket")
public class TravelTicket {

	@Id
	@GeneratedValue
	@Column(name = "ticket_id", unique = true, nullable = false)
	long ticket_id;

	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "passenger_id")
	Traveller traveller;

	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "flight_id")
	FlightInformation flightInformation;

	public TravelTicket() {

	}

	public TravelTicket(Traveller traveller, FlightInformation flightInformation) {
		this.traveller = traveller;
		this.flightInformation = flightInformation;
	}

	public long getTicket_id() {
		return ticket_id;
	}

	public void setTicket_id(long ticket_id) {
		this.ticket_id = ticket_id;
	}

	public Traveller getTraveller() {
		return traveller;
	}

	public void setTraveller(Traveller traveller) {
		this.traveller = traveller;
	}

	public FlightInformation getFlightInformation() {
		return flightInformation;
	}

	public void setFlightInformation(FlightInformation flightInformation) {
		this.flightInformation = flightInformation;
	}

}
