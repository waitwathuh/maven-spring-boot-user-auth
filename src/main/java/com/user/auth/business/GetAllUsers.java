package com.user.auth.business;

import java.util.ArrayList;
import java.util.List;

import com.user.auth.dao.iface.IGetSession;
import com.user.auth.dao.iface.IGetUsers;
import com.user.auth.entities.Users;
import com.user.auth.model.response.UserResponse;

public class GetAllUsers
{
	private IGetUsers iGetUsers;
	private IGetSession iGetSession;

	public GetAllUsers( IGetUsers iGetUsers, IGetSession iGetSession )
	{
		this.iGetUsers = iGetUsers;
		this.iGetSession = iGetSession;
	}

	public List< UserResponse > getAll( String token ) throws Exception
	{
		validateSession( token );
		return mapEntityToResponse( iGetUsers.getAllUsers() );
	}

	private void validateSession( String token ) throws Exception
	{
		iGetSession.getByToken( token );
	}

	private List< UserResponse > mapEntityToResponse( List< Users > userList )
	{
		List< UserResponse > response = new ArrayList< UserResponse >();

		for ( Users user : userList )
		{
			UserResponse userResponse = new UserResponse();
			userResponse.setId( user.getId() );
			userResponse.setPhone( user.getPhone() );
			userResponse.setUsername( user.getUsername() );
			response.add( userResponse );
		}

		return response;
	}
}