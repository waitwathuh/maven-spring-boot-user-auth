package com.user.auth.entities;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable( tableName = "users" )
public class Users
{
	public static final String NAME_FIELD_NAME = "username";

	@DatabaseField( generatedId = true )
	private int id;

	@DatabaseField( columnName = NAME_FIELD_NAME, canBeNull = false, unique = true )
	private String username;

	@DatabaseField( canBeNull = false )
	private String password;

	@DatabaseField( canBeNull = true )
	private String phone;

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
}