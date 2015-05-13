package scientists.controller;

import java.util.List;

import scientists.model.AddScientistRequest;
import scientists.model.NewScientist;
import scientists.model.Scientist;

import com.google.gson.Gson;

public class JsonUtils {

	public static String getJson(NewScientist scientist)
	{
		Gson gson = new Gson();
		String json = gson.toJson(scientist);
		return json;
	}
	
	public static String getFollowup(NewScientist scientist)
	{
		Gson gson = new Gson();
		String json = gson.toJson(scientist.getFollowUp());
		return json;
	}
	
	public static String getListPersonJson(List<NewScientist> people)
	{
		Gson gson = new Gson();
		String json = gson.toJson(people);
		return json;
	}
	
	public static NewScientist getPersonFromJson(String json)
	{
		Gson gson = new Gson();
		NewScientist scientist = gson.fromJson(json, NewScientist.class);
		return scientist;
	}
	
	public static AddScientistRequest getAPRFromJson(String json)
	{
		Gson gson = new Gson();
		AddScientistRequest p = gson.fromJson(json, AddScientistRequest.class);
		return p;
	}

}
