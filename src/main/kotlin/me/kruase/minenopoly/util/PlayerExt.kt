package me.kruase.minenopoly.util

import org.bukkit.entity.Player
import me.kruase.minenopoly.Minenopoly


fun Player.isInGame(): Boolean {
    return inventory.contents.any { it?.itemMeta?.persistentDataContainer?.hasAnyMark() == true } ||
            (Minenopoly.gameData?.bank?.location?.distance(location) ?: 16.0) < 16
}
