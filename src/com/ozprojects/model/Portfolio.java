package com.ozprojects.model;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
	 */
	public void addStock(Stock recievedStock)
	{
		if (portfolioSize >= MAX_PORTFOLIO_SIZE)
		{
			System.out.println("Can’t add new stock, portfolio can have only "+MAX_PORTFOLIO_SIZE+ " stocks");
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
	 * @return true/false whether the operation was successfull or not
	 */
	public boolean removeStock(String symbol)
	{
		
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
				return true;
			}
		}
		return false;
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
	 * @return true/false if the operation was successful
	 */
	public boolean sellStock(String symbol, int quantity)
	{
		boolean flag=false;
		for (int i = 0; i < portfolioSize; i++)
		{
			if (stocksStatus[i].symbol.equals(symbol))
			{
				if (quantity==-1)
				{
					balance = balance+(stocksStatus[i].getStockQuantity()*stocksStatus[i].bid);
					stocksStatus[i].setStockQuantity(0);
				}
				else if (stocksStatus[i].getStockQuantity()-quantity<0)
				{
					System.out.println("Not enough stocks to sell");
					return false;
				}
				else
				{
					balance = balance+(quantity*stocksStatus[i].bid);
					stocksStatus[i].setStockQuantity(stocksStatus[i].getStockQuantity()-quantity);
				}
			 flag = true;
			}
		}
		return flag;
	}
	/**
	 * This method buys a specific stock for a portfolio
	 * @param symbol - symbol of stock
	 * @param quantity - quantity of stock
	 * @return true/false if the operation was successful
	 */
	public boolean buyStock(String symbol, int quantity)
	{
		for (int i=0 ; i <portfolioSize; i++)
		{
			if (stocksStatus[i].symbol.equals(symbol))
			{
				if (quantity==-1)
				{
					int ammountToBuy = (int)(balance/stocksStatus[i].ask);
					stocksStatus[i].setStockQuantity(stocksStatus[i].getStockQuantity() + ammountToBuy);
					balance = balance-(stocksStatus[i].ask*ammountToBuy);
				}
				else if(quantity*stocksStatus[i].ask > balance)
				{
					System.out.println("Not enough balance to complete purchase");
					return false;
				}
				else
				{
					stocksStatus[i].setStockQuantity(stocksStatus[i].getStockQuantity() + quantity);
					balance = balance - (quantity*stocksStatus[i].ask);	
				}
				return true;
			}
		}
		return false;
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


