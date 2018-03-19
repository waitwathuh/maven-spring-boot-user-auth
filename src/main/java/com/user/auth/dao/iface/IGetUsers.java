package com.user.auth.dao.iface;

import java.util.List;

import com.user.auth.entities.Users;

public interface IGetUsers
{
	public List< Users > getAllUsers() throws Exception;
}