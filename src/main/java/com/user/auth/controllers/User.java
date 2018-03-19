package com.user.auth.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.user.auth.business.UserCreation;
import com.user.auth.business.UserLogin;
import com.user.auth.business.UserLogout;
import com.user.auth.dao.impl.CreateSession;
import com.user.auth.dao.impl.CreateUser;
import com.user.auth.dao.impl.DeleteSession;
import com.user.auth.dao.impl.GetSession;
import com.user.auth.dao.impl.LoginUser;
import com.user.auth.entities.Users;
import com.user.auth.model.request.AddUserRequest;
import com.user.auth.model.request.LoginRequest;
import com.user.auth.model.request.LogoutRequest;
import com.user.auth.model.response.SuccessResponse;
import com.user.auth.model.response.UserLoginResponse;

@Controller
public class User
{
	@RequestMapping( value = "/api/user/add", method = RequestMethod.PUT )
	public @ResponseBody SuccessResponse createUser( @RequestBody AddUserRequest jsonString )
	{
		SuccessResponse response = new SuccessResponse();

		try
		{
			Users u = new UserCreation( new CreateUser(), new GetSession() ).create( jsonString );

			response.setSuccess( true );
			response.setMessage( "User saved successfully. User id:" + u.getId() );
		}
		catch ( Exception e )
		{
			response.setSuccess( false );
			response.setMessage( "Internal Server Error." );
		}

		return response;
	}

	@RequestMapping( value = "/api/user/login", method = RequestMethod.POST )
	public @ResponseBody UserLoginResponse login( @RequestBody LoginRequest jsonString )
	{
		UserLoginResponse response = new UserLoginResponse();

		try
		{
			response = new UserLogin( new LoginUser(), new CreateSession() ).login( jsonString );
			response.setSuccess( true );
			response.setMessage( "" );
		}
		catch ( Exception e )
		{
			response.setId( -1 );
			response.setSuccess( false );
			response.setMessage( "Internal Server Error." );
		}

		return response;
	}

	@RequestMapping( value = "/api/user/logout/{id}", method = RequestMethod.POST )
	public @ResponseBody SuccessResponse logout( @PathVariable("id") String userId, @RequestBody LogoutRequest jsonString )
	{
		SuccessResponse response = new SuccessResponse();

		try
		{
			new UserLogout( new DeleteSession() ).logout( userId, jsonString );;
		}
		catch ( Exception ex )
		{
		}

		response.setSuccess( true );
		response.setMessage( "" );
		return response;
	}
}