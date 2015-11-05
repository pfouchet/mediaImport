package com.groupeseb.mediaimport.exception.terminal;

public class NoReaderException extends RuntimeException {

	private static final long serialVersionUID = -6705345044083764094L;

	public NoReaderException(String resourceName) {
		super("No Reader for : " + resourceName);
	}
}
