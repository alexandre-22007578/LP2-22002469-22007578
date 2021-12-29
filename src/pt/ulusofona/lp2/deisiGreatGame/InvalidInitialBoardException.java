package pt.ulusofona.lp2.deisiGreatGame;

public class InvalidInitialBoardException extends Exception {

    String message;
    int erro;
    int id;


    public InvalidInitialBoardException(String message, int erro, int id) {
        this.message = message;
        this.id = id;
        this.erro = erro;
    }

    public int getErro(){
        return erro;
    }

    public String getMessage() {
        return message;
    }

    public boolean isInvalidAbyss() {
        return id==1;

    }

    public boolean isInvalidTool() {
        return id==2;
    }

    public int getTypeId() {
        if (erro==1 || erro==2){
            return id;
        }
     return 10;
    }

}
