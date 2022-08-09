package me.kruase.minenopoly.util

import org.bukkit.Material


fun Material.isStorage(): Boolean {
    return this in listOf(Material.CHEST, Material.BARREL)
}
