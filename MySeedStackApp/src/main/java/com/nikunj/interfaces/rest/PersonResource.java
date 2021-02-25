package com.nikunj.interfaces.rest;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.nikunj.domain.model.aggregate.Person;
import com.nikunj.domain.services.PersonService;

import io.swagger.annotations.Api;

@Path("person")
@Api
public class PersonResource {

	@Inject
	public PersonService service;
	
	@GET
	@Path("/{emailId}")
	@Produces(MediaType.APPLICATION_JSON)
	public  Person getPerson(@PathParam("emailId")String emailId){
		
		return service.getPerson(emailId);
	}
	
	@GET
	@Path("/all")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Person> getPersonList(){
		
		return service.getPersonList();
	}
}
