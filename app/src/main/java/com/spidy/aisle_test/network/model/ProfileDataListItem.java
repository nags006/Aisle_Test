package com.spidy.aisle_test.network.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ProfileDataListItem{

	@SerializedName("preferences")
	private List<PreferencesItem> preferences;

	@SerializedName("question")
	private String question;

	@SerializedName("invitation_type")
	private String invitationType;

	public List<PreferencesItem> getPreferences(){
		return preferences;
	}

	public String getQuestion(){
		return question;
	}

	public String getInvitationType(){
		return invitationType;
	}
}