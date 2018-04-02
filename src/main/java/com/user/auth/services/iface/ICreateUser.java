package com.user.auth.services.iface;

import com.user.auth.model.request.AddUserRequest;
import com.user.auth.model.response.SuccessResponse;

public interface ICreateUser
{
	public SuccessResponse create( AddUserRequest model ) throws Exception;
}