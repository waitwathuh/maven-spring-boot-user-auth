package com.user.auth;

import org.mindrot.jbcrypt.BCrypt;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.user.auth.entities.Session;
import com.user.auth.entities.Users;

public class ConnectionFactory
{
	private final static String DATABASE_URL = "jdbc:h2:mem:account";

	private static ConnectionSource connectionSource;

	private static Dao< Users, Integer > usersDao;
	private static Dao< Session, Integer > sessionDao;

	public ConnectionFactory() throws Exception
	{
		connectionSource = new JdbcConnectionSource( DATABASE_URL );
		createDao();
		createTable();
		createAdminUser();
	}

	private void createDao() throws Exception
	{
		usersDao = DaoManager.createDao( connectionSource, Users.class );
		sessionDao = DaoManager.createDao( connectionSource, Session.class );
	}

	private void createTable() throws Exception
	{
		TableUtils.createTable( connectionSource, Users.class );
		TableUtils.createTable( connectionSource, Session.class );
	}

	private void createAdminUser() throws Exception
	{
		Users user = new Users();
		user.setPassword( BCrypt.hashpw( "password", BCrypt.gensalt() ) );
		user.setPhone( "9" );
		user.setUsername( "admin" );
		usersDao.createOrUpdate( user );
	}

	public static Dao< Users, Integer > getUsersDao()
	{
		return usersDao;
	}

	public static ConnectionSource getConnectionSource()
	{
		return connectionSource;
	}

	public static Dao< Session, Integer > getSessionDao()
	{
		return sessionDao;
	}
}