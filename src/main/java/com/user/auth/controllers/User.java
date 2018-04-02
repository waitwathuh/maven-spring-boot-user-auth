package com.user.auth.controllers;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.user.auth.exceptions.IncorrectPassword;
import com.user.auth.exceptions.InvalidSession;
import com.user.auth.exceptions.SessionExpired;
import com.user.auth.model.request.AddUserRequest;
import com.user.auth.model.request.LoginRequest;
import com.user.auth.model.request.LogoutRequest;
import com.user.auth.model.response.SuccessResponse;
import com.user.auth.model.response.UserLoginResponse;
import com.user.auth.services.iface.ICreateUser;
import com.user.auth.services.iface.ILoginUser;
import com.user.auth.services.iface.ILogoutUser;

@Controller
public class User
{
	@Autowired
	private ICreateUser createUser;

	@Autowired
	private ILoginUser loginUser;

	@Autowired
	private ILogoutUser logoutUser;

	@RequestMapping( value = "/api/user/add", method = RequestMethod.PUT )
	public @ResponseBody SuccessResponse createUser( @RequestBody AddUserRequest jsonString )
	{
		try
		{
			return createUser.create( jsonString );
		}
		catch ( InvalidSession | SessionExpired | NoResultException | EmptyResultDataAccessException ex )
		{
			SuccessResponse response = new SuccessResponse();
			response.setSuccess( false );
			response.setMessage( "Invalid session." );
			return response;
		}
		catch ( Exception ex )
		{
			ex.printStackTrace();

			SuccessResponse response = new SuccessResponse();
			response.setSuccess( false );
			response.setMessage( "Internal server error." );
			return response;
		}
	}

	@RequestMapping( value = "/api/user/login", method = RequestMethod.POST )
	public @ResponseBody UserLoginResponse login( @RequestBody LoginRequest jsonString )
	{
		try
		{
			return loginUser.login( jsonString );
		}
		catch ( IncorrectPassword | EmptyResultDataAccessException ex )
		{
			UserLoginResponse response = new UserLoginResponse();
			response.setSuccess( false );
			response.setMessage( "Incorrect login details." );
			return response;
		}
		catch ( Exception ex )
		{
			ex.printStackTrace();

			UserLoginResponse response = new UserLoginResponse();
			response.setSuccess( false );
			response.setMessage( "Internal server error." );
			return response;
		}
	}

	@RequestMapping( value = "/api/user/logout/{id}", method = RequestMethod.POST )
	public @ResponseBody SuccessResponse logout( @PathVariable( "id" ) String userId, @RequestBody LogoutRequest jsonString )
	{
		try
		{
			return logoutUser.logout( userId, jsonString );
		}
		catch ( NoResultException | EmptyResultDataAccessException ex )
		{
			SuccessResponse response = new SuccessResponse();
			response.setMessage( "" );
			response.setSuccess( true );
			return response;
		}
		catch ( Exception ex )
		{
			ex.printStackTrace();

			SuccessResponse response = new SuccessResponse();
			response.setMessage( "Internal server error." );
			response.setSuccess( false );
			return response;
		}
	}
}