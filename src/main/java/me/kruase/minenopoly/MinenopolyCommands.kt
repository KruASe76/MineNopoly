package me.kruase.minenopoly

import org.bukkit.command.TabExecutor
import org.bukkit.command.CommandSender
import org.bukkit.command.Command


class MinenopolyCommands : TabExecutor {
    override fun onTabComplete(
        sender: CommandSender,
        command: Command,
        label: String,
        args: Array<out String>
    ): MutableList<String> {
        return mutableListOf()
    }

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        return false
    }
}
