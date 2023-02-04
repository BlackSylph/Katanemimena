package com.katanemimena.project.exceptions;

public class TransferStatementNotFoundException extends RuntimeException {

	public TransferStatementNotFoundException(Long id) {
		super("Could not find transfer statement " + id);
	}
}
