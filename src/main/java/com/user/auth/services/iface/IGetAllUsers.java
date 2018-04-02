package com.user.auth.services.iface;

import com.user.auth.model.response.UserListResponse;

public interface IGetAllUsers
{
	public UserListResponse getAll( String sessionToken ) throws Exception;
}