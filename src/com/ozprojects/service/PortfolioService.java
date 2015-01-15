package com.ozprojects.service;

import java.util.Date;

import javax.servlet.http.HttpServlet;

import com.ozprojects.exception.BalanceException;
import com.ozprojects.exception.PortfolioFullException;
import com.ozprojects.exception.StockAlreadyExistsException;
import com.ozprojects.exception.StockNotExistException;
import com.ozprojects.model.Portfolio;
import com.ozprojects.model.Stock;
/**
 * An instance of this class represents a stock service
 * @author Oz Soreq
 * @since 08/12/2014
 * date 8/12/2014
 */
public class PortfolioService
{
	public Portfolio getPorfolio() throws BalanceException, StockAlreadyExistsException, StockNotExistException, PortfolioFullException
	{
		Portfolio myPortfolio = new Portfolio();
		myPortfolio.title = "Exercise 7 portfolio";
		myPortfolio.updateBalance(10000);
		Stock Stock1 = new Stock(("PIH"), (float)10, (float)8.5,new Date(114,11,15));
		Stock Stock2 = new Stock(("AAL"), (float)30, (float)25.5,new Date(114,11,15));
		Stock Stock3 = new Stock(("CAAS"), (float)20, (float)15.5,new Date(114,11,15));
		
		myPortfolio.addStock(Stock1);
		myPortfolio.addStock(Stock2);
		myPortfolio.addStock(Stock3);
		myPortfolio.addStock(Stock3);
		
		myPortfolio.buyStock("PIH", 20);
		myPortfolio.buyStock("AAL", 30);
		myPortfolio.buyStock("CAAS", 40);
		
		myPortfolio.sellStock("AAL", -1);
		myPortfolio.removeStock("CAAS");
		
		return myPortfolio;
	}
	
}
