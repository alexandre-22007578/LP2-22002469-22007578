

package pt.ulusofona.lp2.deisiGreatGame;

import org.junit.Test;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.*;


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
        jogadores[1][2] = "C;C++;Java";
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
    public void test() {
        String[][] abyssesAndToolsInvalidaFerramentaIDInvalidoMaior = criaAbyssesAndTools();
        abyssesAndToolsInvalidaFerramentaIDInvalidoMaior[15][1] = "99";
        String[][] jogadoresValidos = criarJogadores();
        String mensagem = "ID ferramenta invalido";
        int tamanhoTabuleiro = 20;
        GameManager manager = new GameManager();
        try {
            manager.createInitialBoard(jogadoresValidos, tamanhoTabuleiro, abyssesAndToolsInvalidaFerramentaIDInvalidoMaior);
            fail("Deveria ter lançado uma exception");
        } catch (Exception ex) {
            assertEquals(mensagem, ex.getMessage());
        }
    }



    @Test
    public void testePlayerFirstName() {
        String[][] abismosEFerramentas = criaAbyssesAndTools();
        String[][] jogadoresValidos = criarJogadores();

        int tamanhoTabuleiro = 20;
        GameManager manager = new GameManager();
        try {
            manager.createInitialBoard(jogadoresValidos, tamanhoTabuleiro, abismosEFerramentas);

        } catch (Exception ex) {
            assertEquals("erro", ex.getMessage());
        }
        ArrayList<String> teste = new ArrayList<>();
        teste.add("PLAYER");
        teste.add("Alexandre");


        FunctionsKt.polyglots(manager);
        assertEquals("1 | Alexandre | 1 | No tools | Java | Em Jogo", FunctionsKt.playerFirstName(manager, teste));


    }

    //mudar para o fim

    @Test
    public void testeAbismosIDeTituloEID() {
        GameManager manager = new GameManager();
        int tamanhoTabuleiro = 20;
        String[][] jogadoresValidos = criarJogadores();

        String[][] abismos = new String[1][3];

        abismos[0][0] = "0";
        abismos[0][1] = "0";
        abismos[0][2] = "2";
        try {
            manager.createInitialBoard(jogadoresValidos, tamanhoTabuleiro, abismos);
            HashMap<Integer, AbismoOrFerramenta> abismoOrFerramentas = manager.getAbismosEFerramentas();
            assertEquals("Erro no titulo", "Erro de sintaxe", abismoOrFerramentas.get(2).toString());
            assertNull("Erro titulo", manager.getTitle(99));
            assertNull("Erro titulo", manager.getTitle(5));
            assertNull("Erro titulo", manager.getImagePng(5));
            assertNull("Erro titulo", manager.getImagePng(99));
            assertEquals("Erro no titulo", "Erro de sintaxe", manager.getTitle(2));
            assertEquals("Erro no titulo", "Erro de sintaxe.png", manager.getImagePng(2));
            assertEquals("Erro na imagem ultima posição", "podium.png", manager.getImagePng(tamanhoTabuleiro));
            assertEquals("Erro no ID", 0, abismoOrFerramentas.get(2).getId());

        } catch (Exception e) {

        }
    }



    @Test
    public void testeDuplicatedCodeSemFerramenta() {

        GameManager manager = new GameManager();
        int tamanhoTabuleiro = 10;
        String[][] jogadoresValidos = criarJogadores();

        String[][] abismos = new String[1][3];
        abismos[0][0] = "0";
        abismos[0][1] = "5";
        abismos[0][2] = "5";
        try {
            manager.createInitialBoard(jogadoresValidos, tamanhoTabuleiro, abismos);
            manager.moveCurrentPlayer(4);
            List<Programmer> programmers = manager.getProgrammers(5);
            assertEquals(1, programmers.size());
            assertSame("Alexandre", programmers.get(0).getName());
            manager.reactToAbyssOrTool();
            assertEquals(1, manager.getProgrammers(false).get(0).getPosicao());

        } catch (Exception e) {

        }
    }

    @Test
    public void testeDuplicatedCodeComHeranca() {

        GameManager manager = new GameManager();
        int tamanhoTabuleiro = 10;
        String[][] jogadoresValidos = new String[2][4];
        jogadoresValidos[0][0] = "4";
        jogadoresValidos[0][1] = "Guilherme";
        jogadoresValidos[0][2] = "Java";
        jogadoresValidos[0][3] = "Purple";

        jogadoresValidos[1][0] = "21";
        jogadoresValidos[1][1] = "Pizzi";
        jogadoresValidos[1][2] = "C";
        jogadoresValidos[1][3] = "Green";

        String[][] abismos = new String[2][3];
        abismos[0][0] = "0";
        abismos[0][1] = "5";
        abismos[0][2] = "5";

        abismos[1][0] = "1";
        abismos[1][1] = "0";
        abismos[1][2] = "2";
        try {
            manager.createInitialBoard(jogadoresValidos, tamanhoTabuleiro, abismos);

            manager.moveCurrentPlayer(1);
            manager.reactToAbyssOrTool();
            List<Programmer> programmers = manager.getProgrammers(false);
            assertEquals("Erro ao avançar para ganhar ferramenta Herança", "4 | Guilherme | 2 | Herança | Java | Em Jogo", programmers.get(0).toString());

            manager.moveCurrentPlayer(2);
            manager.reactToAbyssOrTool();
            programmers = manager.getProgrammers(false);
            assertEquals("Erro ao andar para casa vazia", "21 | Pizzi | 3 | No tools | C | Em Jogo", programmers.get(1).toString());

            manager.moveCurrentPlayer(3);
            manager.reactToAbyssOrTool();
            programmers = manager.getProgrammers(false);
            assertEquals("Erro caiu num duplicated code e comportamento errado", "4 | Guilherme | 5 | No tools | Java | Em Jogo", programmers.get(0).toString());

            manager.moveCurrentPlayer(2);
            manager.reactToAbyssOrTool();
            programmers = manager.getProgrammers(false);
            assertEquals("Erro depois de cair no duplicated code sem ter ferramenta", "21 | Pizzi | 3 | No tools | C | Em Jogo", programmers.get(1).toString());


        } catch (Exception e) {
        }
    }

    @Test
    public void testeErroSintaxe() {

        GameManager manager = new GameManager();
        int tamanhoTabuleiro = 10;
        String[][] jogadoresValidos = criarJogadores();

        String[][] abismos = new String[1][3];
        abismos[0][0] = "0";
        abismos[0][1] = "0";
        abismos[0][2] = "6";
        try {
            manager.createInitialBoard(jogadoresValidos, tamanhoTabuleiro, abismos);
            manager.moveCurrentPlayer(5);
            List<Programmer> programmers = manager.getProgrammers(6);
            assertEquals(1, programmers.size());
            assertSame("Alexandre", programmers.get(0).getName());
            manager.reactToAbyssOrTool();
            assertEquals(5, manager.getProgrammers(false).get(0).getPosicao());


        } catch (Exception e) {
        }

    }

    @Test
    public void testeErroSintaxeComAjudaProfessor() {
        GameManager manager = new GameManager();
        int tamanhoTabuleiro = 10;
        String[][] jogadoresValidos = new String[2][4];
        jogadoresValidos[0][0] = "4";
        jogadoresValidos[0][1] = "Guilherme";
        jogadoresValidos[0][2] = "Java";
        jogadoresValidos[0][3] = "Purple";

        jogadoresValidos[1][0] = "21";
        jogadoresValidos[1][1] = "Pizzi";
        jogadoresValidos[1][2] = "C";
        jogadoresValidos[1][3] = "Green";

        String[][] abismos = new String[2][3];
        abismos[0][0] = "0";
        abismos[0][1] = "0";
        abismos[0][2] = "5";

        abismos[1][0] = "1";
        abismos[1][1] = "5";
        abismos[1][2] = "2";
        try {
            manager.createInitialBoard(jogadoresValidos, tamanhoTabuleiro, abismos);

            manager.moveCurrentPlayer(1);
            manager.reactToAbyssOrTool();
            List<Programmer> programmers = manager.getProgrammers(false);
            assertEquals("Erro ao avançar para ganhar ferramenta Herança", "4 | Guilherme | 2 | Ajuda Do Professor | Java | Em Jogo", programmers.get(0).toString());

            manager.moveCurrentPlayer(2);
            manager.reactToAbyssOrTool();
            programmers = manager.getProgrammers(false);
            assertEquals("Erro ao andar para casa vazia", "21 | Pizzi | 3 | No tools | C | Em Jogo", programmers.get(1).toString());

            manager.moveCurrentPlayer(3);
            manager.reactToAbyssOrTool();
            programmers = manager.getProgrammers(false);
            assertEquals("Erro caiu num erro de sintaxe com ferramenta ajuda do professor e comportamento errado", "4 | Guilherme | 5 | No tools | Java | Em Jogo", programmers.get(0).toString());

            manager.moveCurrentPlayer(2);
            manager.reactToAbyssOrTool();
            programmers = manager.getProgrammers(false);
            assertEquals("Erro depois de cair no duplicated code sem ter ferramenta", "21 | Pizzi | 4 | No tools | C | Em Jogo", programmers.get(1).toString());
        } catch (Exception e) {
        }

    }

    @Test
    public void testeErroSintaxeComIDE() {
        GameManager manager = new GameManager();
        int tamanhoTabuleiro = 10;
        String[][] jogadoresValidos = new String[2][4];
        jogadoresValidos[0][0] = "4";
        jogadoresValidos[0][1] = "Guilherme";
        jogadoresValidos[0][2] = "Java";
        jogadoresValidos[0][3] = "Purple";

        jogadoresValidos[1][0] = "21";
        jogadoresValidos[1][1] = "Pizzi";
        jogadoresValidos[1][2] = "C";
        jogadoresValidos[1][3] = "Green";

        String[][] abismos = new String[2][3];
        abismos[0][0] = "0";
        abismos[0][1] = "0";
        abismos[0][2] = "5";

        abismos[1][0] = "1";
        abismos[1][1] = "4";
        abismos[1][2] = "2";
        try {
            manager.createInitialBoard(jogadoresValidos, tamanhoTabuleiro, abismos);

            manager.moveCurrentPlayer(1);
            manager.reactToAbyssOrTool();
            List<Programmer> programmers = manager.getProgrammers(false);
            assertEquals("Erro ao avançar para ganhar ferramenta Herança", "4 | Guilherme | 2 | IDE | Java | Em Jogo", programmers.get(0).toString());

            manager.moveCurrentPlayer(2);
            manager.reactToAbyssOrTool();
            programmers = manager.getProgrammers(false);
            assertEquals("Erro ao andar para casa vazia", "21 | Pizzi | 3 | No tools | C | Em Jogo", programmers.get(1).toString());

            manager.moveCurrentPlayer(3);
            manager.reactToAbyssOrTool();
            programmers = manager.getProgrammers(false);
            assertEquals("Erro caiu num erro de sintaxe com ferramenta e comportamento errado", "4 | Guilherme | 5 | No tools | Java | Em Jogo", programmers.get(0).toString());

            manager.moveCurrentPlayer(2);
            manager.reactToAbyssOrTool();
            programmers = manager.getProgrammers(false);
            assertEquals("Erro depois de cair no duplicated code sem ter ferramenta", "21 | Pizzi | 4 | No tools | C | Em Jogo", programmers.get(1).toString());
        } catch (Exception e) {
        }
    }

    @Test
    public void testeErroLogica() {

        GameManager manager = new GameManager();
        int tamanhoTabuleiro = 10;
        String[][] jogadoresValidos = criarJogadores();

        String[][] abismos = new String[1][3];
        abismos[0][0] = "0";
        abismos[0][1] = "1";
        abismos[0][2] = "6";
        try {
            manager.createInitialBoard(jogadoresValidos, tamanhoTabuleiro, abismos);
            manager.moveCurrentPlayer(5);
            List<Programmer> programmers = manager.getProgrammers(6);
            assertEquals(1, programmers.size());
            assertSame("Alexandre", programmers.get(0).getName());
            manager.reactToAbyssOrTool();
            assertEquals(4, manager.getProgrammers(false).get(0).getPosicao());
        } catch (Exception e) {
        }
    }

    @Test
    public void testeLogicaComAjudaDoProfessor() {
        GameManager manager = new GameManager();
        int tamanhoTabuleiro = 10;
        String[][] jogadoresValidos = new String[2][4];
        jogadoresValidos[0][0] = "1";
        jogadoresValidos[0][1] = "Alexandre";
        jogadoresValidos[0][2] = "Java";
        jogadoresValidos[0][3] = "Purple";

        jogadoresValidos[1][0] = "2";
        jogadoresValidos[1][1] = "Daniel";
        jogadoresValidos[1][2] = "C";
        jogadoresValidos[1][3] = "Green";

        String[][] abismos = new String[2][3];
        abismos[0][0] = "0";
        abismos[0][1] = "1";
        abismos[0][2] = "5";

        abismos[1][0] = "1";
        abismos[1][1] = "5";
        abismos[1][2] = "2";
        try {
            manager.createInitialBoard(jogadoresValidos, tamanhoTabuleiro, abismos);

            manager.moveCurrentPlayer(1);
            manager.reactToAbyssOrTool();
            manager.moveCurrentPlayer(2);
            manager.reactToAbyssOrTool();
            List<Programmer> programmers = manager.getProgrammers(true);
            assertEquals("Erro ao avançar para casa com ferramenta", "1 | Alexandre | 2 | Ajuda Do Professor | Java | Em Jogo", programmers.get(0).toString());
            assertEquals("Erro ao avançar para casa sem ferramenta", "2 | Daniel | 3 | No tools | C | Em Jogo", programmers.get(1).toString());
            manager.moveCurrentPlayer(3);
            manager.reactToAbyssOrTool();
            manager.moveCurrentPlayer(2);
            manager.reactToAbyssOrTool();
            programmers = manager.getProgrammers(true);
            assertEquals("Erro ao avançar erro de logica com ferramenta", "1 | Alexandre | 5 | No tools | Java | Em Jogo", programmers.get(0).toString());
            assertEquals("Erro ao avançar erro de logica sem ferramenta", "2 | Daniel | 4 | No tools | C | Em Jogo", programmers.get(1).toString());
        } catch (Exception e) {
        }
    }

    @Test
    public void testeLogicaTSTUnitarios() {
        GameManager manager = new GameManager();
        int tamanhoTabuleiro = 10;
        String[][] jogadoresValidos = new String[2][4];
        jogadoresValidos[0][0] = "1";
        jogadoresValidos[0][1] = "Alexandre";
        jogadoresValidos[0][2] = "Java";
        jogadoresValidos[0][3] = "Purple";

        jogadoresValidos[1][0] = "2";
        jogadoresValidos[1][1] = "Daniel";
        jogadoresValidos[1][2] = "C";
        jogadoresValidos[1][3] = "Green";

        String[][] abismos = new String[2][3];
        abismos[0][0] = "0";
        abismos[0][1] = "1";
        abismos[0][2] = "5";

        abismos[1][0] = "1";
        abismos[1][1] = "2";
        abismos[1][2] = "2";
        try {
            manager.createInitialBoard(jogadoresValidos, tamanhoTabuleiro, abismos);

            manager.moveCurrentPlayer(1);
            manager.reactToAbyssOrTool();
            manager.moveCurrentPlayer(2);
            manager.reactToAbyssOrTool();
            List<Programmer> programmers = manager.getProgrammers(true);
            assertEquals("Erro ao avançar para casa com ferramenta", "1 | Alexandre | 2 | Testes unitários | Java | Em Jogo", programmers.get(0).toString());
            assertEquals("Erro ao avançar para casa sem ferramenta", "2 | Daniel | 3 | No tools | C | Em Jogo", programmers.get(1).toString());
            manager.moveCurrentPlayer(3);
            manager.reactToAbyssOrTool();
            manager.moveCurrentPlayer(2);
            manager.reactToAbyssOrTool();
            programmers = manager.getProgrammers(true);
            assertEquals("Erro ao avançar erro de logica com ferramenta", "1 | Alexandre | 5 | No tools | Java | Em Jogo", programmers.get(0).toString());
            assertEquals("Erro ao avançar erro de logica sem ferramenta", "2 | Daniel | 4 | No tools | C | Em Jogo", programmers.get(1).toString());
        } catch (Exception e) {
        }
    }

    @Test
    public void testeGameIsOver() {
        int tabuleiro = 6;
        GameManager manager = new GameManager();
        String[][] jogadoresValidos = new String[3][4];
        jogadoresValidos[0][0] = "1";
        jogadoresValidos[0][1] = "Alexandre";
        jogadoresValidos[0][2] = "Java";
        jogadoresValidos[0][3] = "Purple";

        jogadoresValidos[1][0] = "2";
        jogadoresValidos[1][1] = "Daniel";
        jogadoresValidos[1][2] = "C";
        jogadoresValidos[1][3] = "Green";

        jogadoresValidos[2][0] = "3";
        jogadoresValidos[2][1] = "Joao";
        jogadoresValidos[2][2] = "C++";
        jogadoresValidos[2][3] = "Blue";

        String[][] abismo = new String[1][3];

        abismo[0][0] = "0";
        abismo[0][1] = "1";
        abismo[0][2] = "1";
        try {
            manager.createInitialBoard(jogadoresValidos, tabuleiro, abismo);
            manager.moveCurrentPlayer(5);
            manager.reactToAbyssOrTool();
            assertTrue("Acabou o jogo", manager.gameIsOver());
            List<String> resultados = manager.getGameResults();
            resultados.forEach(System.out::println);

            JPanel creditos = manager.getAuthorsPanel();


        } catch (Exception e) {
        }
    }

    @Test
    public void testeMoverComCicloInfinito() {
        int tabuleiro = 6;
        GameManager manager = new GameManager();
        String[][] jogadoresValidos = new String[2][4];
        jogadoresValidos[0][0] = "1";
        jogadoresValidos[0][1] = "Alexandre";
        jogadoresValidos[0][2] = "Java";
        jogadoresValidos[0][3] = "Purple";

        jogadoresValidos[1][0] = "2";
        jogadoresValidos[1][1] = "Daniel";
        jogadoresValidos[1][2] = "C";
        jogadoresValidos[1][3] = "Green";

        String[][] abismo = new String[1][3];

        abismo[0][0] = "0";
        abismo[0][1] = "8";
        abismo[0][2] = "4";
        try {
            manager.createInitialBoard(jogadoresValidos, tabuleiro, abismo);
            assertFalse("erro mover mais de 6 casas", manager.moveCurrentPlayer(7));
            assertFalse("erro mover 0 casas", manager.moveCurrentPlayer(0));
            assertFalse("erro mover casas negativas", manager.moveCurrentPlayer(-55));

            manager.moveCurrentPlayer(3);
            manager.reactToAbyssOrTool();
            manager.moveCurrentPlayer(2);
            manager.reactToAbyssOrTool();

            assertFalse("erro mover num ciclo infinito", manager.moveCurrentPlayer(2));
            manager.reactToAbyssOrTool();
            manager.moveCurrentPlayer(1);
            manager.reactToAbyssOrTool();
            List<Programmer> programmers = manager.getProgrammers(false);
            assertTrue(programmers.get(1).getStuck());
            assertFalse(programmers.get(0).getStuck());


        } catch (Exception e) {
        }
    }

    @Test
    public void getProgrammersPosition() {
        int tabuleiro = 6;
        GameManager manager = new GameManager();
        String[][] jogadoresValidos = new String[2][4];
        jogadoresValidos[0][0] = "1";
        jogadoresValidos[0][1] = "Alexandre";
        jogadoresValidos[0][2] = "Java";
        jogadoresValidos[0][3] = "Purple";

        jogadoresValidos[1][0] = "2";
        jogadoresValidos[1][1] = "Daniel";
        jogadoresValidos[1][2] = "C";
        jogadoresValidos[1][3] = "Green";

        String[][] abismo = new String[1][3];

        abismo[0][0] = "0";
        abismo[0][1] = "8";
        abismo[0][2] = "4";
        try {
            manager.createInitialBoard(jogadoresValidos, tabuleiro, abismo);
            assertNull(manager.getProgrammers(100));
            assertNull(manager.getProgrammers(5));

        } catch (Exception e) {
        }
    }

    @Test
    public void testeBSOD() {
        GameManager manager = new GameManager();
        int tamanhoTabuleiro = 20;
        String[][] jogadoresValidos = criarJogadores();

        String[][] abismos = new String[2][3];

        abismos[0][0] = "0";
        abismos[0][1] = "7";
        abismos[0][2] = "2";
        abismos[1][0] = "0";
        abismos[1][1] = "7";
        abismos[1][2] = "6";
        try {
            manager.createInitialBoard(jogadoresValidos, tamanhoTabuleiro, abismos);

            manager.moveCurrentPlayer(2);
            manager.reactToAbyssOrTool();
            manager.moveCurrentPlayer(2);
            manager.reactToAbyssOrTool();
            manager.moveCurrentPlayer(1);
            manager.reactToAbyssOrTool();
            manager.moveCurrentPlayer(1);
            manager.reactToAbyssOrTool();
            List<Programmer> programmers = manager.getProgrammers(true);
            assertEquals("Erro ao avançar para posição depois do BLSO teste 1", "1 | Alexandre | 3 | No tools | Java | Em Jogo", programmers.get(0).toString());
            assertEquals("Erro ao avançar para posição depois do BLSO teste 1", "2 | Daniel | 3 | No tools | C; C++; Java | Em Jogo", programmers.get(1).toString());
            assertEquals("Erro ao avançar para BLSO teste 1", "3 | Ricardo | 2 | No tools | Ruby | Derrotado", programmers.get(2).toString());
            assertEquals("Erro ao avançar para BLSO teste 1", "4 | Joao | 2 | No tools | kotlin | Derrotado", programmers.get(3).toString());

            for (Programmer programmer : programmers) {
                if (manager.getCurrentPlayerID() == programmer.getId()) {
                    manager.moveCurrentPlayer(1);
                    manager.reactToAbyssOrTool();
                    manager.moveCurrentPlayer(1);
                    manager.reactToAbyssOrTool();

                }

            }
            programmers = manager.getProgrammers(true);
            assertEquals("Erro ao avançar depois de jogadores caírem no BSOD teste 2", "1 | Alexandre | 4 | No tools | Java | Em Jogo", programmers.get(0).toString());
            assertEquals("Erro ao avançar depois de jogadores caírem no BSOD teste 2", "2 | Daniel | 4 | No tools | C; C++; Java | Em Jogo", programmers.get(1).toString());

            for (Programmer programmer : programmers) {
                if (manager.getCurrentPlayerID() == programmer.getId()) {
                    manager.moveCurrentPlayer(1);
                    manager.reactToAbyssOrTool();

                }

            }
            programmers = manager.getProgrammers(true);

            assertEquals("Erro ao avançar depois de jogadores caírem no BSOD teste 3", "1 | Alexandre | 5 | No tools | Java | Em Jogo", programmers.get(0).toString());
            assertEquals("Erro ao avançar depois de jogadores caírem no BSOD teste 3", "2 | Daniel | 5 | No tools | C; C++; Java | Em Jogo", programmers.get(1).toString());

            assertFalse("O jogo nao acabou", manager.gameIsOver());


            if (manager.getCurrentPlayerID() == 1) {
                manager.moveCurrentPlayer(1);
                manager.reactToAbyssOrTool();

            }
            if (manager.getCurrentPlayerID() == 2) {
                manager.moveCurrentPlayer(2);
                manager.reactToAbyssOrTool();


            }
            assertEquals("Erro ao avançar depois de jogadores caírem no BSOD teste 4", "1 | Alexandre | 6 | No tools | Java | Derrotado", programmers.get(0).toString());
            assertEquals("Erro ao avançar depois de jogadores caírem no BSOD teste 4", "2 | Daniel | 7 | No tools | C; C++; Java | Em Jogo", programmers.get(1).toString());

            assertTrue("O jogo devia ter acabado", manager.gameIsOver());

        } catch (Exception e) {
        }
    }

    @Test
    public void getProgrammersInfo() {
        int tabuleiro = 6;
        GameManager manager = new GameManager();
        String[][] jogadoresValidos = new String[2][4];
        jogadoresValidos[0][0] = "1";
        jogadoresValidos[0][1] = "Alexandre";
        jogadoresValidos[0][2] = "Java";
        jogadoresValidos[0][3] = "Purple";

        jogadoresValidos[1][0] = "2";
        jogadoresValidos[1][1] = "Daniel";
        jogadoresValidos[1][2] = "C";
        jogadoresValidos[1][3] = "Green";

        String[][] abismo = new String[1][3];

        abismo[0][0] = "0";
        abismo[0][1] = "8";
        abismo[0][2] = "4";
        try {
            manager.createInitialBoard(jogadoresValidos, tabuleiro, abismo);
            assertEquals("erro no ProgremmersInfo", "Alexandre : No tools | Daniel : No tools", manager.getProgrammersInfo());

        } catch (Exception e) {
        }
    }
}


