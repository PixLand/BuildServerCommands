package com.magicsweet.pixland.buildserver.command;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import com.magicsweet.lib.magiclib.annotations.LoadAtStartup;
import static com.magicsweet.lib.magiclib.color.Colorizer.format;
import com.magicsweet.pixland.buildserver.BuildServerUtils;
import dev.jorel.commandapi.CommandAPI;
import dev.jorel.commandapi.annotations.Alias;
import dev.jorel.commandapi.annotations.Command;
import dev.jorel.commandapi.annotations.Default;
import dev.jorel.commandapi.annotations.arguments.AStringArgument;
import java.util.concurrent.TimeUnit;
import org.bukkit.entity.Player;

@LoadAtStartup
@Command("leave")
@Alias({"hub", "lobby"})
public class LeaveCommand {
	
	public LeaveCommand() {
		CommandAPI.registerCommand(getClass());
	}
	
	@Default
	public static void run(Player sender, @AStringArgument String server) {
		if (!server.startsWith("hub")) {
			sender.sendMessage(format("&7Нет ну я конечно всё понимаю, но на другие сервера кроме хабов этой командой ты переходить &cне можешь&7."));
			return;
		}
		sender.sendMessage(format("&aПодключаю тебя к серверу " + server + "..."));
		ByteArrayDataOutput out = ByteStreams.newDataOutput();
		out.writeUTF("Connect");
		out.writeUTF(server);
		
		sender.sendPluginMessage(BuildServerUtils.getInstance(), "BungeeCord", out.toByteArray());
	}
	
	@Default
	public static void run(Player sender) {
		run(sender, "hub-1");
	}
}
