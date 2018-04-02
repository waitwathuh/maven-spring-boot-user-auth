package com.user.auth.dao.impl;

import javax.persistence.TypedQuery;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.user.auth.dao.iface.ISettingsDAO;
import com.user.auth.entities.Settings;

@Repository
@Transactional
public class SettingsDAO implements ISettingsDAO
{
	@Autowired
	private SessionFactory sessionFactory;

	public void save( Settings settings )
	{
		sessionFactory.getCurrentSession().save( settings );
	}

	@Override
	public Settings getSettings()
	{
		@SuppressWarnings( "unchecked" )
		TypedQuery< Settings > query = sessionFactory.getCurrentSession().createQuery( "from Settings where id = 1" );
		return query.getSingleResult();
	}
}