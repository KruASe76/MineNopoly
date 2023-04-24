package me.kruase.minenopoly.commands

import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import org.bukkit.Material
import me.kruase.minenopoly.Minenopoly.Companion.userConfig
import me.kruase.minenopoly.Minenopoly.Companion.gameData
import me.kruase.minenopoly.Minenopoly.Companion.sendGameMessage
import me.kruase.minenopoly.MinenopolyStaticData
import me.kruase.minenopoly.util.*


// mp get <lang> <item> [<property-type> [street-color] <index|name>]
fun get(player: Player, args: Array<out String>) {
    if (!player.hasPluginPermission("get")) throw UnsupportedOperationException()

    if (args.size < 2) throw IllegalArgumentException()

    val gameData = gameData ?: throw IllegalStateException(
        userConfig.messages.error["game-not-started"] ?: "Error: game-not-started"
    )

    when (args[1]) {
        "property" -> {
            args.drop(2).joinToString(".").let {
                if (it !in MinenopolyStaticData.propertyTypes) throw IllegalArgumentException()
                if (gameData.propertiesInGame[it]!!) throw IllegalStateException(
                    userConfig.messages.error["property-already-in-game"] ?: "Error: property-already-in-game"
                )
                gameData.propertiesInGame[it] = true
                item(it, args[0])
            }
        }
        "house" -> {
            if (args.size != 2) throw IllegalArgumentException()
            if (gameData.housesInGame == MinenopolyStaticData.totalHouses) throw IllegalStateException(
                userConfig.messages.error["no-houses-left"] ?: "Error: no-houses-left"
            )
            gameData.housesInGame++
            item("house", args[0])
        }
        "hotel" -> {
            if (args.size != 2) throw IllegalArgumentException()
            if (gameData.hotelsInGame == MinenopolyStaticData.totalHotels) throw IllegalStateException(
                userConfig.messages.error["no-hotels-left"] ?: "Error: no-hotels-left"
            )
            gameData.hotelsInGame++
            item("hotel", args[0])
        }
        "chance" -> {
            if (args.size != 2) throw IllegalArgumentException()
            gameData.chancesInGame++
            item("chance", args[0])
        }
        "community_chest" -> {
            if (args.size != 2) throw IllegalArgumentException()
            gameData.communityChestsInGame++
            item("community_chest", args[0])
        }
        else -> throw IllegalArgumentException()
    }.let {
        gameData.bank.inventory.addItem(it)

        it.itemMeta!!.run {
            sendGameMessage(
                userConfig.messages.info["add-item"]
                    ?.replace("{player}", getColoredName(player.playerListName))
                    ?.replace("{item}", displayName)
            )

            if (persistentDataContainer.hasMark("house")) sendGameMessage(
                userConfig.messages.info["houses-left"]
                    ?.replace(
                        "{n}", getColoredName((MinenopolyStaticData.totalHouses - gameData.housesInGame).toString())
                    )
            )
            if (persistentDataContainer.hasMark("hotel")) sendGameMessage(
                userConfig.messages.info["hotels-left"]
                    ?.replace(
                        "{n}", getColoredName((MinenopolyStaticData.totalHotels - gameData.hotelsInGame).toString())
                    )
            )
        }
    }
}


fun item(name: String, lang: String): ItemStack {
    return when (name) {
        "chance" -> {ItemStack(Material.AIR)}
        "community_chest" -> {ItemStack(Material.AIR)}
        "house" -> {ItemStack(Material.AIR)}  // lang!
        "hotel" -> {ItemStack(Material.AIR)}  // lang!
        else -> {ItemStack(Material.AIR)}  // property type
    }
}
