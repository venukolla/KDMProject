package com.ibm.samples;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import scientists.db.CloudantDatabase;
import scientists.model.NewScientist;
import scientists.model.Scientist;

public class TestWebpage extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String input = req.getParameter("Input");
		
		
		
		CloudantDatabase cdatabase = new CloudantDatabase();
		NewScientist s = cdatabase.getScientist(input);
		
		String string = cdatabase.prepareOutput(s);
		
		req.setAttribute("output", string);
		
		req.getRequestDispatcher("index.jsp").forward(req, resp);
	}
}
