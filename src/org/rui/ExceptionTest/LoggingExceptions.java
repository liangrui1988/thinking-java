package org.rui.ExceptionTest;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.logging.Logger;

public class LoggingExceptions {
	public static void main(String[] args) {

		try {
			throw new LoggingException();
		} catch (LoggingException e) {
			System.err.print("Caught: " + e);
		}

		try {
			throw new LoggingException();
		} catch (LoggingException e) {
			System.err.print("Caught2: " + e);
		}

	}
}

class LoggingException extends Exception {
	private static Logger logger = Logger.getLogger("LoggingException");

	public LoggingException() {
		StringWriter trace = new StringWriter();
		printStackTrace(new PrintWriter(trace));
		logger.severe("severett:" + trace.toString());
	}

}
