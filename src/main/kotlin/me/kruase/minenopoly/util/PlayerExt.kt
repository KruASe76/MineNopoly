package me.kruase.minenopoly.util

import me.kruase.minenopoly.Minenopoly.Companion.gameData
import me.kruase.minenopoly.Minenopoly.Companion.userConfig
import org.bukkit.entity.Player


fun Player.isInGame(): Boolean {
    return inventory.contents.any { it?.itemMeta?.persistentDataContainer?.hasAnyMark() == true } ||
            (gameData?.bank?.location?.distance(location) ?: Double.POSITIVE_INFINITY) < userConfig.gameDistance
}
