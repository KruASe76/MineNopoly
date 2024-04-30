package me.kruase.minenopoly

import org.bukkit.Material
import org.bukkit.configuration.file.FileConfiguration
import java.io.File
import kotlin.math.pow


data class MinenopolyConfig(private val config: FileConfiguration) {
    val gameDistanceSquared = config.getDouble("game-distance", 16.0).pow(2)
    val materials = MaterialsConfig(config, "items")
    val scoreboard = ScoreboardConfig(config, "scoreboard")
    val messages = MessagesConfig(config, "messages")
}
// squared to speed up computation
// "materials" is more API-related name, while "item" is more user-friendly


fun Minenopoly.getUserConfig(): MinenopolyConfig {
    return try {
        saveDefaultConfig()
        reloadConfig()
        MinenopolyConfig(config)
    } catch (e: Exception) {
        when (e) {
            is NullPointerException, is NumberFormatException -> {
                newDefaultConfig()
                MinenopolyConfig(config)
            }
            else -> throw e
        }
    }.also { logger.info("Config loaded!") }
}

fun Minenopoly.newDefaultConfig() {
    logger.severe("Invalid $name config detected! Creating a new one (default)...")
    File(dataFolder, "config.yml").renameTo(
        File(dataFolder, "config.yml.old-${System.currentTimeMillis()}")
    )
    saveDefaultConfig()
    reloadConfig()
    logger.info("New (default) config created!")
}


data class MaterialsConfig(private val config: FileConfiguration, private val prefix: String) {
    val money: Map<Int, Material> = config.getConfigurationSection("$prefix.money")!!
        .getKeys(false).sortedByDescending { it.toInt() }.associate {
            it.toInt() to Material.matchMaterial(config.getString("$prefix.money.$it")!!)!!
        }
    val chance: Material = Material.matchMaterial(config.getString("$prefix.chance")!!)!!
    val communityChest: Material = Material.matchMaterial(config.getString("$prefix.community-chest")!!)!!
}

data class ScoreboardConfig(private val config: FileConfiguration, private val prefix: String) {
    val useDisplayName: Boolean = config.getBoolean("$prefix.use-display-name")
    @Suppress("UNCHECKED_CAST")
    val nameReplaceMap: Map<Regex, String> =
        (config.getMapList("$prefix.name-replace-map") as? List<Map<String, String>>?)
            ?.associate { map -> Regex(map["old"]!!) to map["new"]!! }
            ?: emptyMap()
    val nameReplaceExclusions: List<String> = config.getStringList("$prefix.name-replace-map-exclusions")
}

data class MessagesConfig(private val config: FileConfiguration, private val prefix: String) {
    val help: Map<String, String> = config.getConfigurationSection("$prefix.help")!!
        .getKeys(false).associateWith { config.getString("$prefix.help.$it")!! }
    val error: Map<String, String> = config.getConfigurationSection("$prefix.error")!!
        .getKeys(false).associateWith { config.getString("$prefix.error.$it")!! }
    val info: Map<String, String> = config.getConfigurationSection("$prefix.info")!!
        .getKeys(false).associateWith { config.getString("$prefix.info.$it")!! }
}
