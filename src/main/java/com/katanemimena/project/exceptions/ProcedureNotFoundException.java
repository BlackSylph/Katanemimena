package com.katanemimena.project.exceptions;

public class ProcedureNotFoundException extends RuntimeException {

	public ProcedureNotFoundException(Long id) {
		super("Could not find procedure " + id);
	}

}
