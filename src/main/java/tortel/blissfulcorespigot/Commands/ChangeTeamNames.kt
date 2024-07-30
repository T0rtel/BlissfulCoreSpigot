package tortel.blissfulcorespigot.Commands

import net.md_5.bungee.api.ChatColor
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import tortel.blissfulcorespigot.Functions
import tortel.blissfulcorespigot.Main

class ChangeTeamNames : CommandExecutor {
    override fun onCommand(sender: CommandSender, cmd: Command, p2: String, arrays: Array<out String>): Boolean {
        if (!sender.isOp) return false
        val config = Main.instance!!.config
        println("${config.getInt("teamnames")}")
        /*
        if (config.getInt("teamnames") == 0){
            config.set("teamnames", 2)
            config.save("${Main.dataFolderDir}\\config.yml")
            //Main.instance!!.saveDefaultConfig()
        }

         */

        if (config.getInt("teamnames") == 0){
            println("its 0")
            config.set("teamnames", 1)
            config.save("${Main.dataFolderDir}\\config.yml")

            Functions.detectTeamNames()

            return false
        }

        if (config.getInt("teamnames") == 1){
            println("its 1")
            config.set("teamnames", 0)
            config.save("${Main.dataFolderDir}\\config.yml")

            Functions.detectTeamNames()

            return false
        }

        println("${config.getInt("teamnames")}")


        return false
    }
}