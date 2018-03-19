package com.user.auth.dao.impl;

import com.j256.ormlite.dao.Dao;
import com.user.auth.ConnectionFactory;
import com.user.auth.dao.iface.ICreateUser;
import com.user.auth.entities.Users;

public class CreateUser implements ICreateUser
{
	private static Dao< Users, Integer > usersDao;

	public CreateUser()
	{
		usersDao = ConnectionFactory.getUsersDao();
	}

	public Users save( Users user ) throws Exception
	{
		usersDao.createOrUpdate( user );
		return getUserByUsername( user.getUsername() );
	}

	private Users getUserByUsername( String username ) throws Exception
	{
		return usersDao.queryForFirst(
				usersDao.queryBuilder()
					.where()
					.eq( Users.NAME_FIELD_NAME, username )
					.prepare() );
	}
}