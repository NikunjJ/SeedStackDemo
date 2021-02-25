package com.nikunj.domain.services;

import java.util.List;

import com.nikunj.domain.model.aggregate.Person;

public interface IPersonService {

	public Person getPerson(String emailId);
	
	public List<Person> getPersonList();
}
