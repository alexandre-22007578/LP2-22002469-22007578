package pt.ulusofona.lp2.deisiGreatGame;

import java.util.ArrayList;

public class ErroDeLogica extends AbismoOrFerramenta{// O programador recua N casas, sendo N metade do valor que tiver saído no dado, arredondado para baixo.


    public ErroDeLogica(int id) {
        super(id);
        titulo="Erro de lógica";
    }

    @Override
    public String reactToAbyssOrTool(boolean levouCounter) {
        if (levouCounter){
            return "Caiu num Erro de lógica, mas utilizou uma ferramenta para evitar";
        }
        return "Caiu num Erro de Logica, irá recuar metade do valor que saiu no dado";
    }

    @Override
    public String move(int dado, Programmer player, int tamanhoTabuleiro, boolean levouCounter, ArrayList<Programmer> players) {
        if (!levouCounter){
            player.mover(-dado/2,tamanhoTabuleiro);
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
