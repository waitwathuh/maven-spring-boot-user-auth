package com.user.auth.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Users
{
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	private int id;

	private String username;
	private String password;
	private String phone;
	private int createdBy;

	public Users()
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

	public String getUsername()
	{
		return username;
	}

	public void setUsername( String username )
	{
		this.username = username;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword( String password )
	{
		this.password = password;
	}

	public String getPhone()
	{
		return phone;
	}

	public void setPhone( String phone )
	{
		this.phone = phone;
	}

	public int getCreatedBy()
	{
		return createdBy;
	}

	public void setCreatedBy( int createdBy )
	{
		this.createdBy = createdBy;
	}
}