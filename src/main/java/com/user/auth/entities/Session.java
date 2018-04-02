package com.user.auth.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Session
{
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	private int id;

	private String token;
	private Date creationDate;
	private Date lastTransactionDate;
	private int userId;
	private boolean expired = false;

	public Session()
	{

	}

	public int getId()
	{
		return id;
	}

	public void setId( int id )
	{
		this.id = id;
	}

	public String getToken()
	{
		return token;
	}

	public void setToken( String token )
	{
		this.token = token;
	}

	public Date getCreationDate()
	{
		return creationDate;
	}

	public void setCreationDate( Date creationDate )
	{
		this.creationDate = creationDate;
	}

	public Date getLastTransactionDate()
	{
		return lastTransactionDate;
	}

	public void setLastTransactionDate( Date lastTransactionDate )
	{
		this.lastTransactionDate = lastTransactionDate;
	}

	public int getUserId()
	{
		return userId;
	}

	public void setUserId( int userId )
	{
		this.userId = userId;
	}

	public boolean isExpired()
	{
		return expired;
	}

	public void setExpired( boolean expired )
	{
		this.expired = expired;
	}
}