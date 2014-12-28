package com.ozprojects.model;

import java.util.Date;

import com.ozprojects.model.Portfolio.ALGO_RECOMMENDATION;

/**
 * An instance of this class represents a StockStatus
 * @author Oz Soreq
 * @since JDK7
 * date 28/12/2014
 */
public class StockStatus extends Stock {

	private ALGO_RECOMMENDATION recommendation;
	private int stockQuantity; 
	
	/**
	 * This constructs a StockStatus with a specified 
	 * symbol, currentBid, currentAsk, date, recommendation, stockQuantity
	 */
	public StockStatus()
	{
		this.symbol = "";
		this.ask = 0;
		this.bid = 0;
		this.date = new Date();
		this.recommendation = null;
		this.stockQuantity = 0;
	}
	
	
	/**
	 * This is a copy constructor of StockStatus
	 * @param stockStatus - An instance of StockStatus
	 */
	public StockStatus(StockStatus stockStatus)
	{
		this.symbol = stockStatus.symbol;
		this.ask = stockStatus.ask;
		this.bid = stockStatus.bid;
		this.date = new Date(stockStatus.date.getTime());
		this.recommendation = stockStatus.getRecommendation();
		this.stockQuantity = stockStatus.getStockQuantity();
	}
	/**
	 * This method returns the stock quantity of a stock
	 * @return the value of quantity of stock
	 */
	public int getStockQuantity() {
		return stockQuantity;
	}
	/**
	 * This method a value of quantity into a stock
	 * @param stockQuantity - value of quantity
	 */
	public void setStockQuantity(int stockQuantity) {
		this.stockQuantity = stockQuantity;
	}
	/**
	 * This method returns the recommendation of a stock
	 * @return the value of a recommendation
	 */
	public ALGO_RECOMMENDATION getRecommendation() {
		return recommendation;
	}
	/**
	 * This method sets the value into a recommendation of stock
	 * @param recommendation - value of recommendation
	 */
	public void setRecommendation(ALGO_RECOMMENDATION recommendation) {
		this.recommendation = recommendation;
	}

	


}
