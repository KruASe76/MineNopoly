package me.kruase.minenopoly.commands

import org.bukkit.entity.Player
import org.bukkit.block.Container
import org.bukkit.World.Environment
import org.bukkit.ChatColor
import me.kruase.minenopoly.Minenopoly.Companion.userConfig
import me.kruase.minenopoly.Minenopoly.Companion.gameData
import me.kruase.minenopoly.Minenopoly.Companion.sendGlobalMessage
import me.kruase.minenopoly.MinenopolyGameData as GameData
import me.kruase.minenopoly.util.*


fun start(player: Player, args: Array<out String>) {
    if (!player.hasPluginPermission("start")) throw UnsupportedOperationException()

    if (args.size != 3) throw IllegalArgumentException()

    if (gameData != null) throw IllegalStateException(
        userConfig.messages.error["game-already-started"] ?: "Error: game-already-started"
    )

    val coords: List<Int> = try {
        args.zip(player.location.run { listOf(blockX, blockY, blockZ) }).map {
            if (it.first.startsWith("~")) it.second + if (it.first == "~") 0 else it.first.drop(1).toInt()
            else it.first.toInt()
        }
    } catch (e: NumberFormatException) {
        throw IllegalArgumentException()
    }

    val block = player.world.getBlockAt(coords[0], coords[1], coords[2])
    if (!block.type.canBeBank()) throw IllegalStateException(
        userConfig.messages.error["not-a-container"] ?: "Error: not-a-container"
    )

    gameData = GameData(block.state as Container)

    sendGlobalMessage(
        userConfig.messages.info["game-start"]
            ?.replace("{player}", getColoredName(player.playerListName))
            ?.replace("{coordinates}", "${ChatColor.GREEN}[${coords[0]} ${coords[1]} ${coords[2]}]${ChatColor.RESET}")
            ?.replace("{dimension}", when (player.world.environment) {
                Environment.NORMAL -> "${ChatColor.GREEN}Overworld${ChatColor.RESET}"
                Environment.NETHER -> "${ChatColor.RED}Nether${ChatColor.RESET}"
                Environment.THE_END -> "${ChatColor.LIGHT_PURPLE}End${ChatColor.RESET}"
            })
    )
}
