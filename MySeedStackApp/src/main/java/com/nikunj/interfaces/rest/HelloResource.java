package com.nikunj.interfaces.rest;

import java.util.Arrays;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import org.seedstack.seed.Application;
import org.seedstack.seed.Configuration;
import org.seedstack.seed.Logging;
import org.slf4j.Logger;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.nikunj.application.MyConfig;

import io.swagger.annotations.Api;

//Hashcode is same on each request which proves single instance is used
@Singleton 
@Path("hello")
@Api
public class HelloResource 
{
	@Configuration
	private MyConfig config;
	
	//Logging
	@Logging
	private Logger logger;

	//Application context object
	//@Inject
	//private Application application; 
	
	//Constructor Injection
	private final Application application;
	
	@Inject
	public HelloResource(Application app) {
	
		this.application = app;
	}
	
	@GET
	public String hello() 
	{
		logger.info("This is my SeedStack REST Method");
		
		StringBuilder builder = new StringBuilder("Hellow World!");
		builder.append(Arrays.asList(config.getNames()));
		builder.append("Application >");
		builder.append(application.getName());
		
		//Default scope is Prototype - Each request will have separate instances to serve
		builder.append("Hascode:").append(hashCode());
		
		
		return builder.toString();
	}
	
	@Override
	public int hashCode() {
	
		return super.hashCode();
	}
}