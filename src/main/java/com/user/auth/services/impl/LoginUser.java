package com.user.auth.services.impl;

import java.util.Date;
import java.util.UUID;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.user.auth.dao.iface.ISessionDAO;
import com.user.auth.dao.iface.IUsersDAO;
import com.user.auth.entities.Session;
import com.user.auth.entities.Users;
import com.user.auth.exceptions.IncorrectPassword;
import com.user.auth.model.request.LoginRequest;
import com.user.auth.model.response.UserLoginResponse;
import com.user.auth.services.iface.ILoginUser;

@Service
public class LoginUser implements ILoginUser
{
	@Autowired
	private IUsersDAO usersDAO;

	@Autowired
	private ISessionDAO sessionDAO;

	public UserLoginResponse login( LoginRequest model ) throws Exception
	{
		Users user = usersDAO.getUserByUsername( model.getUsername() );
		validateUserPassword( model.getPassword(), user.getPassword() );
		String sessionToken = createAndSaveSession( user );
		return createSuccessResponse( user.getId(), sessionToken );
	}

	private void validateUserPassword( String loginPassword, String dbPassword ) throws Exception
	{
		if ( passwordCorrect( loginPassword, dbPassword ) == false )
		{
			throw new IncorrectPassword();
		}
	}

	private boolean passwordCorrect( String password, String dbPassword )
	{
		return BCrypt.checkpw( password, dbPassword );
	}

	private String createAndSaveSession( Users user )
	{
		String token = generateUUID();

		Session session = new Session();
		session.setCreationDate( new Date() );
		session.setLastTransactionDate( new Date() );
		session.setToken( token );
		session.setUserId( user.getId() );
		sessionDAO.save( session );
		return token;
	}

	private String generateUUID()
	{
		return UUID.randomUUID().toString();
	}

	private UserLoginResponse createSuccessResponse( int userId, String sessionToken )
	{
		UserLoginResponse response = new UserLoginResponse();
		response.setId( userId );
		response.setMessage( "" );
		response.setSuccess( true );
		response.setToken( sessionToken );
		return response;
	}
}