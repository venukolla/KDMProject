package scientists.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.ibm.samples.Trait;

public class NewScientist implements Comparable<NewScientist> {

	static double weightReasearchInterest;
	
	public String name ;
	
	public String bioinformation;
	public String researchInterest;
	
	public String qualification; 
	
	static NewScientist queryPerson;
	
	public String image_url;
	
	//This will be the list of answers from the q & a of the person
	//index 0 response to question 1, index 1 response to question 2 etc...
	public List<String> qaResponses;
	public double distToQueryPerson;
	
	public List<Trait> traits;
	
	public String awardsReceived;
	
	public String fieldsOfReseacrh;
	
	public NewScientist() {
		
	}
	
	public NewScientist(String name, String bioinformation, String researchInterest, String qulification, String url, List<Trait> traitsList, String awardsReceived , String fieldsOfResearch){
		
		this.name = name;
		this.bioinformation = bioinformation;
		this.researchInterest = researchInterest;
		this.qualification = qulification;
		this.image_url = url;
		this.qaResponses = new ArrayList<String>();
		this.traits = traitsList;
		this.awardsReceived = awardsReceived;
		this.fieldsOfReseacrh = fieldsOfResearch;
	}
	
	@Override
	public int compareTo(NewScientist other) {

		
			return 0;
	
	}
	
	public List<FollowUp> getFollowUp() {
		return Collections.singletonList(new FollowUp(name, image_url));
	}

	public void setQueryPerson(NewScientist p) {
		this.queryPerson = p;
		
	}

	public void setDistanceWeights(double weightTraits) {
		this.weightReasearchInterest = weightTraits;
		
	}
	
	/*double getDistanceToQueryPerson()
	{
		
		double distance = 0, distanceReseachInterests = 0;
		
		for(int i=0; i<this.queryPerson.researchInterest.size(); i++)
		{
			String queryResearchInterest = this.queryPerson.researchInterest.get(i).researchInterests;
			double queryResearchPercent = this.queryPerson.researchInterest.get(i).percentage;
			distanceReseachInterests += this.getReserachInterestDistance(queryResearchInterest	, queryResearchPercent);
		}
		
		
		
		distance = weightReasearchInterest * distanceReseachInterests;
		this.distToQueryPerson = distance;
		return distance;
	}

	public double getReserachInterestDistance(String queryResearchInterest, double queryResearchPercent) {
		double distance = 1;//worst case if the trait does not exist return max value 1
		for(int i=0; i<this.researchInterest.size(); i++)
		{
			if(queryResearchInterest.equals(this.researchInterest.get(i).researchInterests))
			{
				double tempDist = (queryResearchPercent/100.0) - (this.researchInterest.get(i).percentage/100.0);
				distance = Math.pow(tempDist, 2);
				break;//found trait distance break loop
			}
		}
		return distance;
		
	}
*/
}
