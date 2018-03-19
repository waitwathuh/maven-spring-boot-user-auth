package com.user.auth.dao.impl;

import java.util.List;

import com.j256.ormlite.dao.Dao;
import com.user.auth.ConnectionFactory;
import com.user.auth.dao.iface.IGetUsers;
import com.user.auth.entities.Users;

public class GetUsers implements IGetUsers
{
	private static Dao< Users, Integer > usersDao;

	public GetUsers()
	{
		usersDao = ConnectionFactory.getUsersDao();
	}

	public List< Users > getAllUsers() throws Exception
	{
		return usersDao.queryForAll();
	}
}