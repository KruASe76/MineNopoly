package me.kruase.minenopoly.commands

import org.bukkit.entity.Player
import org.bukkit.block.Block
import org.bukkit.block.Container
import org.bukkit.World.Environment
import org.bukkit.ChatColor
import me.kruase.minenopoly.Minenopoly.Companion.mConfig
import me.kruase.minenopoly.Minenopoly.Companion.gameRunning
import me.kruase.minenopoly.Minenopoly.Companion.gameData
import me.kruase.minenopoly.Minenopoly.Companion.sendGlobalMessage
import me.kruase.minenopoly.MinenopolyGameData as GameData
import me.kruase.minenopoly.util.*


fun start(sender: Player, args: Array<out String>) {
    if (!sender.hasPermission("minenopoly.start")) throw UnsupportedOperationException()

    if (args.size != 3) throw IllegalArgumentException()

    if (gameRunning) throw IllegalStateException(
        mConfig.messages.error["game-already-started"] ?: "Error: game-already-started"
    )

    val coords: List<Int> = try {
        args.zip(sender.location.run { listOf(blockX, blockY, blockZ) }).map {
            if (it.first.startsWith("~")) it.second + if (it.first == "~") 0 else it.first.drop(1).toInt()
            else it.first.toInt()
        }
    } catch (e: NumberFormatException) {
        throw IllegalArgumentException()
    }

    val block: Block= sender.world.getBlockAt(coords[0], coords[1], coords[2])
    if (!block.type.isStorage()) throw IllegalStateException(
        mConfig.messages.error["not-a-container"] ?: "Error: not-a-container"
    )

    gameData = GameData(block.state as Container)
    gameRunning = true

    sendGlobalMessage(
        mConfig.messages.info["game-started"]
            ?.replace("{player}", getColoredName(sender.playerListName))
            ?.replace("{coordinates}", "${ChatColor.GREEN}[${coords[0]} ${coords[1]} ${coords[2]}]${ChatColor.RESET}")
            ?.replace("{dimension}", sender.world.environment.run {
                when (this) {
                    Environment.NORMAL -> "${ChatColor.GREEN}Overworld${ChatColor.RESET}"
                    Environment.NETHER -> "${ChatColor.RED}Nether${ChatColor.RESET}"
                    Environment.THE_END -> "${ChatColor.LIGHT_PURPLE}End${ChatColor.RESET}"
                    Environment.CUSTOM -> "${ChatColor.YELLOW}[Custom dimension]${ChatColor.RESET}"
                }
            })
    )
}
