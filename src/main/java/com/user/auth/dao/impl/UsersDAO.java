package com.user.auth.dao.impl;

import java.util.Date;
import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.user.auth.dao.iface.IUsersDAO;
import com.user.auth.entities.Users;

@Repository
@Transactional
public class UsersDAO implements IUsersDAO
{
	@Autowired
	private SessionFactory sessionFactory;

	public void save( Users user )
	{
		sessionFactory.getCurrentSession().save( user );
	}

	public Users getUserByUsername( String username )
	{
		@SuppressWarnings( "unchecked" )
		TypedQuery< Users > query = sessionFactory.getCurrentSession().createQuery( "from Users where username = :username" );
		query.setParameter( "username", username );
		return query.getSingleResult();
	}

	public List< Users > getAllUsers()
	{
		@SuppressWarnings( "unchecked" )
		TypedQuery< Users > query = sessionFactory.getCurrentSession().createQuery( "from Users" );
		return query.getResultList();
	}

	@Override
	public Users getUserById( int userId )
	{
		@SuppressWarnings( "unchecked" )
		TypedQuery< Users > query = sessionFactory.getCurrentSession().createQuery( "from Users where id = :id" );
		query.setParameter( "id", userId );
		return query.getSingleResult();
	}

	@Override
	public List< Users > getAllActiveUsers( Date date )
	{
		@SuppressWarnings( "unchecked" )
		TypedQuery< Users > query = sessionFactory.getCurrentSession().createQuery(
				"select u from Session s " +
				"left join Users u on s.id = u.id " +
				"where s.expired = false and s.creationDate > :date" );
		query.setParameter( "date", date );
		return query.getResultList();
	}
}