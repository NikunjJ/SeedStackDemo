package com.nikunj.domain.model;

import javax.persistence.Embeddable;

import org.seedstack.business.domain.BaseValueObject;

@Embeddable
public class PersonId extends BaseValueObject{

	private static final long serialVersionUID = 1L;

	private String email;

	//Required No-args for Hibernate
	private PersonId() 
	{}

	public PersonId(String email) {
		super();
		this.email = email;
	}

	public String getEmail() {
		return email;
	}
	
}
