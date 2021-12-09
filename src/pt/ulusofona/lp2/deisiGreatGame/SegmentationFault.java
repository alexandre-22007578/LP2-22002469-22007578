package pt.ulusofona.lp2.deisiGreatGame;

import java.util.ArrayList;

public class SegmentationFault extends AbismoOrFerramenta {
    //Este Abismo apenas é activado caso existam dois ou mais programadores na mesma casa.
    //Todos os jogadores nessa casa recuam 3 casas.Caso apenas esteja um programador neste Abismo, então não existe nenhum efeito a aplicar.
    public SegmentationFault(int id) {
        super(id);
        titulo = "Segmentation Fault";
    }

    @Override
    public String reactToAbyssOrTool(boolean levouCounter) {

        if (levouCounter) {
            return "Caiu num Segmentation Fault, mas utilizou uma ferramenta para evitar";
        }
        return "Caiu num Segmentation Fault, Não acontece nada, pois está sozinho na casa";
    }

    @Override
    public String move(int dado, Programmer player, int tamanhoTabuleiro, boolean levouCounter, ArrayList<Programmer> players) {
        if (!levouCounter) {


            int count = 0;
            for (Programmer programmer : players) {
                if (player.getPosicao() == programmer.getPosicao()) {
                    count++;
                }
            }
            if (count >= 2) {
                for (Programmer jogador : players) {

                    if (player.getPosicao() == jogador.getPosicao()) {
                        player.mover(-3, tamanhoTabuleiro);

                    }
                }

                return "Caiu num Segmentation Fault, Todos os jogadores nesta casa recuam 3 casas)";
            }
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
