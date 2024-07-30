package tortel.blissfulcorespigot.Commands

import net.md_5.bungee.api.ChatColor
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import tortel.blissfulcorespigot.Functions
import tortel.blissfulcorespigot.Main

class ChangeTeamNames : CommandExecutor {
    override fun onCommand(sender: CommandSender, cmd: Command, p2: String, arrays: Array<out String>): Boolean {
        val config = Main.instance!!.config

        if (config.getInt("teamnames") == 1){
            config.set("teamnames", 2)

            Functions.detectTeamNames()

            Main.instance!!.saveDefaultConfig()
        }

        if (config.getInt("teamnames") == 2){
            config.set("teamnames", 1)

            Functions.detectTeamNames()

            Main.instance!!.saveDefaultConfig()
        }


        return false
    }
}