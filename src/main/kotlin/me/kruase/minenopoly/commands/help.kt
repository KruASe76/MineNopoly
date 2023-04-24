package me.kruase.minenopoly.commands

import org.bukkit.command.CommandSender
import me.kruase.minenopoly.Minenopoly.Companion.userConfig
import me.kruase.minenopoly.util.hasPluginPermission


fun help(sender: CommandSender, args: Array<out String>) {
    if (!sender.hasPluginPermission("help")) throw UnsupportedOperationException()

    if (args.size > 1) throw IllegalArgumentException()

    when (args.getOrNull(0)) {
        null -> userConfig.messages.help.keys
            .filter { sender.hasPluginPermission(it.replace("-", ".")) || it == "header"}
            .forEach { sender.sendMessage(arrayOf(userConfig.messages.help[it])) }
        in userConfig.messages.help.keys - "header" -> sender.sendMessage(arrayOf(userConfig.messages.help[args[0]]))
        else -> throw IllegalArgumentException()
    }
}
