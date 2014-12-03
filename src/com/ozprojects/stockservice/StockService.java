package com.ozprojects.stockservice;

import java.util.Date;

import javax.servlet.http.HttpServlet;

import com.mta.javacourse.Stock;
import com.ozprojects.stockmodel.Portfolio;

public class StockService
{

	public Portfolio getPorfolio()
	{
		Portfolio myPortfolio = new Portfolio();
		
		Stock Stock1 = new Stock(("PIH"), (float)12.4, (float)13.1,new Date(114,10,15));
		Stock Stock2 = new Stock(("AAL"), (float)5.5, (float)5.78,new Date(114,10,15));
		Stock Stock3 = new Stock(("CAAS"), (float)31.5, (float)31.2,new Date(114,10,15));
		
		myPortfolio.addStock(Stock1);
		myPortfolio.addStock(Stock2);
		myPortfolio.addStock(Stock3);
		
		return myPortfolio;
	}
}
