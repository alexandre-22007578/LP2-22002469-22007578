package pt.ulusofona.lp2.deisiGreatGame;

import java.util.ArrayList;

public class EfeitosSecundarios extends AbismoOrFerramenta {// O programador recua para a posição onde estava há 2 movimentos atrás.

    public EfeitosSecundarios(int id) {
        super(id);
        titulo = "Efeitos secundários";
    }

    @Override
    public String reactToAbyssOrTool(boolean levouCounter) {
        if (levouCounter) {
            return "Caiu nos Efeitos secundários, mas utilizou uma ferramenta para evitar";
        }
        return "Caiu nos Efeitos secundários, irá recuar até á casa onde estava no inicio do turno anterior";
    }

    @Override
    public String move(int dado, Programmer player, int tamanhoTabuleiro, boolean levouCounter, ArrayList<Programmer> players) {
        if (!levouCounter) {
            player.mover(-(player.getPosicao() - player.getPosicao2Anterior()), tamanhoTabuleiro);
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
