package com.user.auth.views;

import org.springframework.web.bind.annotation.RequestMapping;

public class User
{
	@RequestMapping( "/" )
	public String index()
	{
		return "index.html";
	}
}