package me.kruase.minenopoly.commands

import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import me.kruase.minenopoly.Minenopoly.Companion.instance
import me.kruase.minenopoly.Minenopoly.Companion.mConfig
import me.kruase.minenopoly.Minenopoly.Companion.gameRunning
import me.kruase.minenopoly.Minenopoly.Companion.gameData
import me.kruase.minenopoly.Minenopoly.Companion.sendGlobalMessage
import me.kruase.minenopoly.update
import me.kruase.minenopoly.util.*


fun finish(sender: CommandSender, args: Array<out String>) {
    if (!sender.hasPermission("minenopoly.finish")) throw UnsupportedOperationException()

    if (args.size > 1) throw IllegalArgumentException()

    if (!gameRunning) throw IllegalStateException(
        mConfig.messages.error["game-not-started"] ?: "Error: game-not-started"
    )

    when (args.getOrNull(0)) {
        null -> {
            when {
                gameData!!.run {
                    listOf(moneyInGame, chancesInGame, communityChestsInGame, housesInGame, hotelsInGame).all { it == 0 }
                            && propertiesInGame.values.all { !it }
                } -> {
                    gameRunning = false
                    gameData = null
                    instance.server.onlinePlayers.forEach {
                        it.scoreboard.update(it)
                    }
                    sendGlobalMessage(
                        mConfig.messages.info["game-finished"]
                            ?.replace("{player}", getColoredName(
                                when (sender) {
                                    is Player -> sender.playerListName
                                    else -> sender.name
                                }
                            ))
                    )
                }
                else -> throw IllegalStateException(
                    mConfig.messages.error["unable-to-finish-game"] ?: "Error: unable-to-finish-game"
                )
            }
        }
        "forced" -> when {
            (sender.hasPermission("minenopoly.finish.forced")) -> {
                gameRunning = false
                gameData = null
                instance.server.onlinePlayers.forEach {
                    it.inventory.run {
                        contents.filter { it?.itemMeta?.persistentDataContainer?.hasAnyMark() == true }
                            .forEach { remove(it) }
                    }
                    it.scoreboard.update(it)
                }
                sendGlobalMessage(
                    mConfig.messages.info["game-force-finished"]
                        ?.replace("{player}", getColoredName(
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
