package pt.ulusofona.lp2.deisiGreatGame;

public class ErroDeSintaxe extends AbismoOrFerramenta { // O programador recua 1 casa

    public ErroDeSintaxe(int id) {
        super(id);
        titulo = "Erro de sintaxe";
    }

    @Override
    public String reactToAbyssOrTool(boolean levouCounter) {

        if (levouCounter){
            return "Caiu num Erro de Sintaxe, mas utilizou uma ferramenta para evitar";
        }

        return "Caiu num Erro de Sintaxe, irá recuar 1 casa";
    }

    @Override
    public int getQuantidadePosicoesAnda(int dado, int posicaoAtual, int posicaoAnterior, int posicaoAnterior2, boolean levouCounter) throws Exception {

        if (levouCounter){
            return 0;
        }
        return 1;

    }



    @Override
    public boolean daCounter(String nomeAbismo) {
        return false;
    }


}
