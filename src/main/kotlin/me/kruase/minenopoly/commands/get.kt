package me.kruase.minenopoly.commands

import me.kruase.minenopoly.LPT
import me.kruase.minenopoly.MSD
import me.kruase.minenopoly.Minenopoly.Companion.gameData
import me.kruase.minenopoly.Minenopoly.Companion.sendGameMessage
import me.kruase.minenopoly.Minenopoly.Companion.userConfig
import me.kruase.minenopoly.util.*
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.PotionMeta
import org.bukkit.potion.PotionData
import org.bukkit.potion.PotionType
import net.md_5.bungee.api.ChatColor as CC


// mp get <loc> <item> [<property-type> [street-color] <index | name (for utility)>]
fun get(player: Player, args: Array<out String>) {
    if (!player.hasPluginPermission("get")) throw UnsupportedOperationException()

    if (args.size < 2 || args[0] !in listOf("us", "uk", "ru")) throw IllegalArgumentException()

    val gameData = gameData ?: throw IllegalStateException(
        userConfig.messages.error["game-not-started"] ?: "Error: game-not-started"
    )


    when (args[1]) {
        "property" -> {
            args.drop(2).joinToString(".").let {
                if (it !in MSD.propertyTypes) throw IllegalArgumentException()
                if (gameData.propertiesInGame[it]!!) throw IllegalStateException(
                    userConfig.messages.error["property-already-in-game"] ?: "Error: property-already-in-game"
                )
                gameData.propertiesInGame[it] = true
                item(it, args[0])
            }
        }
        "house" -> {
            if (args.size != 2) throw IllegalArgumentException()
            if (gameData.housesInGame == MSD.totalHouses) throw IllegalStateException(
                userConfig.messages.error["no-houses-left"] ?: "Error: no-houses-left"
            )
            gameData.housesInGame++
            item("house", args[0])
        }
        "hotel" -> {
            if (args.size != 2) throw IllegalArgumentException()
            if (gameData.hotelsInGame == MSD.totalHotels) throw IllegalStateException(
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
                    ?.replace("{player}", coloredName(player.playerListName))
                    ?.replace(
                        "{item}",
                        displayName +
                                if (args[1] in listOf("chance", "community_chest"))
                                    "${CC.RESET}\n" + lore!!.joinToString("${CC.RESET}\n")
                                else ""
                    )
            )

            if (persistentDataContainer.hasMark("house")) sendGameMessage(
                userConfig.messages.info["houses-left"]
                    ?.replace(
                        "{n}", coloredName((MSD.totalHouses - gameData.housesInGame).toString())
                    )
            )
            if (persistentDataContainer.hasMark("hotel")) sendGameMessage(
                userConfig.messages.info["hotels-left"]
                    ?.replace(
                        "{n}", coloredName((MSD.totalHotels - gameData.hotelsInGame).toString())
                    )
            )
        }
    }
}


fun item(name: String, loc: String): ItemStack {
    return when (name) {
        "chance" -> ItemStack(userConfig.materials.chance).apply {
            itemMeta = itemMeta!!.apply {
                setDisplayName(MSD.loc[loc]!!.chanceName)
                lore = MSD.loc[loc]!!.chances.random()

                persistentDataContainer.addMark("chance")
            }
        }
        "community_chest" -> ItemStack(userConfig.materials.communityChest).apply {
            itemMeta = itemMeta!!.apply {
                setDisplayName(MSD.loc[loc]!!.communityChestName)
                lore = MSD.loc[loc]!!.communityChests.random()

                persistentDataContainer.addMark("community_chest")
            }
        }
        "house" -> ItemStack(MSD.houseMaterial).apply {
            itemMeta = itemMeta!!.apply {
                setDisplayName(MSD.loc[loc]!!.houseName)

                persistentDataContainer.addMark("house")
            }
        }
        "hotel" -> ItemStack(MSD.hotelMaterial).apply {
            itemMeta = itemMeta!!.apply {
                setDisplayName(MSD.loc[loc]!!.hotelName)

                persistentDataContainer.addMark("hotel")
            }
        }
        else -> run {  // property type
            val args = name.split(".")  // "name" here is property type
            val logicalType = when {
                args[0] == "street" -> LPT.STREET
                args[0] == "railroad" -> LPT.RAILROAD
                args[1] == "electricity" -> LPT.ELECTRICITY
                args[1] == "water" -> LPT.WATER
                else -> throw IllegalArgumentException()  // should never occur
            }

            ItemStack(
                when (logicalType) {
                    LPT.STREET -> Material.matchMaterial(
                        "minecraft:${args[1].replace("cyan", "light_blue")}_stained_glass_pane"
                    )!!
                    LPT.RAILROAD -> Material.RAIL
                    LPT.ELECTRICITY -> Material.REDSTONE_TORCH
                    LPT.WATER -> Material.POTION
                }
            ).apply {
                itemMeta = itemMeta!!.apply {
                    if (this is PotionMeta) basePotionData = PotionData(PotionType.WATER)

                    MSD.loc[loc]!!.properties[name]!!.let {  // "name" here is property type
                        setDisplayName(it.name)
                        lore = it.description
                    }

                    persistentDataContainer.addMark("property")
                    persistentDataContainer.addPropertyType(name)  // "name" here is property type
                }
            }
        }
    }
}
