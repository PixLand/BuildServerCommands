package com.magicsweet.pixland.buildserver;

import com.magicsweet.lib.magiclib.MagicLib;
import dev.jorel.commandapi.CommandAPI;
import dev.jorel.commandapi.CommandAPIConfig;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
public
class BuildServerUtils extends JavaPlugin {
	@Getter static BuildServerUtils instance;
	
	@Override
	public void onLoad() {
		instance = this;
		
		CommandAPI.onLoad(new CommandAPIConfig().silentLogs(false).verboseOutput(false));
	}
	
	@Override
	public void onEnable() {
		getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
		
		CommandAPI.onEnable(this);
		MagicLib.enable(this);
	}
}
