package me.kruase.minenopoly.commands

import org.bukkit.command.CommandSender
import me.kruase.minenopoly.Minenopoly.Companion.mConfig


fun help(sender: CommandSender, args: Array<out String>) {
    if (!sender.hasPermission("minenopoly.help")) throw UnsupportedOperationException()

    if (args.size > 1) throw IllegalArgumentException()

    when (args.getOrNull(0)) {
        null -> mConfig.messages.help.keys
            .filter { sender.hasPermission("minenopoly.${it.replace("-", ".")}") || it == "header"}
            .forEach { sender.sendMessage(arrayOf(mConfig.messages.help[it])) }
        in mConfig.messages.help.keys - "header" -> sender.sendMessage(arrayOf(mConfig.messages.help[args[0]]))
        else -> throw IllegalArgumentException()
    }
}
