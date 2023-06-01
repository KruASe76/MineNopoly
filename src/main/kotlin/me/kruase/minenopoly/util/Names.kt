package me.kruase.minenopoly.util

import net.md_5.bungee.api.ChatColor as CC


fun coloredName(name: String, outerStyle: String = ""): String {
    return name.let {
        if (it.startsWith(CC.COLOR_CHAR)) "$it${CC.RESET}$outerStyle"
        else "${CC.YELLOW}$it${CC.RESET}$outerStyle"
    }
}


fun asCurrency(amount: Int, outerStyle: String = "", negative: Boolean = false): String {
    return "${CC.RESET}${if (negative) CC.RED else CC.GREEN}${CC.STRIKETHROUGH}M" +
            "${CC.RESET}${if (negative) CC.RED else CC.GREEN}$amount" +
            "${CC.RESET}$outerStyle"
}
