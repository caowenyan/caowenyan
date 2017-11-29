package com.aspect;

import java.util.HashMap;
import java.util.Map;

import org.aspectj.lang.JoinPoint;

public class StatisticPerson {
	private Map<String,StatisticClass>map = new HashMap<String, StatisticClass>();

	public Map<String, StatisticClass> getMap() {
		return map;
	}


	/** 前置通知 */
	public void beforeMethod(JoinPoint joinPoint){
		String classname = joinPoint.getTarget().getClass().getName();
		String methodname = joinPoint.getSignature().getName();
		String key = classname + ":" + methodname ;
		if(map.containsKey(key)){
			StatisticClass statisticClass = map.get(key);
			statisticClass.setCount(statisticClass.getCount()+1);
		}else{
			StatisticClass statisticClass = new StatisticClass();
			statisticClass.setCount(1);
			statisticClass.setKey(key);
			map.put(key, statisticClass);
		}
	}
}
