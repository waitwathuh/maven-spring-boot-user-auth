package com.user.auth.dao.iface;

import com.user.auth.entities.Settings;

public interface ISettingsDAO
{
	public Settings getSettings();

	public void save( Settings settings );
}