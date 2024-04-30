package me.kruase.minenopoly.util


fun String.multipleReplace(map: Map<String, String>): String {
    return map.entries
        .fold(this) { string, (old, new) -> string.replace(old, new) }
}

// forced to add unused parameter to avoid "Platform declaration clash"
fun String.multipleReplace(map: Map<Regex, String>, @Suppress("UNUSED_PARAMETER") ignoredIsRegex: Boolean): String {
    return map.entries
        .fold(this) { string, (old, new) -> string.replace(old, new) }
}
