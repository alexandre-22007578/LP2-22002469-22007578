package pt.ulusofona.lp2.deisiGreatGame;

import java.util.ArrayList;

public class ErroDeSintaxe extends AbismoOrFerramenta { // O programador recua 1 casa

    public ErroDeSintaxe(int id) {
        super(id);
        titulo = "Erro de sintaxe";
    }

    @Override
    public String reactToAbyssOrTool(boolean levouCounter) {

        if (levouCounter){
            return "Caiu num Erro de Sintaxe, mas utilizou uma ferramenta para evitar";
        }

        return "Caiu num Erro de Sintaxe, irá recuar 1 casa";
    }

    @Override
    public String move(int dado, Programmer player, int tamanhoTabuleiro, boolean levouCounter, ArrayList<Programmer> players) {
        if (!levouCounter){
            player.mover(-1,tamanhoTabuleiro);
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
