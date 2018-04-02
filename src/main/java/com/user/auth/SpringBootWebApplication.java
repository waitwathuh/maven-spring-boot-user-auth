package com.user.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication
@EnableAutoConfiguration( exclude = { HibernateJpaAutoConfiguration.class } )
public class SpringBootWebApplication extends SpringBootServletInitializer
{
	public static void main( String[] args ) throws Exception
	{
		SpringApplication.run( SpringBootWebApplication.class, args );
	}
}