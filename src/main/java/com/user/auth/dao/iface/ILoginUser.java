package com.user.auth.dao.iface;

import com.user.auth.entities.Users;

public interface ILoginUser
{
	public Users getUserByUsername( String username ) throws Exception;
}