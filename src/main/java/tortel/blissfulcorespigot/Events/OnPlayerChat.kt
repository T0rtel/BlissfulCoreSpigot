package tortel.blissfulcorespigot.Events



import net.md_5.bungee.api.chat.ComponentBuilder
import net.md_5.bungee.api.chat.HoverEvent
import net.md_5.bungee.api.chat.TextComponent
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.AsyncPlayerChatEvent
import org.bukkit.scoreboard.Team
import tortel.blissfulcorespigot.Functions


class OnPlayerChat : Listener {
    @EventHandler
    fun onPlayerChatted(event: AsyncPlayerChatEvent) {
        val player = event.player

        //player.sendMessage("You Typed $MSG, with Format $Format, recipents $recipents")
       // if (!MSG.lowercase().contains(":")) return

       //val newMSG = EmojiUnicode.module.returnMSGwithUniCode(MSG, player)

        val newMSG = Functions.emojiModule.returnMSGwithUniCode(event)


        //var midpart = TextComponent(*TextComponent.fromLegacyText("yeh"))
        //midpart.hoverEvent = HoverEvent(HoverEvent.Action.SHOW_TEXT, arrayOf(TextComponent(*TextComponent.fromLegacyText("neh"))))

        //player.sendMessage(newMSG)
        // we need to get the first : in the emoji.
        /*
        val teamprefix = getteam(player)?.prefix
        val teamcolor = getteam(player)?.color
        val teamsuffix = getteam(player)?.suffix
        var playercomponent = TextComponent("${teamprefix}${teamcolor}${player.name}${teamsuffix}: ")

       playercomponent.hoverEvent = HoverEvent(HoverEvent.Action.SHOW_ITEM, ComponentBuilder("{name:${player.name} , type: Player , id: ${player.uniqueId}}").create())//BaseComponent("{name: ${player.name} , type: $player , id: ${player.uniqueId} }"))


         */
        //Bukkit.broadcastMessage("${newMSG}")
        //player.spigot().sendMessage(newMSG)

    }


}
