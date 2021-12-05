package pt.ulusofona.lp2.deisiGreatGame;

public class EfeitosSecundarios extends AbismoOrFerramenta{// O programador recua para a posição onde estava há 2 movimentos atrás.
    public EfeitosSecundarios(int id) {
        super(id);
        titulo="Efeitos secundários";
    }

    @Override
    public String reactToAbyssOrTool(boolean levouCounter) {
        if (levouCounter){
            return "Caiu nos Efeitos secundários, mas utilizou uma ferramenta para evitar";
        }
        return "Caiu nos Efeitos secundários, irá recuar até á casa onde estava no inicio do turno anterior";
    }

    @Override
    public int getQuantidadePosicoesAnda(int dado, int posicaoAtual, int posicaoAnterior, int posicaoAnterior2, boolean levouCounter) throws Exception {
        if (levouCounter){
            return 0;
        }
        return posicaoAtual- posicaoAnterior2;
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
