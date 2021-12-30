package pt.ulusofona.lp2.deisiGreatGame

enum class CommandType { GET, POST }

fun router(): (CommandType) -> ((GameManager, List<String>) -> String?)? {
    return ::getCommand
}


fun getCommand(type: CommandType): ((GameManager, List<String>) -> String?)? {
    if (type == CommandType.GET) {
       return ::getters
    } else if (type == CommandType.POST) {
        return ::postters
    }
return null
}

fun getters(gameManager: GameManager, list: List<String>): String? {

    return when(list[0]){
        "PLAYER"->playerFirstName(gameManager, list)
        else -> return null
    }


}

fun playerFirstName(gameManager: GameManager, list: List<String>): String{
    val jogadores=gameManager.getProgrammers(true)
    val nome=list[1]
    return jogadores.filter { it.name==nome }.toString()

}





fun postters(gameManager: GameManager, list: List<String>): String? {
    return ""
}
