package com.user.auth.dao.impl;

import com.j256.ormlite.dao.Dao;
import com.user.auth.ConnectionFactory;
import com.user.auth.dao.iface.IDeleteSession;
import com.user.auth.entities.Session;

public class DeleteSession implements IDeleteSession
{
	private static Dao< Session, Integer > sessionDao;

	public DeleteSession(  )
	{
		sessionDao = ConnectionFactory.getSessionDao();
	}

	public void delete( String userId, String sessionToken ) throws Exception
	{
		Session session = getSessionByToken( userId, sessionToken );System.out.println( "\n\n\n" + session.getUser().getUsername() );
		session.setUpdateDate( null );
		sessionDao.update( session );
	}

	private Session getSessionByToken( String userId, String token ) throws Exception
	{
		return sessionDao.queryForFirst(
				sessionDao.queryBuilder()
				.where()
				.eq( Session.USER_ID_FIELD_NAME, userId )
				.eq( Session.NAME_FIELD_NAME, token )
				.prepare() );
	}
}