package me.kruase.minenopoly.util

import org.bukkit.ChatColor


fun getMoneyItemName(cost: Int): String {
    return "${ChatColor.GREEN}${cost} ${ChatColor.GREEN}${ChatColor.STRIKETHROUGH}M"
}
