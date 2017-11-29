package com.dao.impl;

import com.dao.PersonDao;

public class PersonDaoImpl implements PersonDao{

	@Override
	public void savePerson() {
		System.out.println("save person.");
	}

	@Override
	public void updatePerson() {
		System.out.println("update person.");
	}

}
