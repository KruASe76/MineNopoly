package me.kruase.minenopoly.commands

import me.kruase.minenopoly.Minenopoly.Companion.userConfig
import me.kruase.minenopoly.util.hasPluginPermission
import org.bukkit.command.CommandSender


fun help(sender: CommandSender, args: Array<out String>) {
    if (!sender.hasPluginPermission("help")) throw UnsupportedOperationException()

    if (args.size > 1) throw IllegalArgumentException()

    when (args.getOrNull(0)) {
        null -> userConfig.messages.help.keys
            .filter { sender.hasPluginPermission(it.replace("-", ".")) || it == "header"}
            .forEach { sender.sendMessage(userConfig.messages.help[it]) }
        in userConfig.messages.help.keys - "header" -> sender.sendMessage(userConfig.messages.help[args[0]])
        else -> throw IllegalArgumentException()
    }
}
