package pt.ulusofona.lp2.deisiGreatGame;

import java.util.ArrayList;

public class FileNotFoundException extends AbismoOrFerramenta{// O programador recua 3 casa
    public FileNotFoundException(int id) {
        super(id);
        titulo="File Not Found Exception";
    }

    @Override
    public String reactToAbyssOrTool(boolean levouCounter) {
        if (levouCounter){
            return "Caiu num File Not Found Exception, mas utilizou uma ferramenta para evitar";
        }
        return "Caiu num File Not Found Exception, irá recuar 3 casas";
    }

    @Override
    public String move(int dado, Programmer player, int tamanhoTabuleiro, boolean levouCounter, ArrayList<Programmer> players) {
        if (!levouCounter){
            player.mover(-3,tamanhoTabuleiro);
        }
        return reactToAbyssOrTool(levouCounter);
    }



    @Override
    public boolean souFerramenta() {
        return false;
    }


    @Override
    public boolean daCounter(String nomeAbismo) {
        return false;
    }
}
