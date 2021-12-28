package pt.ulusofona.lp2.deisiGreatGame

enum class CommandType { GET, POST }

fun router(): ((CommandType) -> (GameManager, List<String>) -> String)? {
return null
}





fun getPlayer(manager: GameManager, args: List<String>): String? {

    when (args[0]) {
        "PLAYER" -> return player()
        "PLAYERS_BY_LANGUAGE"->return  PLAYERS_BY_LANGUAGE()
    }
    return null
}

fun PLAYERS_BY_LANGUAGE(): String {
    return ""
}

fun player(): String {
    return ""
}

fun getFunction() {

}
