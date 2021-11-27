package pt.ulusofona.lp2.deisiGreatGame;

public class Abismo extends AbismoOrFerramenta{

    public Abismo(int id) {
        super(id);
        darTitulo();
    }

    @Override
    void darTitulo() {
        switch (id) {
            case 0 -> titulo = "Erro de sintaxe"; // O programador recua 1 casa
            case 1 -> titulo = "Erro de lógica"; // O programador recua N casas, sendo N metade do valor que tiver saído no dado, arredondado para baixo.
            case 2 -> titulo = "Exception"; // O programador recua 2 casa
            case 3 -> titulo = "File Not Found Exception"; // O programador recua 3 casa
            case 4 -> titulo = "Crash (aka Rebentanço)"; // O programador volta à primeira casa do jogo.
            case 5 -> titulo = "Duplicated Code"; // O programador recua até à casa onde estava antes de chegar a esta casa
            case 6 -> titulo = "Efeitos secundários"; // O programador recua para a posição onde estava há 2 movimentos atrás.
            case 7 -> titulo = "Blue Screen of Death"; //O programador perde imediatamente o jogo
            case 8 -> titulo = "Ciclo infinito";

            //O programador fica preso na casa onde está até que lá apareça outro programador para o ajudar. O programador que aparecer para ajudar, fica ele próprio preso (mas liberta o que já lá estava).
            //Caso o programador que aparece tenha uma ferramenta que permita livrar-se do abismo, ele não fica preso mas também já não liberta o programador que lá estava.
            case 9 -> titulo = "Segmentation Fault";

            //Este Abismo apenas é activado caso existam dois ou mais programadores na mesma casa.
            //Todos os jogadores nessa casa recuam 3 casas.Caso apenas esteja um programador neste Abismo, então não existe nenhum efeito a aplicar.

        }
    }
}
