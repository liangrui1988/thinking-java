package org.rui.ExceptionTest;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.logging.Logger;

public class LoggingException2 {
	private static Logger logger = Logger.getLogger("LoggingException");

	static void LogException(Exception e) {
		StringWriter trace = new StringWriter();
		e.printStackTrace(new PrintWriter(trace));
		logger.severe("severett:" + trace.toString());

	}

	public static void main(String[] args) {

		try {
			throw new NullPointerException();
		} catch (NullPointerException e) {
			LogException(e);
		}

	}
}
