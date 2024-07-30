package tortel.blissfulcorespigot

import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.scheduler.BukkitRunnable
import tortel.blissfulcorespigot.Commands.ChangeTeamCommand
import tortel.blissfulcorespigot.Commands.ChangeTeamNames
import tortel.blissfulcorespigot.Commands.EmojisCommand
import tortel.blissfulcorespigot.Events.OnPlayerChat
import java.io.File

class Main : JavaPlugin() {
    val pluginmanager = Bukkit.getPluginManager()
    companion object {
        var instance: Main? = null
            private set
        var dataFolderDir: File = File("")
            private set
    }

    override fun onEnable() {
        instance = this
        dataFolderDir = dataFolder

        setupTab()

        registerCommands()
        registerEvents()
        setupConfigsOnEnable()
    }
    override fun onDisable(){
        setupConfigsOnDisable()

        logger.info("Disabled Plugin.")
    }

    private fun setupTab(){
        Functions.detectTeamNames()
        val bukkitRunnable = object: BukkitRunnable() { // runs every second
            override fun run() {
                Functions.GetScores()

                Functions.ReturnPlacements()
            }
        }.runTaskTimer(this, 1, 40) // every 2 seconds.
    }

    private fun registerEvents(){
        pluginmanager.registerEvents(OnPlayerChat(), this)
    }

    private fun registerCommands(){
        //getCommand("changeofflineplayerteam")?.setExecutor(ChangeTeamCommand())
        getCommand("emojis")?.setExecutor(EmojisCommand())
        getCommand("toggleteamnames")?.setExecutor(ChangeTeamNames())
    }


    private fun setupConfigsOnEnable() {
        saveConfig()

        logger.info("Configs Setup!")
    }
    private fun setupConfigsOnDisable() {
        //saveConfig()

        logger.info("Configs Saved! ")
    }

}
