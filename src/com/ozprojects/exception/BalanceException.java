package com.ozprojects.exception;
/**
 * 
 * This method: BalanceException throws an exception if the balance is about to be negative
 *@author Oz
 */
public class BalanceException extends Exception {

	private static final long serialVersionUID = 1L;

	public BalanceException(){
		super("Impossible to complete the transaction, balance will become negative");
	}
}
