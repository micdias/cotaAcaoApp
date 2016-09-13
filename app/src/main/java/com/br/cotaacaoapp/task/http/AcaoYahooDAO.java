package com.br.cotaacaoapp.task.http;


import com.br.cotaacaoapp.dto.PapelAtualizado_Y;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;



public class AcaoYahooDAO {
	
	/*
	* Returns a Stock Object that contains info about a specified stock.
	* @param 	symbol the company's stock symbol
	* @return 	a stock object containing info about the company's stock
	* @see Stock
	*/
	public PapelAtualizado_Y consultaAcaoAtualizada(String papel) {
		String sym = papel.toUpperCase();
		double price = 0.0;
		int volume = 0;
		double pe = 0.0;
		double eps = 0.0;
		double week52low = 0.0;
		double week52high = 0.0;
		double daylow = 0.0;
		double dayhigh = 0.0;
		double movingav50day = 0.0;
		double marketcap = 0.0;
		String name = "";
		String currency = "";
		double shortRatio = 0.0;
		double open = 0.0;
		double previousClose = 0.0;
		String exchange;
		try { 
			
			URL yahoo = new URL("http://finance.yahoo.com/d/quotes.csv?s="+ papel + "&f=l1vr2ejkghm3j3nc4s7pox");
			URLConnection connection = yahoo.openConnection(); 
			InputStreamReader is = new InputStreamReader(connection.getInputStream());
			BufferedReader br = new BufferedReader(is);  
			
			String line = br.readLine();
			String[] stockinfo = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
			

			
			price = getDouble(stockinfo[0]);
			volume = getInt(stockinfo[1]);
			pe = getDouble(stockinfo[2]);
			eps = getDouble(stockinfo[3]);
			week52low = getDouble(stockinfo[4]);
			week52high = getDouble(stockinfo[5]);
			daylow = getDouble(stockinfo[6]);
			dayhigh = getDouble(stockinfo[7]);
			movingav50day = getDouble(stockinfo[8]);
			marketcap = getDouble(stockinfo[9]);
			name = stockinfo[10].replace("\"", "");
			currency = stockinfo[11].replace("\"", "");
			shortRatio = getDouble(stockinfo[12]);
			previousClose = getDouble(stockinfo[13]);
			open = getDouble(stockinfo[14]);
			exchange = stockinfo[15].replace("\"", "");
			
		} catch (IOException e) {
			Logger log = Logger.getLogger(AcaoYahooDAO.class.getName());
			log.log(Level.SEVERE, e.toString(), e);
			return null;
		}
		
		return new PapelAtualizado_Y(sym, price, volume, pe, eps, week52low, week52high, daylow, dayhigh, movingav50day, marketcap, name,currency, shortRatio,previousClose,open,exchange);
		
	}
	
	private  double getDouble(String x) {
		Double y;
		if (Pattern.matches("N/A", x)) {  
			y = 0.00;   
		} else { 
			y = Double.parseDouble(x);  
		}  
		return y;
	}
	
	private  int getInt(String x) {
		int y;
		if (Pattern.matches("N/A", x)) {  
			y = 0;   
		} else { 
			y = Integer.parseInt(x);  
		} 
		return y;
	}
}
