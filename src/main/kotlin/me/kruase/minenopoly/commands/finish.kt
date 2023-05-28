package me.kruase.minenopoly.commands

import me.kruase.minenopoly.Minenopoly
import me.kruase.minenopoly.Minenopoly.Companion.gameData
import me.kruase.minenopoly.Minenopoly.Companion.instance
import me.kruase.minenopoly.Minenopoly.Companion.sendGlobalMessage
import me.kruase.minenopoly.Minenopoly.Companion.userConfig
import me.kruase.minenopoly.update
import me.kruase.minenopoly.util.coloredName
import me.kruase.minenopoly.util.hasAnyMark
import me.kruase.minenopoly.util.hasPluginPermission
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player


fun finish(sender: CommandSender, args: Array<out String>) {
    if (!sender.hasPluginPermission("finish")) throw UnsupportedOperationException()

    if (args.size > 1) throw IllegalArgumentException()

    val gameData = gameData ?: throw IllegalStateException(
        userConfig.messages.error["game-not-started"] ?: "Error: game-not-started"
    )

    when (args.getOrNull(0)) {
        null -> {
            when {
                gameData.run {
                    listOf(
                        moneyInGame, chancesInGame, communityChestsInGame, housesInGame, hotelsInGame
                    ).all { it == 0 } && propertiesInGame.values.all { !it }
                } -> {
                    Minenopoly.gameData = null
                    instance.server.onlinePlayers.forEach {
                        it.scoreboard.update(it)
                    }
                    sendGlobalMessage(
                        userConfig.messages.info["game-finish"]
                            ?.replace("{player}", coloredName(
                                when (sender) {
                                    is Player -> sender.playerListName
                                    else -> sender.name
                                }
                            ))
                    )
                }
                else -> throw IllegalStateException(
                    userConfig.messages.error["unable-to-finish-game"] ?: "Error: unable-to-finish-game"
                )
            }
        }
        "forced" -> when {
            (sender.hasPluginPermission("finish.forced")) -> {
                Minenopoly.gameData = null
                instance.server.onlinePlayers.forEach { player ->
                    player.inventory.run {
                        contents.filter { it?.itemMeta?.persistentDataContainer?.hasAnyMark() == true }
                            .forEach { remove(it) }
                    }
                    player.scoreboard.update(player)
                }
                sendGlobalMessage(
                    userConfig.messages.info["game-force-finish"]
                        ?.replace("{player}", coloredName(
                            when (sender) {
                                is Player -> sender.playerListName
                                else -> sender.name
                            }
                        ))
                )
            }
            else -> throw UnsupportedOperationException()
        }
        else -> throw IllegalArgumentException()
    }
}
