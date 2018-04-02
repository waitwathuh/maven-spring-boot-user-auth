package com.user.auth.services.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.user.auth.dao.iface.ISessionDAO;
import com.user.auth.entities.Session;
import com.user.auth.model.request.LogoutRequest;
import com.user.auth.model.response.SuccessResponse;
import com.user.auth.services.iface.ILogoutUser;

@Service
public class LogoutUser implements ILogoutUser
{
	@Autowired
	private ISessionDAO sessionDAO;

	public SuccessResponse logout( String userId, LogoutRequest model )
	{
		updateSession( sessionDAO.getByToken( model.getToken() ) );
		return createSuccessResponse();
	}

	private void updateSession( Session session )
	{
		session.setExpired( true );
		session.setLastTransactionDate( new Date() );
		sessionDAO.save( session );
	}

	private SuccessResponse createSuccessResponse()
	{
		SuccessResponse response = new SuccessResponse();
		response.setMessage( "" );
		response.setSuccess( true );
		return response;
	}
}