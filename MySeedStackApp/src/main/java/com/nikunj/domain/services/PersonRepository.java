package com.nikunj.domain.services;

import java.util.stream.Stream;

import org.seedstack.business.domain.Repository;

import com.nikunj.domain.model.PersonId;
import com.nikunj.domain.model.aggregate.Person;

public interface PersonRepository extends Repository<Person, PersonId>{

	default Stream<Person> getAllPerson()
	{
		return get(getSpecificationBuilder().of(Person.class).all().build());
	}
}
