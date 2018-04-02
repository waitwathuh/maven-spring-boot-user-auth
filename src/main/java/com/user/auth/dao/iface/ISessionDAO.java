package com.user.auth.dao.iface;

import com.user.auth.entities.Session;

public interface ISessionDAO
{
	public void save( Session session );

	public Session getByToken( String token );
}
