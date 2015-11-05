package com.groupeseb.mediaimport.exception.terminal;

public class NoTransformerException extends RuntimeException {

	private static final long serialVersionUID = -6705345044083764094L;

	public NoTransformerException(String simpleName) {
		super(simpleName);
	}
}
