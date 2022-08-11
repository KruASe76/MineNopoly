package me.kruase.minenopoly

import org.bukkit.command.TabExecutor
import org.bukkit.command.CommandSender
import org.bukkit.command.Command
import org.bukkit.entity.Player
import org.bukkit.ChatColor
import me.kruase.minenopoly.commands.*


class MinenopolyCommands : TabExecutor {
    private val allCommands: List<String> = listOf("help", "start", "finish", "book", "reload", "get")

    override fun onTabComplete(
        sender: CommandSender,
        command: Command,
        label: String,
        args: Array<out String>
    ): List<String> {
        val fullArgs = args.dropLast(1)
        return when (fullArgs.getOrNull(0)) {
            null -> allCommands.filter { sender.hasPermission("minenopoly.$it") }
            "help" -> when (fullArgs.size) {
                1 -> allCommands.filter { sender.hasPermission("minenopoly.$it") } - "help"
                else -> listOf()
            }
            "start" -> when (sender) {
                is Player -> targetBlockCompletion(sender, args.drop(1).toTypedArray())
                else -> listOf()
            }
            "finish" -> when {
                sender.hasPermission("minenopoly.finish.forced") && fullArgs.size == 1 -> listOf("forced")
                else -> listOf()
            }
            "get" -> when (sender) {
                is Player -> when (fullArgs.getOrNull(1)) {
                    null -> listOf("chance", "community_chest", "property", "house", "hotel")
                    "property" -> when (fullArgs.getOrNull(2)) {
                        null -> listOf("street", "railroad", "utility")
                        "street" -> when (fullArgs.getOrNull(3)) {
                            null -> listOf("brown", "cyan", "pink", "orange", "red", "yellow", "green", "blue")
                            "brown", "blue" -> when (fullArgs.size) {
                                4 -> (1..2).map { it.toString() }
                                else -> listOf()
                            }
                            "cyan", "pink", "orange", "red", "yellow", "green" -> when (fullArgs.size) {
                                4 -> (1..3).map { it.toString() }
                                else -> listOf()
                            }
                            else -> listOf()
                        }
                        "railroad" -> when (fullArgs.size) {
                            3 -> (1..4).map { it.toString() }
                            else -> listOf()
                        }
                        "utility" -> when (fullArgs.size) {
                            3 -> listOf("electric", "water")
                            else -> listOf()
                        }
                        else -> listOf()
                    }
                    else -> listOf()
                }
                else -> listOf()
            }
            else -> listOf()
        }
    }

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        try {
            when (args.getOrNull(0)) {
                null -> help(sender, args)
                "help" -> help(sender, args.drop(1).toTypedArray())
                "start" -> playerOnly(sender) { start(sender, args.drop(1).toTypedArray()) }
                "finish" -> finish(sender, args.drop(1).toTypedArray())
                "book" -> playerOnly(sender) { book(sender, args.drop(1).toTypedArray()) }
                "get" -> playerOnly(sender) { get(sender, args.drop(1).toTypedArray()) }
                "reload" -> Minenopoly.mConfig = getMinenopolyConfig(Minenopoly.instance)
            }
        } catch (e: IllegalArgumentException) {
            sender.sendMessage("${ChatColor.RED}${Minenopoly.mConfig.messages.error["invalid-command"]}")
        }

        return true
    }

    private fun playerOnly(sender: CommandSender, run: () -> Unit) {
        when (sender) {
            is Player -> run()
            else -> sender.sendMessage("${ChatColor.RED}${Minenopoly.mConfig.messages.error["player-only"]}")
        }
    }

    // trying to recreate target block coordinates completion (like in /setblock)
    private fun targetBlockCompletion(player: Player, args: Array<out String>): List<String> {
        // already typed in coordinates args amount -> index
        val base: List<List<String>> = player.getTargetBlockExact(8)
            ?.run {
                listOf(listOf("$x", "$y", "$z"), listOf("$y", "$z"), listOf("$z"))
            } ?: listOf(listOf("~", "~", "~"), listOf("~", "~"), listOf("~"))

        val fullCoords = when (val current = args.last()) {
            "" -> base.getOrNull(args.size - 1) ?: listOf()
            else -> when (args.size) {
                in 1..3 -> listOf(current) + base[args.size - 1].drop(1)
                else -> listOf()
            }
        }

        return listOf (
            fullCoords.joinToString(" "),
            fullCoords.dropLast(1).joinToString(" "),
            fullCoords.dropLast(2).joinToString(" ")
        ).filter { it.isNotEmpty()}
    }
}
