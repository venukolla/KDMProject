package com.nlp.Servlets;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jsoup.*;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.net.Authenticator;
import java.net.PasswordAuthentication;

import com.nlp.Classes.DBConnection;

/**
 * Servlet implementation class TextRunner
 */
@WebServlet("/TextRunner")
public class TextRunner extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TextRunner() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");//setting the content type  
		PrintWriter pw = response.getWriter();//get the stream to write the data  
		pw.println("<html><body>");
		pw.println("<a href='index.html'> Home </a><br><br>");
		
		String arg1 = request.getParameter("arg1").toLowerCase();
		String arg2 = request.getParameter("arg2").toLowerCase();
		String arg3 = request.getParameter("arg3").toLowerCase();
		String question = arg1 + " " + arg2 + " " + arg3;
		pw.println("<br><h4>Your Question : "+question+" ?</h4>");
		
		Connection con = null;
		Statement stmt = null;
		int count_ans = 0;
		try{
			DBConnection dbc = new DBConnection();
			con = dbc.getConnection();
			String sql = "SELECT * FROM directory where question = '"+question+"'";
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
		      //STEP 5: Extract data from result set
		      while(rs.next()){
		    	  String answer1 = rs.getString("answer");
		    	  String img_url = rs.getString("image");
		    	  pw.println("Answer:  <h4>"+answer1+"</h4>");
		    	  pw.println("<img height='100' width='150' src="+img_url+" ><br>");
		    	  count_ans++;
		         
		      }
		      rs.close();
		}catch(SQLException se){
		      //Handle errors for JDBC
			pw.println("JDBC Error");
		      se.printStackTrace();
		   }catch(Exception e){
		      //Handle errors for Class.forName
			   pw.println("Class For Name Error");
		      e.printStackTrace();
		   }finally{
		      //finally block used to close resources
		      try{
		         if(stmt != null)
		            con.close();
		      }catch(SQLException se){
		      }// do nothing
		      try{
		         if(con != null)
		            con.close();
		      }catch(SQLException se){
		         se.printStackTrace();
		      }//end finally try
		   }//end try
		
		if (count_ans == 0){
			pw.println("<h5> No Results found in the internal corpora!</h5>");
		}
		pw.println("<h5> Answer from the external Corpora: </h5>");
		String url1 = "http://openie.cs.washington.edu/search?arg1="+arg1+"&rel="+arg2+"&arg2="+arg3+"&corpora=";
		String url2 = "http://websearch.about.com/sitesearch.htm?q="+arg1+"+"+arg2+"+"+arg3;
		
		try
		{
			final String authUser = "";
			final String authPassword = "";
			System.setProperty("http.proxyHost", "proxy.iiit.ac.in");
			System.setProperty("http.proxyPort", "8080");
				Authenticator.setDefault(
					  new Authenticator() 
					  {
					    public PasswordAuthentication getPasswordAuthentication()
					    {
					      return new PasswordAuthentication(authUser, authPassword.toCharArray());
					    }
					  }
					);
		Document doc = Jsoup.connect(url1).get();
		Document doc1 = Jsoup.connect(url2).get();
		Elements divs = doc1.select("div.res a");
		Elements anss = doc.select("div#results-content li.hidden-phone a");
		for (Element each_ans : anss) {
			String answ = "";
			if (each_ans.text().contains("(")){
				answ = each_ans.text();
			    String[] words = answ.split(Pattern.quote("("));
				pw.println(words[0]+"<br>");
			}
				
        }
		for(Element elem : divs){
			String answ = "";
			if (elem.text().contains("-")){
				answ = elem.text();
			    String[] words = answ.split(Pattern.quote("-"));
				pw.println(words[0]+"<br>");
			}
			  //System.out.println(elem.text()+"\n"); //get all elements inside div
			}
		
		}
		catch(IOException e)
		{
			pw.println("Internet Not Available! You must have a Internet Connection and set proxy in the program if you're behind a proxy.");
			e.printStackTrace();
		}
		pw.println("</body></html>");
	}

}
