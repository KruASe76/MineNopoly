package me.kruase.minenopoly

import me.kruase.minenopoly.commands.*
import me.kruase.minenopoly.util.hasPluginPermission
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.command.TabExecutor
import org.bukkit.entity.Player
import net.md_5.bungee.api.ChatColor as CC


private val playerOnlyCommands = setOf("start", "book", "get")


class MinenopolyCommands : TabExecutor {
    override fun onTabComplete(
        sender: CommandSender,
        command: Command,
        label: String,
        args: Array<out String>
    ): List<String> {
        val fullArgs = args.dropLast(1)
        return when (fullArgs.getOrNull(0)) {
            null -> Minenopoly.userConfig.messages.help.keys
                .filter {
                    sender.hasPluginPermission(it.replace("-", "."))
                } - "header" - "finish-forced" - if (sender is Player) emptySet() else playerOnlyCommands
            "help" -> when {
                sender.hasPluginPermission("help") -> when (fullArgs.getOrNull(1)) {
                    null -> Minenopoly.userConfig.messages.help.keys
                        .filter { sender.hasPluginPermission(it.replace("-", ".")) } - "header"
                    else -> emptyList()
                }
                else -> emptyList()
            }
            "start" -> when {
                sender.hasPluginPermission("start") -> when (sender) {
                    is Player -> targetBlockCompletion(sender, args.drop(1).toTypedArray())
                    else -> emptyList()
                }
                else -> emptyList()
            }
            "finish" -> when {
                sender.hasPluginPermission("finish.forced") && fullArgs.size == 1 -> listOf("forced")
                else -> emptyList()
            }
            "book" -> when {
                sender.hasPluginPermission("book") -> when (sender) {
                    is Player -> when (fullArgs.getOrNull(1)) {
                        null -> MSD.localizations.keys.toList()
                        else -> emptyList()
                    }

                    else -> emptyList()
                }
                else -> emptyList()
            }
            "get" -> when {
                sender.hasPluginPermission("get") -> when (sender) {
                    is Player -> when (fullArgs.getOrNull(1)) {
                        null -> MSD.localizations.keys.toList()
                        in MSD.localizations.keys -> when (fullArgs.getOrNull(2)) {
                            null -> listOf("chance", "community_chest", "house", "hotel", "property")
                            "property" -> when (fullArgs.getOrNull(3)) {
                                null -> listOf("street", "railroad", "utility")
                                "street" -> when (fullArgs.getOrNull(4)) {
                                    null -> listOf("brown", "cyan", "pink", "orange", "red", "yellow", "green", "blue")
                                    "brown", "blue" -> when (fullArgs.getOrNull(5)) {
                                        null -> (1..2).map { it.toString() }
                                        else -> emptyList()
                                    }
                                    "cyan", "pink", "orange", "red", "yellow", "green" -> when (fullArgs.getOrNull(5)) {
                                        null -> (1..3).map { it.toString() }
                                        else -> emptyList()
                                    }
                                    else -> emptyList()
                                }
                                "railroad" -> when (fullArgs.getOrNull(4)) {
                                    null -> (1..4).map { it.toString() }
                                    else -> emptyList()
                                }
                                "utility" -> when (fullArgs.getOrNull(4)) {
                                    null -> listOf("electricity", "water")
                                    else -> emptyList()
                                }
                                else -> emptyList()
                            }
                            else -> emptyList()
                        }
                        else -> emptyList()
                    }
                    else -> emptyList()
                }
                else -> emptyList()
            }
            else -> emptyList()
        }
    }

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        try {
            when (args.getOrNull(0)) {
                null -> help(sender, emptyArray())
                "help" -> help(sender, args.drop(1).toTypedArray())
                "start" -> playerOnly(::start, sender, args.drop(1).toTypedArray())
                "finish" -> finish(sender, args.drop(1).toTypedArray())
                "book" -> playerOnly(::book, sender, args.drop(1).toTypedArray())
                "get" -> playerOnly(::get, sender, args.drop(1).toTypedArray())
                "reload" -> {
                    if (!sender.hasPluginPermission("reload")) throw UnsupportedOperationException()
                    Minenopoly.userConfig = Minenopoly.instance.getUserConfig()
                }
            }
        } catch (e: UnsupportedOperationException) {
            sender.sendMessage(
                "${CC.RED}${Minenopoly.userConfig.messages.error["no-permission"] ?: "Error: no-permission"}"
            )
        } catch (e: IllegalArgumentException) {
            sender.sendMessage(
                "${CC.RED}${Minenopoly.userConfig.messages.error["invalid-command"] ?: "Error: invalid-command"}"
            )
        } catch (e: IllegalStateException) {
            sender.sendMessage("${CC.RED}${e.message ?: "Unknown error"}")  // "Unknown error" should never be output
        }

        return true
    }

    private fun playerOnly(command: (Player, Array<out String>) -> Unit, sender: CommandSender, args: Array<out String>) {
        when (sender) {
            is Player -> command(sender, args)
            else -> sender.sendMessage(
                "${CC.RED}${Minenopoly.userConfig.messages.error["player-only"] ?: "Error: player-only"}"
            )
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
            "" -> base.getOrNull(args.size - 1) ?: emptyList()
            else -> when (args.size) {
                in 1..3 -> listOf(current) + base[args.size - 1].drop(1)
                else -> emptyList()
            }
        }

        return listOf (
            fullCoords.joinToString(" "),
            fullCoords.dropLast(1).joinToString(" "),
            fullCoords.dropLast(2).joinToString(" ")
        ).filter { it.isNotEmpty()}
    }
}
