package me.kruase.minenopoly.util

import me.kruase.minenopoly.Minenopoly
import org.bukkit.entity.Player


fun Player.isInGame(): Boolean {
    return inventory.contents.any { it?.itemMeta?.persistentDataContainer?.hasAnyMark() == true } ||
            (Minenopoly.gameData?.bank?.location?.distance(location) ?: 16.0) < 16  // I decided it to be 16.
}
