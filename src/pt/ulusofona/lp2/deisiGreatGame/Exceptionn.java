package pt.ulusofona.lp2.deisiGreatGame;

import java.util.ArrayList;

public class Exceptionn extends AbismoOrFerramenta {// O programador recua 2 casa

    public Exceptionn(int id) {
        super(id);
        titulo = "Exception";
    }

    @Override
    public String reactToAbyssOrTool(boolean levouCounter) {
        if (levouCounter) {
            return "Caiu numa Exception, mas utilizou uma ferramenta para evitar";
        }
        return "Caiu numa Exception, irá recuar 2 casas";
    }

    @Override
    public String move(int dado, Programmer player, int tamanhoTabuleiro, boolean levouCounter, ArrayList<Programmer> players) {
        if (!levouCounter){
            player.mover(-2,tamanhoTabuleiro);
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
