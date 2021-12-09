package pt.ulusofona.lp2.deisiGreatGame;

import java.util.ArrayList;

public class CrashAkaRebentanco extends AbismoOrFerramenta{// O programador volta à primeira casa do jogo.
    public CrashAkaRebentanco(int id) {
        super(id);
        titulo="Crash (aka Rebentanço)";
    }

    @Override
    public String reactToAbyssOrTool(boolean levouCounter) {
        if (levouCounter){
            return "Caiu num Crash (aka Rebentanço), mas utilizou uma ferramenta para evitar";
        }
        return "Caiu num Crash (aka Rebentanço), irá voltar ao inicio do tabuleiro";
    }

    @Override
    public String move(int dado, Programmer player, int tamanhoTabuleiro, boolean levouCounter, ArrayList<Programmer> players) {
        if (!levouCounter){
            player.mover(-player.getPosicao(),tamanhoTabuleiro);
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
