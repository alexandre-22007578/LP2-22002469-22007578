package pt.ulusofona.lp2.deisiGreatGame;

import java.util.ArrayList;

public class DuplicatedCode extends AbismoOrFerramenta{// O programador recua até à casa onde estava antes de chegar a esta casa
    public DuplicatedCode(int id) {
        super(id);
        titulo="Duplicated Code";
    }

    @Override
    public String reactToAbyssOrTool(boolean levouCounter) {
        if (levouCounter){
            return "Caiu num Duplicated Code, mas utilizou uma ferramenta para evitar";
        }
        return "Caiu num Duplicate Code, irá recuar até á casa onde estava no inicio do turno";
    }

    @Override
    public String move(int dado, Programmer player, int tamanhoTabuleiro, boolean levouCounter, ArrayList<Programmer> players) {

        if (!levouCounter){
            player.mover(-(player.getPosicao()-player.getPosicaoAnterior()),tamanhoTabuleiro);
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
