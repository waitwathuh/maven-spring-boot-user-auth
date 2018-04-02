package com.user.auth.services.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.user.auth.dao.iface.ISessionDAO;
import com.user.auth.dao.iface.ISettingsDAO;
import com.user.auth.dao.iface.IUsersDAO;
import com.user.auth.entities.Session;
import com.user.auth.entities.Users;
import com.user.auth.exceptions.InvalidSession;
import com.user.auth.exceptions.SessionExpired;
import com.user.auth.model.response.UserListResponse;
import com.user.auth.model.response.UserResponse;
import com.user.auth.services.iface.IGetAllUsers;

@Service
public class GetAllUsers implements IGetAllUsers
{
	@Autowired
	private IUsersDAO usersDAO;

	@Autowired
	private ISessionDAO sessionDAO;

	@Autowired
	private ISettingsDAO settingsDAO;

	public UserListResponse getAll( String sessionToken ) throws Exception
	{
		validateSession( sessionDAO.getByToken( sessionToken ) );
		return createResponse( usersDAO.getAllUsers() );
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

	private UserListResponse createResponse( List< Users > userList )
	{
		UserListResponse response = new UserListResponse();
		List< UserResponse > users = new ArrayList<>();

		for ( Users user : userList )
		{
			UserResponse u = new UserResponse();
			u.setId( user.getId() );
			u.setPhone( user.getPhone() );
			u.setUsername( user.getUsername() );
			users.add( u );
		}

		response.setUsers( users );
		response.setSuccess( true );
		response.setMessage( "" );
		return response;
	}
}