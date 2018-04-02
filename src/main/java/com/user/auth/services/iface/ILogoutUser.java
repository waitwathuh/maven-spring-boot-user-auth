package com.user.auth.services.iface;

import com.user.auth.model.request.LogoutRequest;
import com.user.auth.model.response.SuccessResponse;

public interface ILogoutUser
{
	public SuccessResponse logout( String userId, LogoutRequest model );
}