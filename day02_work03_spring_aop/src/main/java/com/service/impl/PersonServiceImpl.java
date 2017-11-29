package com.service.impl;


import com.dao.PersonDao;
import com.service.PersonService;

public class PersonServiceImpl implements PersonService{
	private PersonDao personDao;

	public PersonDao getPersonDao() {
		return personDao;
	}

	public void setPersonDao(PersonDao personDao) {
		this.personDao = personDao;
	}

	@Override
	public void savePerson() {
		this.personDao.savePerson();
	}

	@Override
	public void updatePerson() {
		this.personDao.updatePerson();
	}
	
	
}
