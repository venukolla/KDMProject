package scientists;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import scientists.db.CloudantDatabase;
import scientists.model.NewScientist;
import scientists.model.Scientist;

/*import com.ibm.personafusion.controller.SearchController;
import com.ibm.personafusion.db.CloudantClient;
import com.ibm.personafusion.model.Person;*/

public class Engine 
{
	List<NewScientist> scientists;
	
	public Engine(List<NewScientist> people)
	{
		//this.people = people;
		
		this.scientists = new ArrayList<NewScientist>();
		for(NewScientist p : people)
		{
			this.scientists.add(new NewScientist(p.name, p.bioinformation,p.researchInterest,p.qualification, p.image_url, p.traits, p.awardsReceived,p.fieldsOfReseacrh));
		}
	}
	
	List<NewScientist> query(NewScientist p)
	{
		this.setQueryPerson(p);
		this.setDistanceWeights(.5);
		Collections.sort(this.scientists);
		this.scientists.remove(0);
		this.convertScores(this.scientists);
		return this.scientists;
	}
	
	public List<NewScientist> query(String personName)
	{
		personName = personName.toUpperCase();
		
		
		//get person with the person name

		// Here's the changed new code
		CloudantDatabase cc = new CloudantDatabase();
		for (NewScientist p: scientists) {
			NewScientist queriedPer = cc.getScientist(personName);
			if(queriedPer == null) System.out.println("queriedPerson is null");
			p.setQueryPerson(queriedPer);
//			p.setDistanceWeights(.5,  0, .5);
		}
		
		
		//this.setQueryPerson(this.getPersonGivenName(personName));
		//System.out.println("Set person's name: " + this.people.get(0).name);
		//this.setDistanceWeights(.5, 0 , .5);
		
		Collections.sort(this.scientists);
		
		// this.people.remove(0);
		
		this.convertScores(this.scientists);
		return this.scientists;
	}
	
	NewScientist getPersonGivenName(String personName)
	{
		for(NewScientist p : this.scientists)
			if(p.name.equals(personName))
				return p;
		return null;
	}
	
	void convertScores(List<NewScientist> people)
	{
		double minDist = Double.MAX_VALUE;
		for(NewScientist p : people)
		{
			if(minDist > p.distToQueryPerson)
				minDist = p.distToQueryPerson;
		}
		
		for(NewScientist p : people)
		{
			p.distToQueryPerson = (int) (minDist*100.0 / (p.distToQueryPerson));
		}
	}
	
	void setQueryPerson(NewScientist p)
	{
		scientists.get(0).setQueryPerson(p);
	}
	
	void setDistanceWeights(double weightTraits)
	{
		scientists.get(0).setDistanceWeights(weightTraits);
	}
	
	
}
