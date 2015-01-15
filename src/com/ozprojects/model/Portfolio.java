package com.ozprojects.model;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ozprojects.exception.BalanceException;
import com.ozprojects.exception.PortfolioFullException;
import com.ozprojects.exception.StockAlreadyExistsException;
import com.ozprojects.exception.StockNotExistException;
/**
 * An instance of this class represents a portfolio
 * @author Oz Soreq
 * @since 8/12/2014
 * date 8/12/2014
 */
public class Portfolio
{
	public enum ALGO_RECOMMENDATION{
		DO_NOTHING,
		BUY,
		SELL;
	}
	//public Stock[] stocks;
	private StockStatus[] stocksStatus;
	private int portfolioSize;
	private static final int MAX_PORTFOLIO_SIZE = 5;
	private float balance;
	public String title;

	/**
	 * This constructs a Portfolio with a specified 
	 * stocks, stocksStatus, portfolioSize, title
	 */

	public Portfolio()
	{
		//stocks = new Stock[MAX_PORTFOLIO_SIZE];
		stocksStatus = new StockStatus[MAX_PORTFOLIO_SIZE];
		portfolioSize=0;
		title=("Portfolio#1");
		balance = 0;
	}

	/**
	 * This is a copy constructor of portfolio
	 * @param portfolio - An instance of Portfolio
	 */

	public Portfolio(Portfolio portfolio){

		//stocks = new Stock[MAX_PORTFOLIO_SIZE];
		stocksStatus = new StockStatus[MAX_PORTFOLIO_SIZE];
		for (int i = 0; i < portfolio.portfolioSize; i++) {
			//this.stocks[i] = new Stock(portfolio.stocks[i]); 
			this.stocksStatus[i]=new StockStatus(portfolio.stocksStatus[i]); 
		}
		this.title=portfolio.title;
		this.portfolioSize=portfolio.portfolioSize;
		this.balance=portfolio.balance;
	}
	/**
	 * This method adds a stock to a portfolio
	 * @param recievedStock - a stock
	 * @throws StockAlreadyExistsException throws an exception if the stock already exists
	 * @throws PortfolioFullException throws an exception if the portfolio is full
	 */
	public void addStock(Stock recievedStock) throws PortfolioFullException, StockAlreadyExistsException
	{
		for(int i=0 ; stocksStatus[i]!=null ; i++)
		{
			if(stocksStatus[i].getSymbol().equals(recievedStock.getSymbol()))
			{
				throw new StockAlreadyExistsException();
			}
		}
		if (portfolioSize >= MAX_PORTFOLIO_SIZE)
		{
			throw new PortfolioFullException();
		}
		else
		{
			//stocks[portfolioSize] = recievedStock;
			stocksStatus[portfolioSize] = new StockStatus();
			stocksStatus[portfolioSize].ask = recievedStock.getAsk();
			stocksStatus[portfolioSize].bid = recievedStock.getBid();
			stocksStatus[portfolioSize].date = recievedStock.getDate();
			stocksStatus[portfolioSize].symbol = recievedStock.getSymbol();
			portfolioSize++;
		}
	}
	public Stock[] getStocks()
	{
		return stocksStatus;
	}
	/**
	 * 
	 * @return - a string that includes the description and status of stock
	 */
	public String getHtmlString()
	{
		String HtmlString= new String("<h1>"+title+"</h1>");
		for (int i = 0; i < portfolioSize; i++) 
		{ 
			HtmlString=HtmlString+(stocksStatus[i].getHtmlDescription()+"<br>");
		}
		HtmlString = HtmlString + "<br> Total portfolio value: " + getTotalValue() + "$<br>Total stocks value: " + getStocksValue()
				+ "$<br>Balance: " + getBalance()+"$";
		
		return HtmlString;
	}
	/**
	 * This method remove a stock from a Portfolio instance
	 * @param symbol - symbol of stock
	 * @throws StockNotExistException throws an exception if the stock does not exist
	 */
	public void removeStock(String symbol) throws StockNotExistException
	{
		boolean isFound = false;
		sellStock(symbol, -1);
		for (int i = 0; i < portfolioSize; i++)
		{
			if(stocksStatus[i].symbol.equals(symbol))
			{
				for (int j = i; j < portfolioSize-i; j++)
				{
				//	stocks[j]=stocks[j+1];
					stocksStatus[j]=stocksStatus[j+1];
				}
				portfolioSize--;
				isFound = true;
			}
		}
		if(!isFound)
		{
			throw new StockNotExistException();
		}
	}
	
	/**
	 * This method update the balance of a portfolio
	 * @param amount
	 */
	public void updateBalance(float amount)
	{
		this.balance = balance+(amount);
	}
	/**
	 * This method sell a specific stock from portfolio
	 * @param symbol - symbol of stock
	 * @param quantity - quantity of stock
	 * @throws StockNotExistException throws an exception if the stock does not exist
	 */
	public void sellStock(String symbol, int quantity) throws StockNotExistException
	{
		boolean isFound=false;
		for (int i = 0; i < portfolioSize; i++)
		{
			if (stocksStatus[i].symbol.equals(symbol))
			{
				isFound = true;
				if (quantity==-1)
				{
					balance = balance+(stocksStatus[i].getStockQuantity()*stocksStatus[i].bid);
					stocksStatus[i].setStockQuantity(0);
				}
				else if (stocksStatus[i].getStockQuantity()-quantity<0)
				{
					System.out.println("Not enough stocks to sell");
				}
				else
				{
					balance = balance+(quantity*stocksStatus[i].bid);
					stocksStatus[i].setStockQuantity(stocksStatus[i].getStockQuantity()-quantity);
				}
			}
		}
		if(!isFound)
		{
			throw new StockNotExistException();
		}
	}
	/**
	 * This method buys a specific stock for a portfolio
	 * @param symbol - symbol of stock
	 * @param quantity - quantity of stock
	 * @throws StockNotExistException throws an exception if the stock does not exist
	 * @throws BalanceException throws an exception if the balance is about to be negative
	 */
	public void buyStock(String symbol, int quantity) throws BalanceException, StockNotExistException
	{
		boolean isFound = false;
		for (int i=0 ; i <portfolioSize; i++)
		{
			if (stocksStatus[i].symbol.equals(symbol))
			{
				isFound = true;
				if (quantity==-1)
				{
					int ammountToBuy = (int)(balance/stocksStatus[i].ask);
					stocksStatus[i].setStockQuantity(stocksStatus[i].getStockQuantity() + ammountToBuy);
					balance = balance-(stocksStatus[i].ask*ammountToBuy);
				}
				else if(quantity*stocksStatus[i].ask > balance)
				{
					throw new BalanceException();
				}
				else
				{
					stocksStatus[i].setStockQuantity(stocksStatus[i].getStockQuantity() + quantity);
					balance = balance - (quantity*stocksStatus[i].ask);	
				}
				
			}
		}
		if(!isFound)
		{
			throw new StockNotExistException();
		}
		
	}
	/**
	 * This method returns the stocks value of a portfolio
	 * @return value of stocks
	 */
	public float getStocksValue()
	{
		float valueStock=0;
		for (int i = 0; i < portfolioSize; i++) 
		{
			valueStock = valueStock + (stocksStatus[i].getStockQuantity()*stocksStatus[i].bid);
		}
		return valueStock;
	}
	/**
	 * This method returns the balance of a portfolio
	 * @return balance
	 */
	public float getBalance()
	{
		return balance;
	}
	/**
	 * This method returns the total value of a portfolio
	 * @return the value of a portfolio
	 */
	public float getTotalValue()
	{
		return getBalance()+getStocksValue();
	}
}       


