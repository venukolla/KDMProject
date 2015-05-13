package com.ibm.samples;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Yago2Consumer {
	
	static public void main (String args[]) throws IOException, ParseException
	{
		JSONParser parser = new JSONParser();
		 String data;
		
		try {
           
			//data = "Napoleon was the emperor of the First French Empire. He was defeated at Waterloo by Wellington. He was banned to Saint Helena, died of stomach cancer, and was buried at Invalides";
			
			data = "text=Kalam was born in India. ";

            HttpURLConnection con = (HttpURLConnection) new URL("https://gate.d5.mpi-inf.mpg.de/aida/service/disambiguate").openConnection();
            con.setRequestMethod("POST");
            con.setDoOutput(true);
            con.getOutputStream().write(data.getBytes("UTF-8"));

            // Get the inputstream
            BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));

            // .. and print it
            String tmp;
            while((tmp = reader.readLine()) != null) {
                System.out.println(tmp);
                
                Object obj = parser.parse(tmp);
                JSONObject jsonObject = (JSONObject) obj;
                String originalFileName = (String) jsonObject.get("originalFileName");
                String originalText = (String) jsonObject.get("originalText");
                String overallTime = (String) jsonObject.get("overallTime");
                JSONArray mentions = (JSONArray) jsonObject.get("mentions");
              
                
                for(int i=0;i<mentions.size();i++)
                {
                //	System.out.println(mentions.get(i));
                	Object obj2 = parser.parse(mentions.get(i).toString());
                    JSONObject jsonObject2 = (JSONObject) obj2;
                    JSONArray allEntities = (JSONArray) jsonObject2.get("allEntities");
                    
                    for(int j=0;j<allEntities.size();j++)
                    {
                    	Object obj3 = parser.parse(allEntities.get(j).toString());
                    	JSONObject jsonObject3 = (JSONObject) obj3;
                    	String kdId = (String) jsonObject3.get("kbIdentifier");
                    	String disambiguationScore = (String) jsonObject3.get("disambiguationScore");
                    	
                    	String s = kdId.replaceAll("[\\u002e_]", "");
                    	
                    	//String s1[] = kdId.split("");
                    	
                    	String result = "";
                    	/*
                    	for (String string : s1) {
                    		result = result +string + " ";
						}
                    	
                    	*/
                    	System.out.println("KbID: "+s + "===="+"score: "+disambiguationScore);
                    }
                    
                }
                
                
           /*     Iterator<JSONArray> iterator2 = mentions.iterator();
                while (iterator2.hasNext()) {
        			
                	JSONArray tempArray =  (JSONArray)iterator2.next();
                	
                	for(int i=0;i<tempArray.size();i++)
                	{
                		System.out.println(tempArray.get(i));
                	}
        		}*/
                
              /*  JSONArray msg = (JSONArray) jsonObject.get("allEntities");
                Iterator<String> iterator = msg.iterator();
        		while (iterator.hasNext()) {
        			System.out.println("Entities: "+iterator.next());
        		}*/
         
                
             //  System.out.println(mentions);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
		
		
	}
	
	public HashMap<String,String> getNamesConfilct(String ipstring) throws ParseException{
		
		JSONParser parser = new JSONParser();
		 
		//ArrayList<String> names = new ArrayList<String>();
		
		HashMap<String, String> hashMap = new HashMap<String, String>();
		
		try {
          
			//data = "Napoleon was the emperor of the First French Empire. He was defeated at Waterloo by Wellington. He was banned to Saint Helena, died of stomach cancer, and was buried at Invalides";
		
			String data = "text="+ipstring;
			
			System.out.println("Entered text in the name conflicts");
			//data = "text=Newton was born in India. ";

           HttpURLConnection con = (HttpURLConnection) new URL("https://gate.d5.mpi-inf.mpg.de/aida/service/disambiguate").openConnection();
           con.setRequestMethod("POST");
           con.setDoOutput(true);
           con.getOutputStream().write(data.getBytes("UTF-8"));

           // Get the inputstream
           BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));

           // .. and print it
           String tmp;
           while((tmp = reader.readLine()) != null) {
               System.out.println(tmp);
               
               Object obj = parser.parse(tmp);
               JSONObject jsonObject = (JSONObject) obj;
               String originalFileName = (String) jsonObject.get("originalFileName");
               String originalText = (String) jsonObject.get("originalText");
               String overallTime = (String) jsonObject.get("overallTime");
               JSONArray mentions = (JSONArray) jsonObject.get("mentions");
             
               
               for(int i=0;i<mentions.size();i++)
               {
               //	System.out.println(mentions.get(i));
               	Object obj2 = parser.parse(mentions.get(i).toString());
                   JSONObject jsonObject2 = (JSONObject) obj2;
                   JSONArray allEntities = (JSONArray) jsonObject2.get("allEntities");
                   
                   for(int j=0;j<allEntities.size();j++)
                   {
                   	Object obj3 = parser.parse(allEntities.get(j).toString());
                   	JSONObject jsonObject3 = (JSONObject) obj3;
                   	String kdId = (String) jsonObject3.get("kbIdentifier");
                   	
                   	String result = kdId.replaceAll("[\0123456789_-]", "");
                   	String disambiguationScore = (String) jsonObject3.get("disambiguationScore");
                   	System.out.println("KbID: "+kdId + "===="+"score: "+disambiguationScore);
                   	
                   	hashMap.put(result, disambiguationScore);
                   }
                   
               }
               
        
           }
       } catch (UnsupportedEncodingException e) {
           e.printStackTrace();
       } catch (MalformedURLException e) {
           e.printStackTrace();
       } catch (IOException e) {
           e.printStackTrace();
       }
		
		return hashMap;
		
	}

}
