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
        "POLYGLOTS" ->polyglots(gameManager)
        "PLAYERS_BY_LANGUAGE" ->playerByLanguage(gameManager, list)
        else -> return null

    }


}

fun playerFirstName(gameManager: GameManager, list: List<String>): String{
    val jogadores=gameManager.getProgrammers(true)
    val nome=list[1]
    val resultado = jogadores.filter { it.name==nome }
    if (resultado.isEmpty()){
        return "Inexistent player"
    }
    return resultado.toString().replace("[", "").replace("]", "")

}
fun playerByLanguage(gameManager: GameManager, list: List<String>): String{
    val jogadores=gameManager.getProgrammers(true)
    val linguagem=list[1]
    return jogadores.filter { it.linguagensList.contains(linguagem) }.map { it.nome }.toString().replace("[", "").replace("]", "").trim()

}

fun polyglots(gameManager: GameManager): String{
    val resultado: String
    val jogadores=gameManager.getProgrammers(true)
    resultado = jogadores.filter { it.linguagensList.size >= 2 }.sortedWith{j1, j2 -> j1.linguagensList.size - j2.linguagensList.size }.joinToString ("\n" ){it.nome+ " : " + it.linguagensList.size}
    return resultado
}



fun postters(gameManager: GameManager, list: List<String>): String? {
    return ""
}
