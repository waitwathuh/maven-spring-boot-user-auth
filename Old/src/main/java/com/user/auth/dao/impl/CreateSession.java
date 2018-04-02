package com.user.auth.dao.impl;

import com.j256.ormlite.dao.Dao;
import com.user.auth.ConnectionFactory;
import com.user.auth.dao.iface.ICreateSession;
import com.user.auth.entities.Session;

public class CreateSession implements ICreateSession
{
	private static Dao< Session, Integer > sessionDao;

	public CreateSession()
	{
		sessionDao = ConnectionFactory.getSessionDao();
	}

	public Session save( Session session ) throws Exception
	{
		sessionDao.create( session );
		return getSessionByToken( session.getToken() );
	}

	private Session getSessionByToken( String token ) throws Exception
	{
		return sessionDao.queryForFirst(
				sessionDao.queryBuilder()
				.where()
				.eq( Session.NAME_FIELD_NAME, token )
				.prepare() );
	}
}