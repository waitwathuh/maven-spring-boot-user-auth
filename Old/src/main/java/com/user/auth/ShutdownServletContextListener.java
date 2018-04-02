package com.user.auth;

import java.io.IOException;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ShutdownServletContextListener implements ServletContextListener
{
	public void contextDestroyed( ServletContextEvent sce )
	{
		try
		{
			ConnectionFactory.getConnectionSource().close();
		}
		catch ( IOException e )
		{
		}
	}

	public void contextInitialized( ServletContextEvent arg0 )
	{
	}
}