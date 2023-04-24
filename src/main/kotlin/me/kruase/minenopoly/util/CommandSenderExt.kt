package me.kruase.minenopoly.util

import org.bukkit.command.CommandSender
import me.kruase.minenopoly.Minenopoly.Companion.instance


fun CommandSender.hasPluginPermission(name: String): Boolean {
    return hasPermission("${instance.name.lowercase()}.$name")
}
