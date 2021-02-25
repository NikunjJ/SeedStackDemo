package com.nikunj.domain.model.aggregate;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import org.seedstack.business.domain.BaseAggregateRoot;

import com.nikunj.domain.model.PersonId;

//This is Aggregator Root class which will take data from different VO/Entity classes
@Entity
public class Person extends BaseAggregateRoot<PersonId>{

	@EmbeddedId
	private PersonId personId;
	
	private String firstName;
	
	private String lastName;

	private String mobNo;
	
	//Required No-args for Hibernate
	private Person() 
	{}
	
	public Person(PersonId personId, String firstName, String lastName, String mobileNo) {
		super();
		this.personId = personId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.mobNo = mobileNo;
	}

	//This is BaseEntity class method to act as PK/Unique Key
	@Override
	public PersonId getId() {
		return this.personId;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getMobNo() {
		return mobNo;
	}
	
}
