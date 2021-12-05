package pt.ulusofona.lp2.deisiGreatGame;

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
    public int getQuantidadePosicoesAnda(int dado, int posicaoAtual, int posicaoAnterior, int posicaoAnterior2, boolean levouCounter) throws Exception {
        if (levouCounter){
            return 0;
        }
        return posicaoAtual-posicaoAnterior;
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
