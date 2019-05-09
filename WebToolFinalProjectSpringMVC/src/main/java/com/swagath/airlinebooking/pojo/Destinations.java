package com.swagath.airlinebooking.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "destinations")
public class Destinations {

	@Id
	@Column(name = "destinationname")
	private String destinationname;

	public Destinations() {

	}

	public String getDestinationname() {
		return destinationname;
	}

	public void setDestinationname(String destinationname) {
		this.destinationname = destinationname;
	}

}
