package com.user.auth.business;

import org.mindrot.jbcrypt.BCrypt;

import com.user.auth.dao.iface.ICreateUser;
import com.user.auth.dao.iface.IGetSession;
import com.user.auth.entities.Users;
import com.user.auth.model.request.AddUserRequest;

public class UserCreation
{
	private ICreateUser iCreateUser;
	private IGetSession iGetSession;

	public UserCreation( ICreateUser iCreateUser, IGetSession iGetSession )
	{
		this.iCreateUser = iCreateUser;
		this.iGetSession = iGetSession;
	}

	public Users create( AddUserRequest addUser ) throws Exception
	{
		validateSession( addUser.getToken() );
		return iCreateUser.save( mapRequestToEntity( addUser ) );
	}

	private void validateSession( String token ) throws Exception
	{
		iGetSession.getByToken( token );
	}

	private Users mapRequestToEntity( AddUserRequest addUser )
	{
		Users user = new Users();
		user.setPassword( BCrypt.hashpw( addUser.getPassword(), BCrypt.gensalt() ) );
		user.setPhone( addUser.getPhone() );
		user.setUsername( addUser.getUsername() );
		return user;
	}
}