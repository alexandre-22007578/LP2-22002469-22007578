package pt.ulusofona.lp2.deisiGreatGame

enum class CommandType { GET, POST }

fun router(): (CommandType) -> (GameManager, List<String>) -> String? {
    return ::teste
}


fun teste(type: CommandType): (GameManager, List<String>) -> String? {
    return if (type==CommandType.GET){
        ::getters
    }else {
        ::postters
    }

}

fun getters(gameManager: GameManager,list: List<String>):String?{
    return ""
}

fun postters(gameManager: GameManager,list: List<String>):String?{
    return ""
}
