package com.ozprojects.model;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * An instance of this class represents a stock
 * @author Oz Soreq
 * @since 8/12/2014
 * date - 8/12/2014
 */
public class Stock
{


	private String symbol;
	private float ask;
	private float bid;
	private Date date;
	private String DATE_FORMAT = "MM/dd/yyyy";
	private SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);

	/**
	 * This constructs a Stock with a specified 
	 * stockSymbol, stockAsk, stockBid and stockDate
	 * @param stockSymbol - The symbol of the stock
	 * @param stockAsk - The ask value for the stock
	 * @param stockBid - The bid value for the stock
	 * @param stockDate - The date of the stock
	 */
	
	public Stock(String stockSymbol, float stockAsk, float stockBid,Date stockDate)
	{
		setSymbol(stockSymbol);
		setAsk(stockAsk);
		setBid(stockBid);
		setDate(stockDate);
	}
	
	/**
	 * This is a copy constructor of Stock that
	 * Initializes a copy of recieved stock
	 * @param stock - includes all the date of the recieved stock
	 */
	
	public Stock (Stock stock){

		this.setSymbol(stock.getSymbol());
		this.setBid(stock.getBid());
		this.setAsk(stock.getAsk());
		this.setDate(stock.getDate());
	}

	public void setSymbol(String stockSymbol)
	{
		symbol = stockSymbol;	
	}
	public String getSymbol()
	{
		return symbol;
	}
	public void setAsk(float stockAsk)
	{
		ask = stockAsk;
	}
	public float getAsk()
	{
		return ask;
	}
	public void setBid(float stockBid)
	{
		bid = stockBid;
	}
	public float getBid()
	{
		return bid;
	}
	public void setDate(Date stockDate)
	{
		date = stockDate;
	}
	public Date getDate()
	{
		return date;
	}
	public String getHtmlDescription()
	{
		return ("<b>Stock Symbol:</b> " + symbol + " ,<b>Ask:</b> "+ ask+" ,<b>Bid:</b> "+bid+" ,<b>Date:</b> " +sdf.format(date));
	}

}
