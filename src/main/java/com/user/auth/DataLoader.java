package com.user.auth;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.user.auth.dao.iface.ISettingsDAO;
import com.user.auth.dao.iface.IUsersDAO;
import com.user.auth.entities.Settings;
import com.user.auth.entities.Users;

@Component
public class DataLoader implements ApplicationRunner
{
	@Autowired
	private IUsersDAO usersDAO;

	@Autowired
	private ISettingsDAO settingsDAO;

	public void run( ApplicationArguments args )
	{
		addAdminUser();
		addSettings();
	}

	private void addAdminUser()
	{
		Users adminUser = new Users();
		adminUser.setUsername( "admin" );
		adminUser.setPassword( BCrypt.hashpw( "password", BCrypt.gensalt() ) );
		adminUser.setPhone( "1023" );
		usersDAO.save( adminUser );
	}

	private void addSettings()
	{
		Settings settings = new Settings();
		settings.setId( 1 );
		settings.setLoginHistoryMin( 5 );
		settings.setSessionExpireMin( 3 );
		settingsDAO.save( settings );
	}
}