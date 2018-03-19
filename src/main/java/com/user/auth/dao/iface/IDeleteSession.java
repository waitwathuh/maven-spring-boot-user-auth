package com.user.auth.dao.iface;

public interface IDeleteSession
{
	public void delete( String userId, String sessionToken ) throws Exception;
}