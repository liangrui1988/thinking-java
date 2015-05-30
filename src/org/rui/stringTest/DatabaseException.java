package org.rui.stringTest;

public class DatabaseException extends Exception {
	public DatabaseException(int transactionId, int queryID, String message) {
		super(String.format("(t%-5d,q%-5d) %10s", transactionId, queryID,
				message));
	}

	public static void main(String[] args) {

		/*
		 * try { throw new DatabaseException(6,7, "Write failed"); } catch
		 * (DatabaseException e) { System.out.println(e); }
		 */

	}

}
