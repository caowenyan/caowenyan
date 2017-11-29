package com.dao.impl;

import com.dao.PersonDao;

public class PersonDaoImpl implements PersonDao{

	@Override
	public void savePerson() {
		int i = 10/0;
		System.out.println("save person.");
	}

	@Override
	public void updatePerson() {
		Integer.parseInt("dsa");
		System.out.println("update person.");
	}

}
