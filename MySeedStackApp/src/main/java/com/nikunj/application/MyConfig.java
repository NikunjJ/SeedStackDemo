package com.nikunj.application;

import java.util.List;

import org.seedstack.coffig.Config;

@Config("myConfig")
public class MyConfig {

	private List<String> names;

	public List<String> getNames() {

		return names;
	}
}