package com.ozprojects.exception;
/**
 * This method: StockNotExistException throws an exception if the stock does not exist
 * @author Oz
 *
 */
public class StockNotExistException extends Exception {

	private static final long serialVersionUID = 1L;

	public StockNotExistException(){
		super("Stock does not exist");
	}
}
