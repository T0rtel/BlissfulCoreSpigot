package tortel.blissfulcorespigot.Commands


import net.md_5.bungee.api.chat.HoverEvent
import net.md_5.bungee.api.chat.TextComponent
import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import tortel.blissfulcorespigot.Main

class EmojisCommand : CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (sender !is Player) return false
        var Text = TextComponent("Emojis: ")
        val config = Main.instance?.config!!
        val emojis = config.getConfigurationSection("emojis")!!

        for (key in emojis.getKeys(false)){
            val value = config.getString("emojis.$key")

            val newtext = TextComponent(value + " ")
                newtext.hoverEvent = HoverEvent(HoverEvent.Action.SHOW_TEXT, arrayOf(TextComponent(*TextComponent.fromLegacyText(":${key}:"))))


            Text.addExtra(newtext)
        }
        sender.spigot().sendMessage(Text)
        //val emojiTable = Main.instance?.config!!.getStringList("emojis") //Functions.emojiModule.emojiTable
        /*
        for (thing in emojiTable){

            val newtext = TextComponent(thing.value + " ")
            newtext.hoverEvent = HoverEvent(HoverEvent.Action.SHOW_TEXT, arrayOf(TextComponent(*TextComponent.fromLegacyText(thing.key))))

            Text.addExtra(newtext)
        }
        sender.spigot().sendMessage(Text)

         */


        return false
    }
}