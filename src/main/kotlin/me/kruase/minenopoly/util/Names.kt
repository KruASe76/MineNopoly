package me.kruase.minenopoly.util

import org.bukkit.ChatColor


fun getColoredName(name: String): String {
    return name.let {
        if (it.startsWith(ChatColor.COLOR_CHAR)) "$it${ChatColor.RESET}"
        else "${ChatColor.YELLOW}$it${ChatColor.RESET}"
    }
}


fun getMoneyItemName(cost: Int): String {
    return "${ChatColor.GREEN}${ChatColor.STRIKETHROUGH}M${ChatColor.RESET}${ChatColor.GREEN}${cost}${ChatColor.RESET}"
}
