package pt.ulusofona.lp2.deisiGreatGame;

public class CicloInfinito extends AbismoOrFerramenta{

    //O programador fica preso na casa onde está até que lá apareça outro programador para o ajudar. O programador que aparecer para ajudar, fica ele próprio preso (mas liberta o que já lá estava).
    //Caso o programador que aparece tenha uma ferramenta que permita livrar-se do abismo, ele não fica preso mas também já não liberta o programador que lá estava.

    public CicloInfinito(int id) {
        super(id);
        titulo="Ciclo infinito";
    }

    @Override
    public String reactToAbyssOrTool(boolean levouCounter) {
        if (levouCounter){
            return "Caiu num Ciclo infinito, mas utilizou uma ferramenta para evitar";
        }
        return "Caiu num Ciclo infinito, Irá ficar preso até alguém o vir salvar;)";
    }

    @Override
    public int getQuantidadePosicoesAnda(int dado,int posicaoAtual, int posicaoAnterior, int posicaoAnterior2, boolean levouCounter) throws Exception {
        throw new Exception(titulo);
    }



    @Override
    public boolean daCounter(String nomeAbismo) {
        return false;
    }
}
