package tortel.blissfulcorespigot



import net.md_5.bungee.api.ChatColor
import net.md_5.bungee.api.chat.BaseComponent
import net.md_5.bungee.api.chat.ComponentBuilder
import net.md_5.bungee.api.chat.HoverEvent
import org.bukkit.Bukkit
import org.bukkit.OfflinePlayer
import org.bukkit.entity.Player
import org.bukkit.event.player.AsyncPlayerChatEvent
import org.bukkit.scoreboard.Team
import net.md_5.bungee.api.chat.TextComponent
import net.md_5.bungee.api.chat.hover.content.Content
import java.awt.Color

object Functions {
    data class Points(val points: Int, val name: String)


    val teamnames = mutableMapOf<String, String>(
            ".ruby" to "Ruby",
            ".amber" to "Amber",
            ".topaz" to "Topaz",
            ".jade" to "Jade",
            ".aquamarine" to "Aquamarine",
            ".diamond" to "Diamond",
            ".sapphire" to "Sapphire",
            ".amethyst" to "Amethyst"
    )

    val teamcolors = mutableMapOf<ChatColor, String>(
        ChatColor.RED to "Ruby",
        ChatColor.GOLD to "Amber",
        ChatColor.YELLOW to "Topaz",
        ChatColor.GREEN to "Jade",
        ChatColor.DARK_AQUA to "Aquamarine",
        ChatColor.AQUA to "Diamond",
        ChatColor.BLUE to "Sapphire",
        ChatColor.LIGHT_PURPLE to "Amethyst"
    )


    var records = listOf<Points>()

    var currentplacement = 0

    var endmsg = listOf<String>()

    fun GetScores() { // gets all entities and adds their values to the list

        val manager = Bukkit.getScoreboardManager()
        val scoreboard = manager?.mainScoreboard

        records = listOf<Points>()
        //d0c22018-32ac-4f04-b89c-d4e7ec6fb4d0

         //       .amethyst : 1abd2272-767c-4bfb-8352-e909ff993e70
        //.sapphire : e011af2b-9db6-4546-bece-a76042eb61b3
        //.diamond : 789f708f-e2a7-4110-b1f6-cb7bb63eaeb3
        //.aquamarine : 1ebfd7b1-0311-46ad-948c-495fe444b54f
        //.jade : f7968fb8-3cc2-4b28-9818-a2447243b6fc
        //.topaz : 8fca6a83-9baa-4e09-b1b1-b91f17dd52bb
        //.amber : 28a058c4-197a-4d8d-968e-2b0dc867e532
        //.ruby : d0c22018-32ac-4f04-b89c-d4e7ec6fb4d0

        //Blissful Palace Bukkit.getWorld("world")!!.entities
        for (entity in Bukkit.getWorlds().get(0).entities) {
            for (teamname in teamnames) {

                if (entity.scoreboardTags.contains(teamname.key)) {

                    val score = scoreboard?.getObjective("TokensThisEvent")?.getScore("${entity.uniqueId}")?.score

                    records += Points( score as Int , teamname.value)
                }
            }
        }


        records = records.sortedByDescending { it.points }

    }

    fun returnprefix(placement : Int): String {
        if (currentplacement == 1){
            return "st"
        }
        if (currentplacement == 2){
            return "nd"
        }
        if (currentplacement == 3){
            return "rd"
        }
        return "th"
    }

    fun returncolor(name: String): ChatColor {
        for (color in teamcolors) {
            if (name == color.value) {
                return color.key
            }
        }
        return ChatColor.WHITE
    }

    fun color(HexString : String) : ChatColor{
        return ChatColor.of(HexString)
    }

    fun plrisinteam(plr : Player) : Boolean{
        for (team in Bukkit.getScoreboardManager()?.mainScoreboard?.teams!!){
            if (team.hasPlayer(plr)) {
                return true
            }
        }
        return false
    }

    fun getteam(player: Player) : Team? {
        return player.scoreboard.getPlayerTeam(Bukkit.getOfflinePlayer(player.name))!!
    }


    fun returnisonlineColor(plr: OfflinePlayer, teamname: String):ChatColor {
        var plrteamcolor: ChatColor = ChatColor.WHITE
        if (plr.isOnline) {

            for (color in teamcolors) {
                if (teamname == color.value) {
                    plrteamcolor = color.key
                }
            }
        }else{
            return ChatColor.DARK_GRAY
        }
        return plrteamcolor
    }

