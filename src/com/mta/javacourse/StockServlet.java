package com.mta.javacourse;

import java.io.IOException;
import java.util.Date;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;


public class StockServlet extends HttpServlet{
	
	public StockServlet(String stockSymbol, float stockAsk, float stockBid)
	{
		setSymbol(stockSymbol);
		setAsk(stockAsk);
		setBid(stockBid);
		setDate();
	}
	private String symbol;
	private float ask;
	private float bid;
	private Date date;
	private String DATE_FORMAT = "MM/dd/yyyy";
	private SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
	
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
	public void setDate()
	{
		date = new Date();
		date.setDate(15);
		date.setMonth(10);
		date.setYear(114);
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
