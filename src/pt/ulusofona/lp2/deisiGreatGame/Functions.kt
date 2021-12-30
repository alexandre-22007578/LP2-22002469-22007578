package pt.ulusofona.lp2.deisiGreatGame

enum class CommandType { GET, POST }

fun router(): (CommandType) -> ((GameManager, List<String>) -> String?)? {
    return ::teste
}


fun teste(type: CommandType): ((GameManager, List<String>) -> String?)? {
    if (type == CommandType.GET) {
       return ::getters
    } else if (type == CommandType.POST) {
        return ::postters
    }
return null
}

fun getters(gameManager: GameManager, list: List<String>): String? {
    return ""
}

fun postters(gameManager: GameManager, list: List<String>): String? {
    return ""
}
