package com.ozprojects.exception;
/**
 * this method PortfolioFullException throws an exception if the portfolio is full
 * @author Oz
 *
 */
public class PortfolioFullException extends Exception {

	private static final long serialVersionUID = 1L;

	public PortfolioFullException(){
		super("Not allowed to add too many stocks");
	}
}
