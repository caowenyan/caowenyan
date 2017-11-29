package com.test;

import java.util.Map;
import java.util.Map.Entry;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.action.PersonAction;
import com.aspect.StatisticClass;
import com.aspect.StatisticPerson;

public class AOPTest {
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		PersonAction personAction = (PersonAction)context.getBean("personAction");
		personAction.savePerson();
		personAction.savePerson();
		StatisticPerson statisticPerson = (StatisticPerson)context.getBean("statisticPerson");
		Map<String,StatisticClass> map = statisticPerson.getMap();
		for(Entry<String,StatisticClass> entry:map.entrySet()){
			System.out.print(entry.getKey()+":");
			System.out.println(entry.getValue().getCount());
		}
		personAction.updatePerson();
		map = statisticPerson.getMap();
		for(Entry<String,StatisticClass> entry:map.entrySet()){
			System.out.print(entry.getKey()+":");
			System.out.println(entry.getValue().getCount());
		}
	}
}
