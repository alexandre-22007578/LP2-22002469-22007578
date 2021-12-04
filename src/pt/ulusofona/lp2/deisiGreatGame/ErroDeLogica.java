package pt.ulusofona.lp2.deisiGreatGame;

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
    public int getQuantidadePosicoesAnda(int dado, int posicaoAtual, int posicaoAnterior, int posicaoAnterior2, boolean levouCounter) throws Exception {
        if (levouCounter){
            return 0;
        }
        return dado/2;
    }



    @Override
    public boolean daCounter(String nomeAbismo) {
        return false;
    }


}
