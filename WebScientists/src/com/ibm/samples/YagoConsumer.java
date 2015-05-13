package com.ibm.samples;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.parser.ParseException;


public class YagoConsumer extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HashMap<String, String> hashMap;
		String input = req.getParameter("inputSentense");
		
		System.out.println("entered text:"+ input);
		
		Yago2Consumer yagoConsumer = new Yago2Consumer();
		try {
			
			hashMap = yagoConsumer.getNamesConfilct(input);
			
			req.setAttribute("hashmap", hashMap);
		} catch (ParseException e) {
			System.out.println("Exception in YagoConsumer:"+ e.getMessage());
		}
		
		
		req.getRequestDispatcher("Yago.jsp").forward(req, resp);
		
	}
}
