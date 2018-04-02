package com.user.auth.model.response;

import java.util.List;

public class UserListResponse
{
	private boolean success;
	private String message;
	private List< UserResponse > users;

	public boolean isSuccess()
	{
		return success;
	}

	public void setSuccess( boolean success )
	{
		this.success = success;
	}

	public String getMessage()
	{
		return message;
	}

	public void setMessage( String message )
	{
		this.message = message;
	}

	public List< UserResponse > getUsers()
	{
		return users;
	}

	public void setUsers( List< UserResponse > users )
	{
		this.users = users;
	}
}