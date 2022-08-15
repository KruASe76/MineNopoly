package me.kruase.minenopoly

import org.bukkit.block.Container


data class MinenopolyGameData(val bank: Container) {
    var moneyInGame: Int = 0
    var chancesInGame: Int = 0
    var communityChestsInGame: Int = 0
    var housesInGame: Int = 0
    var hotelsInGame: Int = 0

    val propertiesInGame: MutableMap<String, Boolean> = listOf(
        "utility.electric", "utility.water",
        "railroad.1", "railroad.2", "railroad.3", "railroad.4",
        "street.brown.1", "street.brown.2",
        "street.blue.1", "street.blue.2",
        "street.cyan.1", "street.cyan.2", "street.cyan.3",
        "street.pink.1", "street.pink.2", "street.pink.3",
        "street.orange.1", "street.orange.2", "street.orange.3",
        "street.red.1", "street.red.2", "street.red.3",
        "street.yellow.1", "street.yellow.2", "street.yellow.3",
        "street.green.1", "street.green.2", "street.green.3",
    ).associateWith { false }.toMutableMap()
}
