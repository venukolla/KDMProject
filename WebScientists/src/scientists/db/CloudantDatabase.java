package scientists.db;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.ektorp.CouchDbConnector;
import org.ektorp.CouchDbInstance;
import org.ektorp.http.StdHttpClient;
import org.ektorp.impl.StdCouchDbConnector;
import org.ektorp.impl.StdCouchDbInstance;

import scientists.Config;
import scientists.Constants;
import scientists.controller.JsonUtils;
import scientists.model.BioInfo;
import scientists.model.NewScientist;
import scientists.model.Qualification;
import scientists.model.ResearchInterest;
import scientists.model.Scientist;
//import twitter4j.JSONArray;
//import twitter4j.JSONObject;











import com.ibm.json.java.JSONArray;
import com.ibm.json.java.JSONObject;
import com.ibm.samples.AddScientist;
import com.ibm.samples.Trait;


public class CloudantDatabase {
	
	private org.ektorp.http.HttpClient httpClient;
	private CouchDbConnector dbc;
	
	private int port;
	private String name;
	private String host;
	private String username;
	private String password;
	
	private JSONArray cloudant;
	private JSONObject cloudantInstance;
	private JSONObject cloudantCredentials;
	
	public CloudantDatabase() 
	{
		this.httpClient = null;
		
		
		/*
		 try {
           		String VCAP_SERVICES = System.getenv("VCAP_SERVICES");
			JSONObject vcap;
			vcap = (JSONObject) JSONObject.parse(VCAP_SERVICES);
			
			cloudant = (JSONArray) vcap.get("cloudantNoSQLDB");
			cloudantInstance = (JSONObject) cloudant.get(0);
			cloudantCredentials = (JSONObject) cloudantInstance.get("credentials");
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
		this.port = Config.CLOUDANT_PORT;
		this.host = (String) cloudantCredentials.get("host");
		this.username = (String) cloudantCredentials.get("username");
		this.password = (String) cloudantCredentials.get("password");
		this.name = Config.CLOUDANT_NAME;
		 */
	
		this.port = 443;
		this.host = "8adc5bca-fac0-42c8-817e-91ddb4465653-bluemix.cloudant.com";
		this.username = "8adc5bca-fac0-42c8-817e-91ddb4465653-bluemix";
		this.password = "ec03a278122209e4403d11c6d5247b3621e8348bd8f6b2e346630b13453b1798";
		
		this.name = Config.CLOUDANT_NAME;
		
		this.dbc = this.createDBConnector();
		
		
	}

	private CouchDbConnector createDBConnector() {
CouchDbInstance dbInstance = null;
		
		System.out.println("Creating CouchDB instance...");
		System.out.println(this.username);
		this.httpClient = new StdHttpClient.Builder().host(this.host)
		.port(this.port)
		.username(this.username)
		.password(this.password)
		.enableSSL(true)
		.relaxedSSLSettings(true)
		.build();

		dbInstance = new StdCouchDbInstance(this.httpClient);
		CouchDbConnector dbc = new StdCouchDbConnector(this.name, dbInstance);
		dbc.createDatabaseIfNotExists();
		return dbc;
	}

	private void closeDBConnector()
	{
		if (httpClient != null)
		{
			httpClient.shutdown();
		}
	}
	public static void main(String[] args) {
		
		CloudantDatabase cdatabase = new CloudantDatabase();
						
		NewScientist newscientist = cdatabase.getScientist("Abdul Kalam");
		
		//String groupName = "physics";
		
		//List<NewScientist> listScientist = cdatabase.getAllScientistsInGroup(groupName);
		
		System.out.println(cdatabase.prepareOutput(newscientist));
		
		/*for (NewScientist ns : listScientist) {
			System.out.println(ns.name);
			
			System.out.println(ns.bioinformation);
			
			System.out.println(ns.awardsReceived);
			
			System.out.println(ns.fieldsOfReseacrh);
			
			System.out.println(ns.image_url);
			
			System.out.println(ns.qualification);
		}*/
			
	}
	
public String prepareOutput(NewScientist s) {
		
		String text;
		
		String name =s.name;
		text = "The Bio information of "+s.name +" is" +s.bioinformation +". "+s.name+" qualifications are "+ s.qualification +". "+s.name+ "research interests are "+ s.researchInterest +". "+s.name+" fields of research are "+s.fieldsOfReseacrh+". "+s.name +" received awards "+s.awardsReceived +". "+"His attributes are "	;
		
		
		List<Trait> traitTesting = s.traits;
		HashMap<String, Double> hashMap = new HashMap<String, Double>();
		for (Trait trait : traitTesting) {
			String idValue = trait.traitName;
			
			double percen = trait.percent;
			
			hashMap.put(idValue, percen);
		}
		
		HashMap<String, Double> tttt = prepareTopList(hashMap);
		
		String kkkkk = "";
		for (Map.Entry<String, Double> mapp : tttt.entrySet()) {
			kkkkk = kkkkk+mapp.getKey() +", ";
		}
		
		System.out.println("KKKKKKKK:"+ kkkkk);
		
		List<Trait> traitsList = s.traits;
		if(traitsList != null){
		for (Trait trait : traitsList) {
			 text = text +" "+ trait.traitName;
		}
		}
		return text;
	}

