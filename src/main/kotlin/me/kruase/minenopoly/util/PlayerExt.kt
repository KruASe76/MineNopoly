package me.kruase.minenopoly.util

import me.kruase.minenopoly.Minenopoly.Companion.gameData
import me.kruase.minenopoly.Minenopoly.Companion.userConfig
import org.bukkit.entity.Player


val Player.isInGame: Boolean
    get() =
        gameData != null &&
                (
                        inventory.contents
                            .any { it?.itemMeta?.persistentDataContainer?.hasAnyMark() == true } ||
                                gameData?.bank?.location?.let { bankLocation ->
                                    bankLocation.world == location.world &&
                                            bankLocation.distanceSquared(location) < userConfig.gameDistanceSquared
                                } ?: false
                        )
