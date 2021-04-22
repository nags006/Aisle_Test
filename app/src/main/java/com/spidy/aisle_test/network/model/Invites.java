package com.spidy.aisle_test.network.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Invites{

	@SerializedName("pending_invitations_count")
	private int pendingInvitationsCount;

	@SerializedName("profiles")
	private List<ProfilesItem> profiles;

	@SerializedName("totalPages")
	private int totalPages;

	public int getPendingInvitationsCount(){
		return pendingInvitationsCount;
	}

	public List<ProfilesItem> getProfiles(){
		return profiles;
	}

	public int getTotalPages(){
		return totalPages;
	}
}