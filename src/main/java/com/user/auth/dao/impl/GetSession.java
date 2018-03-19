package com.user.auth.dao.impl;

import com.j256.ormlite.dao.Dao;
import com.user.auth.ConnectionFactory;
import com.user.auth.dao.iface.IGetSession;
import com.user.auth.entities.Session;

public class GetSession implements IGetSession
{
	private static Dao< Session, Integer > sessionDao;

	public GetSession()
	{
		sessionDao = ConnectionFactory.getSessionDao();
	}

	public Session getByToken( String token ) throws Exception
	{
		return sessionDao.queryForFirst(
				sessionDao.queryBuilder()
				.where()
				.eq( Session.NAME_FIELD_NAME, token )
				.prepare() );
	}
}