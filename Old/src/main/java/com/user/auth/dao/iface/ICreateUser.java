package com.user.auth.dao.iface;

import com.user.auth.entities.Users;

public interface ICreateUser
{
	public Users save( Users user ) throws Exception;
}