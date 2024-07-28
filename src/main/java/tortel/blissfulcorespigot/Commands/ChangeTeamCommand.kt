package tortel.blissfulcorespigot.Commands

import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.OfflinePlayer
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.command.TabCompleter
import org.bukkit.entity.Player

class ChangeTeamCommand : CommandExecutor, TabCompleter {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if(sender.hasPermission("op")) {
            if(sender is Player) {
                val player = sender.player!!
                if (args[0] == "remove") {
                    if (args[1] == "player") { // for player
                        for (offlineplr : OfflinePlayer in Bukkit.getOfflinePlayers()) {
                            if (!offlineplr.isOnline) {
                                if (offlineplr.name == args[1]) {
                                    val team = Bukkit.getScoreboardManager()?.mainScoreboard?.getPlayerTeam(offlineplr)
                                    team?.removePlayer(offlineplr)
                                    player.sendMessage("Removing ${offlineplr} from ${team?.name}")
                                }
                            }else{
                                player.sendMessage("${ChatColor.RED} Player is Online!")
                            }

                        }
                    } // for teams
                    if (args[1] == "team"){
                        for (team in Bukkit.getScoreboardManager()?.mainScoreboard?.teams!!) {
                            if (team.name == args[1]) {
                                for (offlineplr : OfflinePlayer in Bukkit.getOfflinePlayers()) {
                                    if (!offlineplr.isOnline && team.hasPlayer(offlineplr)) {
                                        team.removePlayer(offlineplr)
                                    }else{
                                        player.sendMessage("${ChatColor.RED} Player is Online!")
                                    }

                                }
                            }
                        }

                    }

                }
            }
        }

        return false
    }

    override fun onTabComplete(sender: CommandSender, command: Command, label: String, args: Array<out String>): MutableList<String>? {
        val tabComplete: MutableList<String> = ArrayList()
        if (args.size == 0) {
            tabComplete.add("remove")
        }
        if (args.size == 1) {
            tabComplete.add("team")
        }

        return tabComplete
    }

}