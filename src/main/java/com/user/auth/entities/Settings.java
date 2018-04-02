package com.user.auth.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Settings
{
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	private int id;

	private int sessionExpireMin;
	private int loginHistoryMin;

	public int getId()
	{
		return id;
	}

	public void setId( int id )
	{
		this.id = id;
	}

	public int getSessionExpireMin()
	{
		return sessionExpireMin;
	}

	public void setSessionExpireMin( int sessionExpireMin )
	{
		this.sessionExpireMin = sessionExpireMin;
	}

	public int getLoginHistoryMin()
	{
		return loginHistoryMin;
	}

	public void setLoginHistoryMin( int loginHistoryMin )
	{
		this.loginHistoryMin = loginHistoryMin;
	}
}