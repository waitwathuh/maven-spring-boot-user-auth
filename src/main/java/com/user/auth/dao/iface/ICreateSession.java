package com.user.auth.dao.iface;

import com.user.auth.entities.Session;

public interface ICreateSession
{
	public Session save( Session session ) throws Exception;
}