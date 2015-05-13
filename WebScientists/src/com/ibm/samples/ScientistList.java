package com.ibm.samples;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import scientists.db.CloudantDatabase;
import scientists.model.NewScientist;

public class ScientistList extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String groupName = req.getParameter("group");
		
		CloudantDatabase cdatabase = new CloudantDatabase();
		
		HashMap<String, String> scientistMap = new HashMap<String, String>();
		
		List<NewScientist> listScientists = cdatabase.getAllScientistsInGroup(groupName);
		
		for (NewScientist newScientist : listScientists) {
			String name = newScientist.name;
			String url = newScientist.image_url;
			
			scientistMap.put(name, "http://d2yhexj5rb8c94.cloudfront.net/sites/default/files/kalam%2BPTI_1_0_1_0_0_0_0.jpg");
		}
		
		req.setAttribute("listScientists", scientistMap);
		
		req.getRequestDispatcher("ListScientists.jsp").forward(req, resp);
		
		
	}
}