    fun returnteamplayerstostring(teamname : String) : String{
        var returnedteamString = ""
        var index = 0
        for (plr in Bukkit.getOnlinePlayers()) {
            if (!plrisinteam(plr)) return ""
            if (getteam(plr)?.name == teamname) {
                index += 1
                if (index < 4) {
                    returnedteamString += "${returnisonlineColor(plr, teamname)}[${plr.name}]     "
                }else{
                    returnedteamString += "${returnisonlineColor(plr, teamname)}[${plr.name}]"
                }
            }
        }

        if (index == 0) {
            returnedteamString += "${ChatColor.DARK_GRAY}[null]     [null]     [null]     [null]"
        }
        if (index == 1) {
            returnedteamString += "${ChatColor.DARK_GRAY}[null]     [null]     [null]"
        }
        if (index == 2) {
            returnedteamString += "${ChatColor.DARK_GRAY}[null]     [null]"
        }
        if (index == 3) {
            returnedteamString += "${ChatColor.DARK_GRAY}[null]"
        }
        return returnedteamString
    }

    fun getColorCodeFromString(colorString: String): String {
        // Decode the color string to a Color object
        val color = Color.decode(colorString)

        // Convert the Color object to a hex string
        val hexColor = String.format("#%06X", 0xFFFFFF and color.rgb)

        return hexColor
    }

    fun returnTitleString() :String {
        val config = Main.instance?.config!!
        /*
        val titlemap = listOf(
                mapOf("text" to "~ ", "color" to "#ffff00"),
                mapOf("text" to "B", "color" to "#fdf30b"),
                mapOf("text" to "l", "color" to "#fbe816"),
                mapOf("text" to "i", "color" to "#f9dd21"),
                mapOf("text" to "s", "color" to "#f7d22c"),
                mapOf("text" to "s", "color" to "#f5c737"),
                mapOf("text" to "f", "color" to "#f3bc42"),
                mapOf("text" to "u", "color" to "#f1b14d"),
                mapOf("text" to "l ", "color" to "#efa658"),
                mapOf("text" to "C", "color" to "#ed9b63"),
                mapOf("text" to "h", "color" to "#eb906e"),
                mapOf("text" to "a", "color" to "#e98579"),
                mapOf("text" to "m", "color" to "#e87985"),
                mapOf("text" to "p", "color" to "#e66e90"),
                mapOf("text" to "i", "color" to "#e4639b"),
                mapOf("text" to "o", "color" to "#e258a6"),
                mapOf("text" to "n", "color" to "#e04db1"),
                mapOf("text" to "s", "color" to "#de42bc"),
                mapOf("text" to "h", "color" to "#dc37c7"),
                mapOf("text" to "i", "color" to "#da2cd2"),
                mapOf("text" to "p ", "color" to "#d821dd"),
                mapOf("text" to "1", "color" to "#d616e8"),
                mapOf("text" to "2 ", "color" to "#d40bf3"),
                mapOf("text" to "~\n", "color" to "#d300ff")
        )

         */
        var newTitle : String = ""


        //val title = Main.instance?.config!!.getConfigurationSection("title")!!
        config.getMapList("title").forEach {
            //Bukkit.broadcastMessage("${it.keys} ${it.entries} ${it.values}")

            val hexColor = getColorCodeFromString(it.values.last() as String)
            newTitle = "$newTitle${ChatColor.of(hexColor)}${it.values.first()}"

        }
        return newTitle
        /*
        for (data in titlemap) {
            val text = data["text"]
            val color = data["color"]
            //Bukkit.broadcastMessage("${data} ${title}")


            // Do something with text and color
            val hexColor = getColorCodeFromString(color as String)
            newTitle = "$newTitle${ChatColor.of(hexColor)}$text"
        }
        return newTitle

         */
    }

    val finalTitle = returnTitleString()

    fun ReturnPlacements(){
        endmsg = listOf<String>()
        currentplacement = 0

        for (element in records) {
            currentplacement += 1

            endmsg += "${returncolor(element.name)}$currentplacement${returnprefix(currentplacement)}: ${element.name} With ${element.points} Points"
        }

        //~ Blissful Championship 10 ~ §x§0§0§f§f§f§f~ §x§0§c§f§3§f§fB§x§1§7§e§8§f§fl§x§2§3§d§c§f§fi§x§2§e§d§1§f§fs§x§3§a§c§5§f§fs§x§4§6§b§9§f§ff§x§5§1§a§e§f§fu§x§5§d§a§2§f§fl §x§6§8§9§7§f§fC§x§7§4§8§b§f§fh§x§8§0§7§f§f§fa§x§8§b§7§4§f§fm§x§9§7§6§8§f§fp§x§a§2§5§d§f§fi§x§a§e§5§1§f§fo§x§b§9§4§6§f§fn§x§c§5§3§a§f§fs§x§d§1§2§e§f§fh§x§d§c§2§3§f§fi§x§e§8§1§7§f§fp §x§f§3§0§c§f§f6 §x§f§f§0§0§f§f
        for (plr in Bukkit.getOnlinePlayers()) {

            plr.setPlayerListHeaderFooter("$finalTitle\n" +
                    "${ChatColor.WHITE}--------------------------------------------------------\n" +
                    "${endmsg[0]} \n" +
                    "${returnteamplayerstostring(records[0].name)} \n \n" +
                    "${endmsg[1]} \n" +
                    "${returnteamplayerstostring(records[1].name)} \n \n" +
                    "${endmsg[2]} \n" +
                    "${returnteamplayerstostring(records[2].name)} \n \n" +
                    "${endmsg[3]} \n" +
                    "${returnteamplayerstostring(records[3].name)} \n \n" +
                    "${endmsg[4]} \n" +
                    "${returnteamplayerstostring(records[4].name)} \n \n" +
                    "${endmsg[5]} \n" +
                    "${returnteamplayerstostring(records[5].name)} \n \n" +
                    "${endmsg[6]} \n" +
                    "${returnteamplayerstostring(records[6].name)} \n \n" +
                    "${endmsg[7]} \n" +
                    "${returnteamplayerstostring(records[7].name)} \n \n" +
                    "${ChatColor.WHITE}--------------------------------------------------------\n", "")
        }

    }

