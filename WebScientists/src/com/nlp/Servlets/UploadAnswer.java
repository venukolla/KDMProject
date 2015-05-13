package com.nlp.Servlets;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.nlp.Classes.*;

/**
 * Servlet implementation class UploadAnswer
 */
@WebServlet("/UploadAnswer")
@MultipartConfig(fileSizeThreshold=1024*1024*2, // 2MB
maxFileSize=1024*1024*10,      // 10MB
maxRequestSize=1024*1024*50)   // 50MB
public class UploadAnswer extends HttpServlet {
	private static final String SAVE_DIR = "SaveHere";
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UploadAnswer() {
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
		// gets absolute path of the web application
		response.setContentType("text/html");//setting the content type  
		PrintWriter pw = response.getWriter();//get the stream to write the data  
		pw.println("<html><body>");
		pw.println("<a href='index.html'> Home </a><br><br>");
		String appPath = request.getServletContext().getRealPath("");
		String savePath = appPath + File.separator + SAVE_DIR;
		
		String arg1 = request.getParameter("arg1").toLowerCase();
		String arg2 = request.getParameter("arg2").toLowerCase();
		String arg3 = request.getParameter("arg3").toLowerCase();
		String answer = request.getParameter("txtans");
		String question = arg1 + " " + arg2 + " " + arg3;
		//final Part filePart = request.getPart("file");
		pw.println("<br><h4>Your Question : "+question+"</h4>");
		pw.println("<h4>Answer : "+answer+"</h4><br>");
		File fileSaveDir = new File(savePath);
		if (!fileSaveDir.exists()) {
			fileSaveDir.mkdir();
			}
		final Part part = request.getPart("uploaded");
		String fileName = extractFileName(part);
		part.write(savePath + File.separator + fileName);
		Connection con = null;
		Statement stmt = null;
		try{
			String file_path = SAVE_DIR+"/"+ fileName;
			DBConnection dbc = new DBConnection();
			con = dbc.getConnection();
			stmt = con.createStatement();
			String sql = "INSERT INTO directory " +
	                   "VALUES ('"+question+"', '"+answer+"', '"+file_path+"')";
	        stmt.executeUpdate(sql);
			pw.println("File Saved: "+file_path);
			pw.println("<br><img height='100' width='150' src="+SAVE_DIR+"/"+ fileName+" >");
			pw.println("<h3>Q n A Saved Successfully!</h3>");
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
		
		pw.println("</body></html>");
	}
	
    private String extractFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                return s.substring(s.indexOf("=") + 2, s.length()-1);
            }
        }
        return "";
    }

}
