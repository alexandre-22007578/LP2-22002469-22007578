package pt.ulusofona.lp2.deisiGreatGame;

import java.util.ArrayList;

public class BlueScreeOfDeath extends AbismoOrFerramenta {//O programador perde imediatamente o jogo

    public BlueScreeOfDeath(int id) {
        super(id);
        titulo = "Blue Screen of Death";
    }

    @Override
    public String reactToAbyssOrTool(boolean levouCounter) {

        if (levouCounter) {
            return "Caiu num Blue Screen of Death, mas utilizou uma ferramenta para evitar";
        }
        return "Caiu num Blue Screen of Death, Perdeu\n Better Luck next time;)";
    }

    @Override
    public String move(int dado, Programmer player, int tamanhoTabuleiro, boolean levouCounter, ArrayList<Programmer> players) {
        player.perdeu();
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