    object emojiModule {
        /*
        val emojiTable = mapOf(
            //NOVALY
            ":happy:" to "\uEF00",
            ":laugh:" to "\uEF01",
            ":sob:" to "\uEF02",
            ":cool:" to "\uEF03",
            ":angry:" to "\uEF04",
            ":monocle:" to "\uEF05",
            ":skull:" to "\uEF07",
            ":pog:" to "\uEF08",
            ":heart:" to "\uEF09",
        )

         */

        fun setupPlayerComponent(player : Player) : TextComponent{
            val team = getteam(player)
            val teamprefix = team?.prefix
            val teamcolor = team?.color
            val teamsuffix = team?.suffix
            var playercomponent = TextComponent("${teamprefix}${teamcolor}${player.name}${teamsuffix}${org.bukkit.ChatColor.WHITE}: ")

            playercomponent.hoverEvent = HoverEvent(HoverEvent.Action.SHOW_ITEM, ComponentBuilder("{name: A Blissful Champion , type: Player , id: ${player.uniqueId}}").create())
            return playercomponent
        }

        fun returnMSGwithUniCode(event : AsyncPlayerChatEvent): Any {
            event.isCancelled = true
            val config = Main.instance?.config!!
            val plr = event.player
            val stringMsg = event.message

            val plrComponent  = setupPlayerComponent(plr)
            val newMsg = TextComponent()

            val resultMsg = TextComponent()
            resultMsg.addExtra(plrComponent)

            if (!stringMsg.contains(":")) {

                resultMsg.addExtra(stringMsg)

                Bukkit.getOnlinePlayers().forEach { it.spigot().sendMessage(resultMsg) }
                return resultMsg
            }
            val words = stringMsg.split(" ").toMutableList()
            val emojis = config.getConfigurationSection("emojis")!!

            for (Index in words.indices){
                val word = words[Index]
                var isAppend = false
                for (key in emojis.getKeys(false)){
                    val value = config.getString("emojis.$key")
                    if (word.lowercase() == ":$key:"){
                        val emoji = TextComponent("$value ")
                        emoji.hoverEvent = HoverEvent(HoverEvent.Action.SHOW_TEXT, arrayOf(TextComponent(*TextComponent.fromLegacyText(word))))

                        newMsg.addExtra(emoji)
                        isAppend = true
                    }
                }
                if (isAppend == false){

                    newMsg.addExtra("$word ")

                }

            }

            resultMsg.addExtra(newMsg)


            //plrComponent.addExtra("${org.bukkit.ChatColor.WHITE}${newMsg}")

            Bukkit.getOnlinePlayers().forEach { it.spigot().sendMessage(resultMsg) }
            return resultMsg


            /*
            if (stringMsg.contains(":")) {
                val emojis = Main.instance?.config!!.getStringList("emojis")
                val words = MSG.split(" ")
                val output = mutableListOf<String>()

                val chatComponents = TextComponent()
                for (word in words) {
                    if (emojiTable.containsKey(word)) {
                        output.add(emojiTable[word]!!)
                        val textcomponent = TextComponent(emojiTable[word]!! + " ")
                        textcomponent.hoverEvent = HoverEvent(HoverEvent.Action.SHOW_TEXT, arrayOf(TextComponent(*TextComponent.fromLegacyText(word))))

                        chatComponents.addExtra(textcomponent)
                    } else {
                        output.add(word)
                        val textcomponent = TextComponent(word + " ")

                        chatComponents.addExtra(textcomponent)
                    }
                }

                //return output.joinToString(" ")
                return chatComponents


            }
            return TextComponent(MSG)

             */


        }
    }




}