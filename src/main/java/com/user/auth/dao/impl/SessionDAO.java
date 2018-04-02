package com.user.auth.dao.impl;

import javax.persistence.TypedQuery;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.user.auth.dao.iface.ISessionDAO;
import com.user.auth.entities.Session;

@Repository
@Transactional
public class SessionDAO implements ISessionDAO
{
	@Autowired
	private SessionFactory sessionFactory;

	public void save( Session session )
	{
		sessionFactory.getCurrentSession().saveOrUpdate( session );
	}

	public Session getByToken( String sessionToken )
	{
		@SuppressWarnings( "unchecked" )
		TypedQuery< Session > query = sessionFactory.getCurrentSession().createQuery( "from Session where token = :token" );
		query.setParameter( "token", sessionToken );
		return query.getSingleResult();
	}
}