package com.ozprojects.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ozprojects.exception.BalanceException;
import com.ozprojects.exception.PortfolioFullException;
import com.ozprojects.exception.StockAlreadyExistsException;
import com.ozprojects.exception.StockNotExistsException;
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
	private List<StockStatus> stockStatus;
	private int portfolioSize;
	private static final int MAX_PORTFOLIO_SIZE = 5;
	private float balance;
	public String title;
	private int stockStatusSize;

	/**
	 * This constructs a Portfolio with a specified 
	 * stocks, stocksStatus, portfolioSize, title
	 */

	public Portfolio()
	{
		//stocks = new Stock[MAX_PORTFOLIO_SIZE];
		stockStatusSize=0;
		stockStatus = new ArrayList<StockStatus>(MAX_PORTFOLIO_SIZE);
		portfolioSize=0;
		title=("Portfolio#1");
		balance = 0;
	}

	/**
	 * This is a copy constructor of portfolio
	 * @param portfolio - An instance of Portfolio
	 */

	public Portfolio(Portfolio portfolio){
		this();
		title=portfolio.title;
		portfolioSize=portfolio.portfolioSize;
		stockStatusSize=portfolio.stockStatusSize;
		stockStatus.addAll(portfolio.stockStatus);
		balance=portfolio.balance;
	}
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Portfolio(List <StockStatus> stockStatus)
	{
		this();

		for (int i = 0; i < stockStatus.size(); i++) {
			stockStatus.add(stockStatus.get(i));
		}
	}

	/**
	 * This method adds a stock to a portfolio
	 * @param recievedStock - a stock
	 * @throws StockAlreadyExistsException throws an exception if the stock already exists
	 * @throws PortfolioFullException throws an exception if the portfolio is full
	 */
	public void addStock(Stock stock) throws StockAlreadyExistsException, PortfolioFullException {
		{
			if (portfolioSize >= getMaxPortfolioSize())
			{
				throw new PortfolioFullException();
			}
			for (int i = 0; i < stockStatus.size(); i++) {

				if (stockStatus.get(i).getSymbol().equals(stock.getSymbol())) {
					System.out.println(stockStatus.get(i).getSymbol() + " Already exists in the portfolio");

					throw new StockAlreadyExistsException(stock.getSymbol());
				}
			}
			stockStatus.add(new StockStatus((StockStatus) stock));
		}

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
			HtmlString=HtmlString+(stockStatus.get(i).getHtmlDescription()+"<br>");
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
	public void removeStock(String symbol) throws StockNotExistsException
	{
		{	
			int index = get_index_of_symbol(symbol);

			if(index ==-1){
				throw new StockNotExistsException(symbol);
			}

			sellStock(symbol, -1);

			while (index < stockStatus.size())
			{
				stockStatus.remove(index);
				index++;
			}
		}
	}

	public StockStatus[] getStocks() {
		StockStatus[] ret = new StockStatus[stockStatus.size()];
		ret =  stockStatus.toArray(ret);
		return ret;
	}

	private int get_index_of_symbol(String symbol)
	{
		for(int i = 0; i < stockStatus.size(); i++)
		{
			if(stockStatus.get(i).getSymbol().toLowerCase().equals(symbol.toLowerCase()))
			{
				return i;
			}
		}
		return -1; // index not found
	}
	public void printPortfolio(Portfolio portfolio)
	{
		System.out.println("method printPortfolio");
		System.out.println("portfolioSize in portfolioPrint is:" + portfolio.portfolioSize);
		for (int i = 0; i < portfolio.portfolioSize; i++) {
			System.out.println(portfolio.stockStatus.get(i).getHtmlDescription());
		}
	}

	public void printStockStatusList (List<StockStatus> stockStatuses)
	{
		System.out.println("method printStockStatusList");
		System.out.println("stockStatuses.size() in printStockStatusList is:" + stockStatuses.size());
		for (int i = 0; i < stockStatuses.size(); i++) {
			System.out.println(stockStatuses.get(i).symbol);
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
	public void sellStock(String symbol, int quantity) throws StockNotExistsException
	{
		boolean isFound=false;
		for (int i = 0; i < portfolioSize; i++)
		{
			if (stockStatus.get(i).getSymbol().equals(symbol))
			{
				isFound = true;
				if (quantity==-1)
				{
					balance=balance+stockStatus.get(i).getStockQuantity()*stockStatus.get(i).bid;
					stockStatus.get(i).setStockQuantity(0);
				}
				else if (stockStatus.get(i).getStockQuantity()-quantity<0)
				{
					System.out.println("Not enough stocks to sell");
				}
				else
				{
					balance = balance+(quantity*stockStatus.get(i).bid);
					stockStatus.get(i).setStockQuantity(stockStatus.get(i).getStockQuantity()-quantity);
				}
			}
		}
		if(!isFound)
		{
			throw new StockNotExistsException(symbol);
		}
	}
	/**
	 * This method buys a specific stock for a portfolio
	 * @param symbol - symbol of stock
	 * @param quantity - quantity of stock
	 * @throws StockNotExistException throws an exception if the stock does not exist
	 * @throws BalanceException throws an exception if the balance is about to be negative
	 */
	public void buyStock(String symbol, int quantity) throws BalanceException, StockNotExistsException
	{
		boolean isFound = false;
		for (int i=0 ; i <portfolioSize; i++)
		{
			if (stockStatus.get(i).symbol.equals(symbol))
			{
				isFound = true;
				if (quantity==-1)
				{
					int ammountToBuy = (int)(balance/stockStatus.get(i).ask);
					stockStatus.get(i).setStockQuantity(stockStatus.get(i).getStockQuantity() + ammountToBuy);
					balance = balance-(stockStatus.get(i).ask*ammountToBuy);
				}
				else if(quantity*stockStatus.get(i).ask > balance)
				{
					throw new BalanceException();
				}
				else
				{
					stockStatus.get(i).setStockQuantity(stockStatus.get(i).getStockQuantity() + quantity);
					balance = balance - (quantity*stockStatus.get(i).ask);	
				}

			}
		}
		if(!isFound)
		{
			throw new StockNotExistsException(symbol);
		}

	}
	/**
	 * This method returns the stocks value of a portfolio
	 * @return value of stocks
	 */
	public float getStocksValue()
	{
		float ret = 0;		
		for (int i=0; i < stockStatus.size() ; i++){
			ret += stockStatus.get(i).getStockQuantity() * stockStatus.get(i).getBid();
		}
		return ret;
	}
	public List<StockStatus> getStockStatus() {
		return stockStatus;
	}

	public void setStockStatus(List<StockStatus> stockStatus) {
		this.stockStatus = stockStatus;
	}

	public int getPortfolioSize() {
		return portfolioSize;
	}

	public void setPortfolioSize(int portfolioSize) {
		this.portfolioSize = portfolioSize;
	}

	public int getStockStatusSize() {
		return stockStatusSize;
	}

	public void setStockStatusSize(int stockStatusSize) {
		this.stockStatusSize = stockStatusSize;
	}

	public void setBalance(float balance) {
		this.balance = balance;
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

	public StockStatus findBySymbol(String symbol) throws StockNotExistsException {
		boolean isFound = false;
		for (int i=0 ; i <portfolioSize; i++)
		{
			if (stockStatus.get(i).symbol.equals(symbol))
			{
				return stockStatus.get(i);

			}
		}
		if(!isFound)
		{
			throw new StockNotExistsException(symbol);
		}
		return null;
	}

	public static int getMaxPortfolioSize() {
		return MAX_PORTFOLIO_SIZE;
	}
}       


