package pt.ulusofona.lp2.deisiGreatGame;

public class FileNotFoundException extends AbismoOrFerramenta{// O programador recua 3 casa
    public FileNotFoundException(int id) {
        super(id);
        titulo="File Not Found Exception";
    }

    @Override
    public String reactToAbyssOrTool(boolean levouCounter) {
        if (levouCounter){
            return "Caiu num File Not Found Exception, mas utilizou uma ferramenta para evitar";
        }
        return "Caiu num File Not Found Exception, irá recuar 3 casas";
    }

    @Override
    public int getQuantidadePosicoesAnda(int dado, int posicaoAtual, int posicaoAnterior, int posicaoAnterior2, boolean levouCounter) throws Exception {
        if (levouCounter){
            return 0;
        }
        return 3;
    }



    @Override
    public boolean daCounter(String nomeAbismo) {
        return false;
    }
}
