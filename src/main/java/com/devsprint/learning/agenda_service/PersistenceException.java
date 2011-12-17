package com.devsprint.learning.agenda_service;

public class PersistenceException extends Exception {

	/** Serial version. */
	private static final long serialVersionUID = 1L;

	/**
	 * Create a new exception and pass the error message.
	 * 
	 * @param message
	 *            - exception message
	 */
	public PersistenceException(final String message) {
		super(message);
	}

}
