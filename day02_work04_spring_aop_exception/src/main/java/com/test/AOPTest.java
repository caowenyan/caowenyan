package com.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.action.PersonAction;

public class AOPTest {
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		PersonAction personAction = (PersonAction)context.getBean("personAction");
//		personAction.savePerson();
		personAction.updatePerson();
	}
}
