package me.kruase.minenopoly

import org.bukkit.block.Container


typealias MGD = MinenopolyGameData


data class MinenopolyGameData(val bank: Container) {
    var moneyInGame: Int = 0
    var chancesInGame: Int = 0
    var communityChestsInGame: Int = 0
    var housesInGame: Int = 0
    var hotelsInGame: Int = 0

    val propertiesInGame: MutableMap<String, Boolean> = MSD.propertyTypes.associateWith { false }.toMutableMap()
}
