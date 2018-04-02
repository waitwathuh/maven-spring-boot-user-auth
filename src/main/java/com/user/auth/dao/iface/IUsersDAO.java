package com.user.auth.dao.iface;

import java.util.Date;
import java.util.List;

import com.user.auth.entities.Users;

public interface IUsersDAO
{
	public void save( Users user );

	public Users getUserByUsername( String username );

	public Users getUserById( int userId );

	public List< Users > getAllUsers();

	public List< Users > getAllActiveUsers( Date date );
}