package com.user.auth.business;

import java.util.Date;
import java.util.UUID;

import org.mindrot.jbcrypt.BCrypt;

import com.user.auth.dao.iface.ICreateSession;
import com.user.auth.dao.iface.ILoginUser;
import com.user.auth.entities.Session;
import com.user.auth.entities.Users;
import com.user.auth.model.request.LoginRequest;
import com.user.auth.model.response.UserLoginResponse;

public class UserLogin
{
	private ILoginUser iLoginUser;
	private ICreateSession iCreateSession;

	public UserLogin( ILoginUser iLoginUser, ICreateSession iCreateSession )
	{
		this.iLoginUser = iLoginUser;
		this.iCreateSession = iCreateSession;
	}

	public UserLoginResponse login( LoginRequest login ) throws Exception
	{
		Users user = iLoginUser.getUserByUsername( login.getUsername() );
		validateUserPassword( login.getPassword(), user.getPassword() );

		UserLoginResponse response = new UserLoginResponse();
		response.setId( user.getId() );
		response.setToken( createSession( user ) );
		return response;
	}

	private void validateUserPassword( String loginPassword, String dbPassword ) throws Exception
	{
		if ( passwordCorrect( loginPassword, dbPassword ) == false )
		{
			throw new Exception();
		}
	}

	private boolean passwordCorrect( String password, String dbPassword )
	{
		boolean match = false;

		try
		{
			match = BCrypt.checkpw( password, dbPassword );
		}
		catch ( Exception ex )
		{
		}

		return match;
	}

	private String createSession( Users user ) throws Exception
	{
		Session session = new Session();
		session.setCreationDate( new Date() );
		session.setUpdateDate( new Date() );
		session.setToken( generateUUID() );
		session.setUser( user );
		iCreateSession.save( session );
		
		return session.getToken();
	}

	private String generateUUID()
    {
    	return UUID.randomUUID().toString();
    }
}