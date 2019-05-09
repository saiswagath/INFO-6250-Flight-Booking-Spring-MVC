package com.swagath.airlinebooking.controller;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.swagath.airlinebooking.pojo.Airline;

public class AirlineValidator implements Validator {

	@Override
	public boolean supports(Class aClass) {
		return aClass.equals(Airline.class);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		// TODO Auto-generated method stub
		Airline airplane = (Airline) obj;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "airlineName", "error.invalid.airlineName",
				"Airplane Name Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "owner", "error.invalid.owner", "Owner Required");

	}
}
