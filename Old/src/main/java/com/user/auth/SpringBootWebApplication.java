package com.user.auth;

import javax.servlet.ServletContextListener;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;

import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;

import com.sun.istack.internal.NotNull;

@SpringBootApplication
public class SpringBootWebApplication extends SpringBootServletInitializer
{
	public static void main( String[] args ) throws Exception
	{
		new ConnectionFactory();
		SpringApplication.run( SpringBootWebApplication.class, args );
	}

	@NotNull
	@Bean
	ServletListenerRegistrationBean< ServletContextListener > myServletListener()
	{
		ServletListenerRegistrationBean< ServletContextListener > srb = new ServletListenerRegistrationBean< ServletContextListener >();
		srb.setListener( new ShutdownServletContextListener() );
		return srb;
	}
}