package me.kruase.minenopoly.util

import org.bukkit.ChatColor


fun getColoredName(name: String): String {
    return name.run {
        if (startsWith(ChatColor.COLOR_CHAR)) "$this${ChatColor.RESET}"
        else "${ChatColor.YELLOW}$this${ChatColor.RESET}"
    }
}

fun getMoneyItemName(cost: Int): String {
    return "${ChatColor.GREEN}${ChatColor.STRIKETHROUGH}M${ChatColor.RESET}${ChatColor.GREEN}${cost}${ChatColor.RESET}"
}
