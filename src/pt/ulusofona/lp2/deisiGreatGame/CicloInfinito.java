package pt.ulusofona.lp2.deisiGreatGame;

import java.util.ArrayList;

public class CicloInfinito extends AbismoOrFerramenta {

    //O programador fica preso na casa onde está até que lá apareça outro programador para o ajudar. O programador que aparecer para ajudar, fica ele próprio preso (mas liberta o que já lá estava).
    //Caso o programador que aparece tenha uma ferramenta que permita livrar-se do abismo, ele não fica preso mas também já não liberta o programador que lá estava.

    public CicloInfinito(int id) {
        super(id);
        titulo = "Ciclo infinito";
    }

    @Override
    public String reactToAbyssOrTool(boolean levouCounter) {

        if (levouCounter) {
            return "Caiu num Ciclo infinito, mas utilizou uma ferramenta para evitar";
        }
        return "Caiu num Ciclo infinito, Irá ficar preso até alguém o vir salvar;)";
    }

    @Override
    public String move(int dado, Programmer player, int tamanhoTabuleiro, boolean levouCounter, ArrayList<Programmer> players) {
        if (!levouCounter) {
            for (Programmer jogador : players) {
                if (jogador.getPosicao() == player.getPosicao() && jogador.getId() != player.getId()) {
                    player.mudarEstadoLivre();
                }
            }
        }
        player.mudarEstadoPreso();
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
