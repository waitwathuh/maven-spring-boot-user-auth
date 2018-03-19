package com.user.auth.dao.impl;

import com.j256.ormlite.dao.Dao;
import com.user.auth.ConnectionFactory;
import com.user.auth.dao.iface.ILoginUser;
import com.user.auth.entities.Users;

public class LoginUser implements ILoginUser
{
	private static Dao< Users, Integer > usersDao;

	public LoginUser()
	{
		usersDao = ConnectionFactory.getUsersDao();
	}

	public Users getUserByUsername( String username ) throws Exception
	{
		return usersDao.queryForFirst(
				usersDao.queryBuilder()
					.where()
					.eq( Users.NAME_FIELD_NAME, username )
					.prepare() );
	}
}