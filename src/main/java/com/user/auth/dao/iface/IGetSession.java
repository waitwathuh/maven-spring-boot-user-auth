package com.user.auth.dao.iface;

import com.user.auth.entities.Session;

public interface IGetSession
{
	public Session getByToken( String token ) throws Exception;
}