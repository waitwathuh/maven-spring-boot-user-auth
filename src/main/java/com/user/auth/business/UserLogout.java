package com.user.auth.business;

import com.user.auth.dao.iface.IDeleteSession;
import com.user.auth.model.request.LogoutRequest;

public class UserLogout
{
	private IDeleteSession iDeleteSession;

	public UserLogout( IDeleteSession iDeleteSession )
	{
		this.iDeleteSession = iDeleteSession;
	}

	public void logout( String userId, LogoutRequest logout ) throws Exception
	{
		iDeleteSession.delete( userId, logout.getToken() );
	}
}