package pt.ulusofona.lp2.deisiGreatGame;

public class AjudaDoProfessor extends AbismoOrFerramenta{// Evita os efeitos de:Erro de Sintaxe,Erro de Lógica, Exception, File Not Found Exception
    public AjudaDoProfessor(int id) {
        super(id);
        titulo="Ajuda Do Professor";
    }

    @Override
    public String reactToAbyssOrTool(boolean levouCounter) {

        return "Caiu na Ajuda Do Professor, esta ferramenta foi adicionada ao seu conjunto de ferramentas";
    }

    @Override
    public int getQuantidadePosicoesAnda(int dado, int posicaoAtual, int posicaoAnterior, int posicaoAnterior2, boolean levouCounter) throws Exception {
        return 0;
    }

    @Override
    public boolean souFerramenta() {
        return true;
    }


    @Override
    public boolean daCounter(String nomeAbismo) {
        return nomeAbismo.equals("Erro de sintaxe") || nomeAbismo.equals("Erro de lógica") || nomeAbismo.equals("Exception") || nomeAbismo.equals("File Not Found Exception");
    }
}
