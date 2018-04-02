package com.user.auth.controllers;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.user.auth.exceptions.InvalidSession;
import com.user.auth.exceptions.SessionExpired;
import com.user.auth.model.response.UserListResponse;
import com.user.auth.services.iface.IGetAllActiveUsers;
import com.user.auth.services.iface.IGetAllUsers;

@Controller
public class Users
{
	@Autowired
	private IGetAllUsers getAllUsers;

	@Autowired
	private IGetAllActiveUsers getAllActiveUsers;

	@RequestMapping( value = "/api/users/{uuid}", method = RequestMethod.GET )
	public @ResponseBody UserListResponse getAllUsers( @PathVariable("uuid") String sessionToken )
	{
		try
		{
			return getAllUsers.getAll( sessionToken );
		}
		catch ( InvalidSession | SessionExpired | NoResultException | EmptyResultDataAccessException ex )
		{
			UserListResponse response = new UserListResponse();
			response.setSuccess( false );
			response.setMessage( "Invalid session." );
			return response;
		}
		catch ( Exception ex )
		{
			ex.printStackTrace();

			UserListResponse response = new UserListResponse();
			response.setSuccess( false );
			response.setMessage( "Internal server error." );
			return response;
		}
	}

	@RequestMapping( value = "/api/users/active/{uuid}", method = RequestMethod.GET )
	public @ResponseBody UserListResponse getAllActiveUsers( @PathVariable("uuid") String sessionToken )
	{
		try
		{
			return getAllActiveUsers.getAll( sessionToken );
		}
		catch ( InvalidSession | SessionExpired | NoResultException | EmptyResultDataAccessException ex )
		{
			UserListResponse response = new UserListResponse();
			response.setSuccess( false );
			response.setMessage( "Invalid session." );
			return response;
		}
		catch ( Exception ex )
		{
			ex.printStackTrace();

			UserListResponse response = new UserListResponse();
			response.setSuccess( false );
			response.setMessage( "Internal server error." );
			return response;
		}
	}
}