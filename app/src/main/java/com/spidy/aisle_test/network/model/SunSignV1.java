package com.spidy.aisle_test.network.model;

import com.google.gson.annotations.SerializedName;

public class SunSignV1{

	@SerializedName("name")
	private String name;

	@SerializedName("id")
	private int id;

	public String getName(){
		return name;
	}

	public int getId(){
		return id;
	}
}