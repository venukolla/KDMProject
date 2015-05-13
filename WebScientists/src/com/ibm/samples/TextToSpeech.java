package com.ibm.samples;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import scientists.db.CloudantDatabase;
import scientists.model.NewScientist;

public class TextToSpeech extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
doPost(req, resp);	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name  = req.getParameter("text");
		
		CloudantDatabase cdatabase = new CloudantDatabase();
		
		NewScientist st = cdatabase.getScientist(name.toUpperCase().trim());
		
		String output1  = cdatabase.prepareOutput(st);
		
		String output = output1.replaceFirst("[~`!@#$%^&*()_//'-.]", " ");
		
		req.setAttribute("output", output);
		
		req.getRequestDispatcher("index.jsp").forward(req, resp);
	}
}
