package me.kruase.minenopoly

import java.io.File
import org.bukkit.configuration.file.FileConfiguration
import org.bukkit.Material


data class MinenopolyConfig(private val config: FileConfiguration) {
    val materials = MaterialsConfig(config)
    val messages = MessagesConfig(config)
}


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


// "materials" is more API-related name, while "item" is more user-friendly
data class MaterialsConfig(private val config: FileConfiguration) {
    val money: Map<Int, Material> = config.getConfigurationSection("items.money")!!
        .getKeys(false).sortedByDescending { it.toInt() }.associate {
            it.toInt() to Material.matchMaterial(config.getString("items.money.$it")!!)!!
        }
    val chance: Material = Material.matchMaterial(config.getString("items.chance")!!)!!
    val communityChest: Material = Material.matchMaterial(config.getString("items.community-chest")!!)!!
}

data class MessagesConfig(private val config: FileConfiguration) {
    val help: Map<String, String> = config.getConfigurationSection("messages.help")!!
        .getKeys(false).associateWith { config.getString("messages.help.$it")!! }
    val error: Map<String, String> = config.getConfigurationSection("messages.error")!!
        .getKeys(false).associateWith { config.getString("messages.error.$it")!! }
    val info: Map<String, String> = config.getConfigurationSection("messages.info")!!
        .getKeys(false).associateWith { config.getString("messages.info.$it")!! }
}
