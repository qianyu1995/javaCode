package com.higanbana.io.utils;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public enum Calculator
{
	Instance;
	
	private final static ScriptEngine jse = new ScriptEngineManager().getEngineByName("JavaScript");
	
	public static Object cal(String expression) throws ScriptException
	{
		return jse.eval(expression);
	}
}