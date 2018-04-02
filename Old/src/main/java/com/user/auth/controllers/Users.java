package com.user.auth.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.user.auth.business.GetAllUsers;
import com.user.auth.dao.impl.GetSession;
import com.user.auth.dao.impl.GetUsers;
import com.user.auth.model.response.UserResponse;

@Controller
public class Users
{
	@RequestMapping( value = "/api/users/{uuid}", method = RequestMethod.GET )
	public @ResponseBody List< UserResponse > createUser( @PathVariable("uuid") String sessioToken )
	{
		try
		{
			return new GetAllUsers( new GetUsers(), new GetSession() ).getAll( sessioToken );
		}
		catch ( Exception ex )
		{
			return null;
		}
	}
}