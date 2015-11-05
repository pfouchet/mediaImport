package com.groupeseb.mediaimport.exception.terminal;

public class CSVParseException extends RuntimeException {
	private static final long serialVersionUID = 2444806452528413785L;

	public CSVParseException(Exception e) {
		super(e);
	}
}
