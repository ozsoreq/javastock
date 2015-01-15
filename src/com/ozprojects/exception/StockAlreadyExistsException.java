package com.ozprojects.exception;
/**
 * the method StockAlreadyExistsException throws an exception if the stock already exists
 * @author Oz
 *
 */
public class StockAlreadyExistsException extends Exception {

	private static final long serialVersionUID = 1L;

	public StockAlreadyExistsException(){
		super("Stock already exists");
	}
}
