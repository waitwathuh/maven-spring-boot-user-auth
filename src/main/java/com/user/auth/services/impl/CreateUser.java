package com.user.auth.services.impl;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.user.auth.dao.iface.ISessionDAO;
import com.user.auth.dao.iface.ISettingsDAO;
import com.user.auth.dao.iface.IUsersDAO;
import com.user.auth.entities.Session;
import com.user.auth.entities.Users;
import com.user.auth.exceptions.InvalidSession;
import com.user.auth.exceptions.SessionExpired;
import com.user.auth.model.request.AddUserRequest;
import com.user.auth.model.response.SuccessResponse;
import com.user.auth.services.iface.ICreateUser;

@Service
public class CreateUser implements ICreateUser
{
	@Autowired
	private IUsersDAO usersDAO;

	@Autowired
	private ISessionDAO sessionDAO;

	@Autowired
	private ISettingsDAO settingsDAO;

	public SuccessResponse create( AddUserRequest model ) throws Exception
	{
		Session session = sessionDAO.getByToken( model.getToken() );
		validateSession( session );
		createUser( model, session );
		return createSuccessResponse();
	}

	private void validateSession( Session session ) throws Exception
	{
		if ( session.isExpired() )
		{
			throw new InvalidSession();
		}
		else if ( sessionTimedOut( session ) )
		{
			session.setExpired( true );
			sessionDAO.save( session );
			throw new SessionExpired();
		}
		else
		{
			session.setLastTransactionDate( new Date() );
			sessionDAO.save( session );
		}
	}

	private boolean sessionTimedOut( Session session )
	{
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime( session.getLastTransactionDate() );
		cal1.add( Calendar.MINUTE, settingsDAO.getSettings().getSessionExpireMin() );

		Calendar cal2 = Calendar.getInstance();
		cal2.setTime( new Date() );

		if ( cal1.before( cal2 ) )
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	private void createUser( AddUserRequest model, Session session )
	{
		Users user = new Users();
		user.setPassword( hashPassword( model.getPassword() ) );
		user.setPhone( model.getPhone() );
		user.setUsername( model.getUsername() );
		user.setCreatedBy( session.getUserId() );
		usersDAO.save( user );
	}

	private String hashPassword( String plainPassword )
	{
		return new BCryptPasswordEncoder().encode( plainPassword );
	}

	private SuccessResponse createSuccessResponse()
	{
		SuccessResponse response = new SuccessResponse();
		response.setSuccess( true );
		response.setMessage( "" );
		return response;
	}
}