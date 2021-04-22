package com.spidy.aisle_test.network.model;

import com.google.gson.annotations.SerializedName;

public class IndustryV1{

	@SerializedName("name")
	private String name;

	@SerializedName("id")
	private int id;

	@SerializedName("preference_only")
	private boolean preferenceOnly;

	public String getName(){
		return name;
	}

	public int getId(){
		return id;
	}

	public boolean isPreferenceOnly(){
		return preferenceOnly;
	}
}