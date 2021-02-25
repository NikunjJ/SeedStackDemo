package com.nikunj.infrastructure;

import javax.inject.Inject;

import org.seedstack.business.domain.Repository;
import org.seedstack.jpa.Jpa;
import org.seedstack.jpa.JpaUnit;
import org.seedstack.seed.LifecycleListener;
import org.seedstack.seed.transaction.Transactional;

import com.nikunj.domain.model.PersonId;
import com.nikunj.domain.model.aggregate.Person;

public class PersonDataGenerator implements LifecycleListener{

	@Inject
//	@InMemory //Commented as JPA is included
	@Jpa
	private Repository<Person, PersonId> personRepository;
	
	
	@Override
	@JpaUnit("myUnit")
	@Transactional
	public void started() {
	
		System.out.println("In memory loaded.....");
		
		Person p1= new Person(new PersonId("abc@gmail.com"), "ABC", "XYZ","11111");
		Person p2= new Person(new PersonId("pqr@gmail.com"), "PQR", "UVW","22222");
		
		personRepository.addOrUpdate(p1);
		personRepository.addOrUpdate(p2);
		
	}
	
}
