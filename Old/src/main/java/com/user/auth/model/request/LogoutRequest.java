package com.user.auth.model.request;

public class LogoutRequest
{
	private String token;

	public String getToken()
	{
		return token;
	}

	public void setToken( String token )
	{
		this.token = token;
	}
}