package com.spidy.aisle_test.network.model;

import com.google.gson.annotations.SerializedName;

public class ResponseProfile{

	@SerializedName("invites")
	private Invites invites;

	@SerializedName("likes")
	private Likes likes;

	public Invites getInvites(){
		return invites;
	}

	public Likes getLikes(){
		return likes;
	}
}