package pt.ulusofona.lp2.deisiGreatGame;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class TestesUnitariosAutomaticos {

    private String[][] criarJogadores() {

        String[][] jogadores = new String[4][4];
        // ids
        for (int i = 0; i < 4; i++) {
            jogadores[i][0] = (i + 1) + "";
        }
        // nomes
        jogadores[0][1] = "Alexandre";
        jogadores[1][1] = "Daniel";
        jogadores[2][1] = "Ricardo";
        jogadores[3][1] = "Joao";

        // linguagens programação
        jogadores[0][2] = "Java";
        jogadores[1][2] = "C;C++";
        jogadores[2][2] = "Ruby";
        jogadores[3][2] = "kotlin";

        // linguagens programação
        jogadores[0][3] = "Purple";
        jogadores[1][3] = "Blue";
        jogadores[2][3] = "Green";
        jogadores[3][3] = "Brown";

        return jogadores;
    }

    private String[][] criaAbyssesAndTools() {

        String[][] abyssesAndToolsValidos = new String[16][3];


        // abismo ou ferramenta e os ids
        for (int i = 0; i < abyssesAndToolsValidos.length; i++) {
            if (i < 10) {
                abyssesAndToolsValidos[i][0] = "0";
                abyssesAndToolsValidos[i][1] = i + ""; // os 10 abismo

            } else {
                abyssesAndToolsValidos[i][0] = "1";
                abyssesAndToolsValidos[i][1] = (i - 10) + "";// as 6 ferramentas
            }
            abyssesAndToolsValidos[i][2] = i + 1 + "";// posicao de cada um
        }


        return abyssesAndToolsValidos;

    }

    @Test
    public void testCreateInicialBoard() {
        GameManager manager = new GameManager();
        int tamanhoTabuleiro = 20;
        String[][] abyssesAndToolsValidos = criaAbyssesAndTools();
        String[][] jogadoresValidos = criarJogadores();
        String[][] jogadoresComNomeInvalidoNull =criarJogadores();
        String[][] jogadoresComNomeInvalidoVazio = criarJogadores();
        String[][] jogadoresComIDInvalidoString = criarJogadores();
        String[][] jogadoresComIDInvalidoRepetido = criarJogadores();
        String[][] jogadoresComCorInvalidaRepetida =criarJogadores();
        String[][] jogadoresComCorInvalidaNenhumaDas4Possiveis = criarJogadores();
        String[][] abyssesAndToolsInvalidoPrimeiraPosicao2 = criaAbyssesAndTools();
        String[][] abyssesAndToolsInvalidoAbismoIDInvalidoMaior = criaAbyssesAndTools();
        String[][] abyssesAndToolsInvalidoAbismoIDInvalidoNegativo = criaAbyssesAndTools();
        String[][] abyssesAndToolsInvalidaFerramentaIDInvalidoMaior = criaAbyssesAndTools();
        String[][] abyssesAndToolsInvalidaFerramentaIDInvalidoNegativo = criaAbyssesAndTools();
        String[][] abyssesAndToolsInvalidaPosicao = criaAbyssesAndTools();

        jogadoresComNomeInvalidoNull[0][1] = null;
        jogadoresComNomeInvalidoVazio[0][1] = "";
        jogadoresComIDInvalidoString[0][0] = "id invalido";
        jogadoresComIDInvalidoRepetido[0][0] = jogadoresComIDInvalidoRepetido[1][0];
        jogadoresComCorInvalidaRepetida[0][3] = jogadoresComCorInvalidaRepetida[1][3];
        jogadoresComCorInvalidaNenhumaDas4Possiveis[0][3] = "AMARELO";
        abyssesAndToolsInvalidoPrimeiraPosicao2[0][0] = "2";
        abyssesAndToolsInvalidoAbismoIDInvalidoMaior[0][1] = "10";
        abyssesAndToolsInvalidoAbismoIDInvalidoNegativo[0][1] = "-10";
        abyssesAndToolsInvalidaFerramentaIDInvalidoMaior[15][1]="99";
        abyssesAndToolsInvalidaFerramentaIDInvalidoNegativo[15][1]="-99";
        abyssesAndToolsInvalidaPosicao[0][2]=(tamanhoTabuleiro+1)+"";


        assertFalse("Erro nomes null", manager.createInitialBoard(jogadoresComNomeInvalidoNull, tamanhoTabuleiro, abyssesAndToolsValidos));
        assertFalse("Erro nomes vazio", manager.createInitialBoard(jogadoresComNomeInvalidoVazio, tamanhoTabuleiro, abyssesAndToolsValidos));
        assertFalse("Erro ids repetidos", manager.createInitialBoard(jogadoresComIDInvalidoRepetido, tamanhoTabuleiro, abyssesAndToolsValidos));
        assertFalse("Erro id fora da gama", manager.createInitialBoard(jogadoresComIDInvalidoString, tamanhoTabuleiro, abyssesAndToolsValidos));
        assertFalse("Erro cor repetida", manager.createInitialBoard(jogadoresComCorInvalidaRepetida, tamanhoTabuleiro, abyssesAndToolsValidos));
        assertFalse("Erro cor fora das 4 possíveis", manager.createInitialBoard(jogadoresComCorInvalidaNenhumaDas4Possiveis, tamanhoTabuleiro, abyssesAndToolsValidos));
        assertFalse("Erro abyssesAndTools primeira posicao 2", manager.createInitialBoard(jogadoresValidos, tamanhoTabuleiro, abyssesAndToolsInvalidoPrimeiraPosicao2));
        assertFalse("Erro abismo id negativo", manager.createInitialBoard(jogadoresValidos, tamanhoTabuleiro, abyssesAndToolsInvalidoAbismoIDInvalidoNegativo));
        assertFalse("Erro abismo id maior", manager.createInitialBoard(jogadoresValidos, tamanhoTabuleiro, abyssesAndToolsInvalidoAbismoIDInvalidoMaior));
        assertFalse("Erro ferramenta id negativo", manager.createInitialBoard(jogadoresValidos, tamanhoTabuleiro, abyssesAndToolsInvalidaFerramentaIDInvalidoNegativo));
        assertFalse("Erro ferramenta id maior", manager.createInitialBoard(jogadoresValidos, tamanhoTabuleiro, abyssesAndToolsInvalidaFerramentaIDInvalidoMaior));
        assertFalse("Erro abyssesAndTools posicao invalida", manager.createInitialBoard(jogadoresValidos, tamanhoTabuleiro, abyssesAndToolsInvalidaPosicao));
        assertTrue("Erro com os dados validos", manager.createInitialBoard(jogadoresValidos, tamanhoTabuleiro, abyssesAndToolsValidos));

    }

}
