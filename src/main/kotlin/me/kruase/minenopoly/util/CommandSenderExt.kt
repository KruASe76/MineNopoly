package me.kruase.minenopoly.util

import me.kruase.minenopoly.Minenopoly.Companion.instance
import org.bukkit.command.CommandSender


fun CommandSender.hasPluginPermission(name: String): Boolean {
    return hasPermission("${instance.name.lowercase()}.$name")
}
