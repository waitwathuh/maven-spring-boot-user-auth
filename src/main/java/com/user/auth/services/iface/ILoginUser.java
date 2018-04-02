package com.user.auth.services.iface;

import com.user.auth.model.request.LoginRequest;
import com.user.auth.model.response.UserLoginResponse;

public interface ILoginUser
{
	public UserLoginResponse login( LoginRequest model ) throws Exception;
}