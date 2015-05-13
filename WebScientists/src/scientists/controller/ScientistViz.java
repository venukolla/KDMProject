package scientists.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import profile.scientists.ProfileIInsightsUserModelling;
import scientists.Config;
import scientists.model.NewScientist;
import scientists.model.Scientist;

public class ScientistViz extends HttpServlet{

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, resp);
	}
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String researchInterst = req.getParameter("input");
		
		NewScientist s = Config.cc.getScientist(researchInterst.toUpperCase());
		
		if (s == null)
		{
			//return error();
		}
		
		ProfileIInsightsUserModelling pfiUserModelling = new ProfileIInsightsUserModelling();
		String vizHTML = pfiUserModelling.getPersonVizHTML(s);
		
		if (vizHTML == null)
		{
			//return error();
		}
		
		req.setAttribute("output", vizHTML);
		
		req.getRequestDispatcher("index.jsp").forward(req, resp);
	}
	
	private void error()
	{
		/*return Response.serverError()
				.header("Access-Control-Allow-Origin", "*")
	            .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
	            .build();*/
	}
	
}
