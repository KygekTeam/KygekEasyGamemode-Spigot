package org.kygekteam.java.kygekeasygamemode;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    private static final String PREFIX = ChatColor.GREEN + "[KygekEasyGamemode] ";

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        switch(cmd.getName().toLowerCase()) {
            case "gmds":
                this.changeGamemode(sender, "gmds", args);
                break;
            case "gmdc":
                this.changeGamemode(sender, "gmdc", args);
                break;
            case "gmda":
                this.changeGamemode(sender, "gmda", args);
                break;
            case "gmdsp":
                this.changeGamemode(sender, "gmdsp", args);
        }
        return true;
    }

    private void changeGamemode(CommandSender sender, String cmd, String[] args) {
        if (!sender.hasPermission("kygekeasygmd." + cmd)) {
            sender.sendMessage(PREFIX + ChatColor.RED + "You do not have permission to use this command");
            return;
        }

        if (args.length < 1) {
            if (!(sender instanceof Player)) {
                sender.sendMessage(PREFIX + ChatColor.WHITE + "Usage: /" + cmd + " <player>");
            } else {
                String gamemode = this.setGamemode((Player) sender, cmd);
                sender.sendMessage(PREFIX + ChatColor.YELLOW + "Successfully changed your gamemode to " + gamemode);
            }
            return;
        }

        Player player = this.getServer().getPlayer(args[0]);
        if (player == null) {
            sender.sendMessage(PREFIX + ChatColor.RED + "Player was not found");
            return;
        }

        String gamemode = this.setGamemode(player, cmd);
        sender.sendMessage(PREFIX + ChatColor.YELLOW + "Successfully changed " + player.getName() + "'s gamemode to " + gamemode);
        player.sendMessage(PREFIX + ChatColor.YELLOW + "Your gamemode has been changed to " + gamemode + " by " + sender.getName());
    }

    private String setGamemode(Player player, String cmd) {
        switch (cmd) {
            case "gmds":
                player.setGameMode(GameMode.SURVIVAL);
                return "Survival";
            case "gmdc":
                player.setGameMode(GameMode.CREATIVE);
                return "Creative";
            case "gmda":
                player.setGameMode(GameMode.ADVENTURE);
                return "Adventure";
            case "gmdsp":
                player.setGameMode(GameMode.SPECTATOR);
                return "Spectator";
            default:
                return "";
        }
    }
}
