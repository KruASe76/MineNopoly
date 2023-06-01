package me.kruase.minenopoly.commands

import me.kruase.minenopoly.MGD
import me.kruase.minenopoly.Minenopoly.Companion.gameData
import me.kruase.minenopoly.Minenopoly.Companion.sendGlobalMessage
import me.kruase.minenopoly.Minenopoly.Companion.userConfig
import me.kruase.minenopoly.util.canBeBank
import me.kruase.minenopoly.util.coloredName
import me.kruase.minenopoly.util.hasPluginPermission
import org.bukkit.Material
import org.bukkit.World.Environment
import org.bukkit.block.Container
import org.bukkit.entity.Player
import net.md_5.bungee.api.ChatColor as CC


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
        if (  // easter egg
            args.contentEquals(arrayOf("~", "~-1", "~")) &&
            player.world.getBlockAt(player.location).type == Material.CHEST
        ) userConfig.messages.error["standing-on-chest"] ?: "Error: standing-on-chest"
        else userConfig.messages.error["not-a-container"] ?: "Error: not-a-container"
    )

    gameData = MGD(block.state as Container)

    sendGlobalMessage(
        userConfig.messages.info["game-start"]
            ?.replace("{player}", coloredName(player.playerListName))
            ?.replace("{coordinates}", "${CC.GREEN}[${coords[0]} ${coords[1]} ${coords[2]}]${CC.RESET}")
            ?.replace("{dimension}", when (player.world.environment) {
                Environment.NORMAL -> "${CC.GREEN}Overworld${CC.RESET}"
                Environment.NETHER -> "${CC.RED}Nether${CC.RESET}"
                Environment.THE_END -> "${CC.LIGHT_PURPLE}End${CC.RESET}"
                else -> "${CC.DARK_AQUA}${player.world.name}${CC.RESET}"
            })
    )
}
