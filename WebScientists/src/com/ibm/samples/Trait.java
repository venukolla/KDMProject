package com.ibm.samples;

public class Trait {

	public String traitName;
	public double percent;
	
	public Trait(String traitName, double percent)
	{
		this.traitName = traitName;
		this.percent = percent;
	}
	
	public String toString()
	{
		return this.traitName + " , " + this.percent;
	}
}