	private HashMap<String, Double> prepareTopList(HashMap<String, Double> hashMap) {
	
		HashMap<String,Double> hhhh = hashMap;
		
		HashMap<String, Double> map = sortByValues(hhhh); 
	      System.out.println("After Sorting:");
	      Set set2 = map.entrySet();
	      Iterator iterator2 = set2.iterator();
	      while(iterator2.hasNext()) {
	           Map.Entry me2 = (Map.Entry)iterator2.next();
	           System.out.print(me2.getKey() + ": ");
	           System.out.println(me2.getValue());
	      }
	      
	      
	      HashMap<String, Double> hast = prepareTopAttr(map);
	      
	      return hast;
	  }

	  private HashMap<String, Double> prepareTopAttr(HashMap<String, Double> map) {
		
		  HashMap<String, Double> hhhhh = new HashMap<String, Double>();
		  
		  int count = map.size();
		  
		  int counter = 0;
		  for(Map.Entry<String, Double> ent: map.entrySet()){
			  if(counter > (count-5)){
				  hhhhh.put(ent.getKey(), ent.getValue());
				  
			  }
			  counter++;
		  }
		  
		return hhhhh;
	}

	private static HashMap<String, Double> sortByValues(HashMap<String, Double> map) { 
	       List list = new LinkedList(map.entrySet());
	       // Defined Custom Comparator here
	       Collections.sort(list, new Comparator() {
	            public int compare(Object o1, Object o2) {
	               return ((Comparable) ((Map.Entry) (o1)).getValue())
	                  .compareTo(((Map.Entry) (o2)).getValue());
	            }
	       });

	       // Here I am copying the sorted list in HashMap
	       // using LinkedHashMap to preserve the insertion order
	       HashMap sortedHashMap = new LinkedHashMap();
	       for (Iterator it = list.iterator(); it.hasNext();) {
	              Map.Entry entry = (Map.Entry) it.next();
	              sortedHashMap.put(entry.getKey(), entry.getValue());
	       } 
	       return sortedHashMap;
	 
}

	public void putScientist(NewScientist scientist) {
		
		HashMap<String, Object> data = new HashMap<String, Object>();
		String name = scientist.name.toUpperCase();
		
		String group = scientist.fieldsOfReseacrh.toUpperCase();
		data.put(Constants.ID_KEY, name);
		data.put(Constants.TYPE_KEY, NewScientist.class.getName());
		data.put(Constants.GROUP_KEY, group);
		//System.out.println(data.get(Constants.TYPE_KEY));
		data.put(Constants.JSON_KEY, JsonUtils.getJson(scientist));
		this.putItem(data);
	}

	private void putItem(HashMap<String, Object> data) {
		if (data == null) 
		{ 
			System.err.println("data cannot be null in putItem()"); 
			return;
		}
		String id = (String)data.get(Constants.ID_KEY);
		if (id == null)   
		{ 
			System.err.println("data must have an _id field."); 
			return;
		}
		if (this.dbc.contains(id)) 
		{ 
			System.err.println("Didn't putItem. _id=" + id + " already exists."); 
			return;
		}
		this.dbc.create(data);
		System.out.println("Put _id=" + id + " into the datastore."); 
		
	}
	
	/**
	 * 
	 * 
	 *  Get a Scientist Name from Cloudant using name as the unique id. 
	 * 
	 * **/
	public NewScientist getScientist(String name)
	{
		name = name.toUpperCase();
		@SuppressWarnings("unchecked")
		HashMap<String, Object> obj = this.dbc.get(HashMap.class, name);
		NewScientist scientist = JsonUtils.getPersonFromJson((String)obj.get(Constants.JSON_KEY));
		return scientist;
	}

	public List<NewScientist> getAllScientistsInGroup(String groupName) {
		
		List<NewScientist> scientists  = new ArrayList<NewScientist>();
		
		groupName = groupName.toUpperCase();
		
		CloudantDatabase cdatabase  =  new CloudantDatabase();
		List<String> docIds = dbc.getAllDocIds();
		
		for(String str: docIds){
			
			@SuppressWarnings("unchecked")
			HashMap<String, Object> obj = this.dbc.get(HashMap.class, str);
			if (/*obj.get(Constants.TYPE_KEY) != null && 
				obj.get(Constants.TYPE_KEY).equals(NewScientist.class.getName()) &&*/
				obj.get(Constants.GROUP_KEY) != null &&
				obj.get(Constants.GROUP_KEY).equals(groupName))
			{
				String json = (String)obj.get(Constants.JSON_KEY);
				NewScientist p = JsonUtils.getPersonFromJson(json);
				scientists.add(p);
		}
		
		
	}
		
		return scientists;
}
	
}
