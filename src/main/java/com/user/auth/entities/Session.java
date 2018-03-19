package com.user.auth.entities;

import java.util.Date;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable( tableName = "sessions" )
public class Session
{
	public static final String NAME_FIELD_NAME = "token";
	public static final String USER_ID_FIELD_NAME = "user_id";

	@DatabaseField( generatedId = true )
	private int id;

	@DatabaseField( columnName = NAME_FIELD_NAME, canBeNull = false, unique = true )
	private String token;

	@DatabaseField( canBeNull = false )
	private Date creationDate;

	@DatabaseField( canBeNull = true )
	private Date updateDate;

	@DatabaseField(foreign = true, columnName = USER_ID_FIELD_NAME)
	private Users user;

	public Session()
	{
		creationDate = new Date();
		updateDate = new Date();
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

	public Date getUpdateDate()
	{
		return updateDate;
	}

	public void setUpdateDate( Date updateDate )
	{
		this.updateDate = updateDate;
	}

	public Users getUser()
	{
		return user;
	}

	public void setUser( Users user )
	{
		this.user = user;
	}
}