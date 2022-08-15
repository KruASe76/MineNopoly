package me.kruase.minenopoly

import org.bukkit.configuration.file.FileConfiguration
import org.bukkit.Material
import org.bukkit.plugin.Plugin
import java.io.File


data class MinenopolyConfig(private val config: FileConfiguration) {
    val materials = MaterialsConfig(config)
    val messages = MessagesConfig(config)
}

fun getMinenopolyConfig(plugin: Plugin): MinenopolyConfig {
    return try {
        plugin.saveDefaultConfig()
        plugin.reloadConfig()
        MinenopolyConfig(plugin.config)
    } catch (e: Exception) {
        when (e) {
            is NullPointerException, is NumberFormatException -> {
                newDefaultConfig(plugin)
                MinenopolyConfig(plugin.config)
            }
            else -> throw e
        }
    }
}

fun newDefaultConfig(plugin: Plugin) {
    plugin.logger.severe("Invalid Minenopoly config detected! Creating a new one (default)...")
    File(plugin.dataFolder, "config.yml").renameTo(
        File(plugin.dataFolder, "config.yml.old-${System.currentTimeMillis()}")
    )
    plugin.saveDefaultConfig()
    plugin.reloadConfig()
    plugin.logger.info("New (default) config created!")
}


// "materials" is more API-related name, when "item" is more user-friendly
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
