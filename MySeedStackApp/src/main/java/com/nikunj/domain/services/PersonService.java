package com.nikunj.domain.services;

import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.ws.rs.NotFoundException;

import org.seedstack.business.Service;
import org.seedstack.jpa.Jpa;
import org.seedstack.jpa.JpaUnit;
import org.seedstack.seed.Bind;
import org.seedstack.seed.transaction.Transactional;

import com.nikunj.domain.model.PersonId;
import com.nikunj.domain.model.aggregate.Person;

@Service
@Bind
public class PersonService implements IPersonService
{
	
	//Approach: There is LifecycleListener interface which will allow us to preload sample data
//	private final static List<Person> personList = new ArrayList<Person>();
//	
//	static
//	{
//		//Load few person infor
//		Person p1= new Person(new PersonId("abc@gmail.com", "123456"), "ABC", "XYZ");
//		Person p2= new Person(new PersonId("pqr@gmail.com", "789109"), "PQR", "UVW");
//		
//		personList.add(p1);
//		personList.add(p2);		
//	}
	
	//This is direct repository provided by SeedStack framework
	//private Repository<Person, PersonId> personRepository; 

	@Inject
//	@InMemory
	@Jpa
	private PersonRepository personRepository;
		
	@JpaUnit("myUnit")
	@Transactional
	public Person getPerson(String emailId) {

		System.out.println("service:"+emailId);
		
//		List<Person> collect = personRepository.stream().filter(p -> p.getId().getEmail().equals(emailId)).collect(Collectors.toList());
//		return collect;
		
		return 	personRepository.get(new PersonId(emailId)).orElseThrow(NotFoundException::new);
	}


	@JpaUnit("myUnit")
	@Transactional
	public List<Person> getPersonList() {

		return personRepository.getAllPerson().collect(Collectors.toList());
	}

}

