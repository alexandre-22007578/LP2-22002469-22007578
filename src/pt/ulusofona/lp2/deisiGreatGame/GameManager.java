package pt.ulusofona.lp2.deisiGreatGame;

import javax.swing.*;
import java.util.*;

public class GameManager {
    private int dado;
    private int tamanhoTabueiro;
    private int playerAtual;
    private int numeroTotalDeTurnos;


    ArrayList<Programmer> players = new ArrayList<>();
    HashMap<Integer, AbismoOrFerramenta> abismoEFerramentas = new HashMap<>();


    public GameManager() {
    }

    public boolean createInitialBoard(String[][] playerInfo, int worldSize) {
        numeroTotalDeTurnos = 1;
        players.clear();
        int id;
        String nome;
        ProgrammerColor color;
        String linguagensProgramacao;
        HashSet<Integer> ids = new HashSet<>();
        playerAtual = 0;

        if (worldSize < playerInfo.length * 2 || playerInfo.length < 2 || playerInfo.length > 4) {
            return false;
        }
        try {
            for (String[] strings : playerInfo) {
                id = Integer.parseInt(String.valueOf(strings[0]));
                if (strings[1] == null || strings[1].equals("")) {
                    return false;
                }
                nome = strings[1];
                linguagensProgramacao = String.valueOf(strings[2]);
                switch (strings[3]) {
                    case "Purple":
                        color = ProgrammerColor.PURPLE;
                        break;
                    case "Green":
                        color = ProgrammerColor.GREEN;
                        break;
                    case "Brown":
                        color = ProgrammerColor.BROWN;
                        break;
                    case "Blue":
                        color = ProgrammerColor.BLUE;
                        break;
                    default:
                        return false;
                }
                if (id < 1 || !(ids.add(id))) {
                    return false;
                }
                players.add(new Programmer(id, nome, color, linguagensProgramacao));
                this.tamanhoTabueiro = worldSize;

            }

        } catch (Exception e) {
            return false;
        }

        players.sort(Comparator.comparingInt(Programmer::getId));
        return true;
    }

    public boolean createInitialBoard(String[][] playerInfo, int worldSize, String[][] abyssesAndTools) {

        abismoEFerramentas.clear();
        int abismoOrFerramenta;
        int id;
        int posicao;
        try {

            for (String[] abyssesAndTool : abyssesAndTools) {
                abismoOrFerramenta = Integer.parseInt(String.valueOf(abyssesAndTool[0]));
                posicao = Integer.parseInt(String.valueOf(abyssesAndTool[2]));
                id = Integer.parseInt(String.valueOf(abyssesAndTool[1]));

                if (posicao > worldSize || posicao < 0) { // check posicao
                    return false;
                }
                if (abismoOrFerramenta != 0 && abismoOrFerramenta != 1) { // check abismo ou ferramenta
                    return false;
                }

                if (abismoOrFerramenta == 0) {
                    if (id < 0 || id > 9) { // check  id abismos
                        return false;
                    }
                    abismoEFerramentas.put(posicao, new Abismo(id));
                }
                if (abismoOrFerramenta == 1) {
                    if (id < 0 || id > 5) { // check  id ferramenta
                        return false;
                    }
                    abismoEFerramentas.put(posicao, new Ferramenta(id));
                }
            }
        } catch (Exception e) {
            return false;
        }


        return createInitialBoard(playerInfo, worldSize);
    }

    public String getTitle(int position) {
        if (position < 0 || position > tamanhoTabueiro) {
            return null;
        }
        if (abismoEFerramentas.containsKey(position)) {
            return abismoEFerramentas.get(position).getTitulo();
        }
        return null;
    }

    public String getImagePng(int position) {

        if (position < 0 || position > tamanhoTabueiro) {
            return null;
        }

        if (position == tamanhoTabueiro) {
            return "podium.png.jpg";
        }

        if (abismoEFerramentas.containsKey(position)) {
            return abismoEFerramentas.get(position).getTitulo() + ".png";
        }


        return null;

    }

    public List<Programmer> getProgrammers(boolean includeDefeated) {

        List<Programmer> jogadores;
        ArrayList<Programmer> intermedio = new ArrayList<>();

        if (includeDefeated) {
            jogadores = players;
        } else {

            for (Programmer player : players) {
                if (player.getEstado().equals("Em Jogo")) {

                    intermedio.add(player);
                }
            }
            jogadores = intermedio;
        }
        return jogadores;

    }

    public List<Programmer> getProgrammers(int position) {

        ArrayList<Programmer> programmersInPosition = new ArrayList<>();
        if (position < 1 || position > tamanhoTabueiro) {
            return null;
        }
        for (Programmer player : players) {
            if (player.getPosicao() == position) {
                programmersInPosition.add(player);
            }
        }
        if (programmersInPosition.size() == 0) {
            return null;
        }
        return programmersInPosition;
    }

    public String getProgrammersInfo() {
        List<Programmer> players = getProgrammers(false);
        StringBuilder info = new StringBuilder();

        for (int i=0;i<players.size();i++){
            if (i== players.size()-1){
                info.append(players.get(i).getProggramesInfo());
            }else {
                info.append(players.get(i).getProggramesInfo()).append(" | ");
            }
        }

        return info.toString();
    }

    public int getCurrentPlayerID() {
        return players.get(playerAtual).getId();

    }

    public boolean moveCurrentPlayer(int nrSpaces) {

        dado = nrSpaces;
        if (nrSpaces < 1 || nrSpaces > 6) {
            return false;
        }
        if (abismoEFerramentas.containsKey(players.get(playerAtual).getPosicao())) {
            if (abismoEFerramentas.get(players.get(playerAtual).getPosicao()).getTitulo().equals("Ciclo infinito") && players.get(playerAtual).getStuck().equals("Preso")) {
                return false;
            }
        }

        players.get(playerAtual).mover(nrSpaces, tamanhoTabueiro);


        return true;
    }

    public void mudaTurno() {
        if (playerAtual == players.size() - 1) {
            playerAtual = 0;
        } else {
            playerAtual++;
        }

        numeroTotalDeTurnos++;
    }

    public boolean usoDeFerramenta(String abismo) {
        boolean counter;
        for (int i = 0; i < players.get(playerAtual).getFerramentas().size(); i++) {
            counter = players.get(playerAtual).getFerramentas().get(i).daCounter(abismo);
            if (counter) {
                players.get(playerAtual).retiraFerramenta(players.get(playerAtual).getFerramentas().get(i));
                return true;
            }
        }
        return false;
    }

    public String reactToAbyss() {

        if (abismoEFerramentas.get(players.get(playerAtual).getPosicao()).getTitulo().equals("Erro de sintaxe")) {

            if (usoDeFerramenta("Erro de sintaxe")) {

                return "Caiu num Erro de Sintaxe, mas utilizou uma ferramenta para evitar";

            }
            players.get(playerAtual).mover(-1, tamanhoTabueiro);

            return "Caiu num Erro de Sintaxe, irá recuar 1 casa";

        }
        if (abismoEFerramentas.get(players.get(playerAtual).getPosicao()).getTitulo().equals("Erro de lógica")) {

            if (usoDeFerramenta("Erro de lógica")) {

                return "Caiu num Erro de lógica, mas utilizou uma ferramenta para evitar";
            }
            players.get(playerAtual).mover(-dado / 2, tamanhoTabueiro);

            return "Caiu num Erro de Logica, irá recuar " + dado / 2 + " casas";
        }
        if (abismoEFerramentas.get(players.get(playerAtual).getPosicao()).getTitulo().equals("Exception")) {
            if (usoDeFerramenta("Exception")) {

                return "Caiu numa Exception, mas utilizou uma ferramenta para evitar";
            }
            players.get(playerAtual).mover(-2, tamanhoTabueiro);

            return "Caiu numa Exception, irá recuar 2 casas";
        }
        if (abismoEFerramentas.get(players.get(playerAtual).getPosicao()).getTitulo().equals("File Not Found Exception")) {
            if (usoDeFerramenta("File Not Found Exception")) {

                return "Caiu num File Not Found Exception, mas utilizou uma ferramenta para evitar";
            }
            players.get(playerAtual).mover(-3, tamanhoTabueiro);

            return "Caiu num File Not Found Exception, irá recuar 3 casas";
        }
        if (abismoEFerramentas.get(players.get(playerAtual).getPosicao()).getTitulo().equals("Crash (aka Rebentanço)")) {
            if (usoDeFerramenta("Crash (aka Rebentanço)")) {

                return "Caiu num Crash (aka Rebentanço), mas utilizou uma ferramenta para evitar";
            }
            players.get(playerAtual).mover(0, tamanhoTabueiro);

            return "Caiu num Crash (aka Rebentanço), irá voltar ao inicio do tabuleiro";
        }
        if (abismoEFerramentas.get(players.get(playerAtual).getPosicao()).getTitulo().equals("Duplicated Code")) {

            if (usoDeFerramenta("Duplicated Code")) {

                return "Caiu num Duplicated Code, mas utilizou uma ferramenta para evitar";
            }

            players.get(playerAtual).mover(players.get(playerAtual).getPosicaoAnterior(), tamanhoTabueiro);

            return "Caiu num Duplicate Code, irá recuar até á casa onde estava no inicio do turno";
        }
        if (abismoEFerramentas.get(players.get(playerAtual).getPosicao()).getTitulo().equals("Efeitos secundários")) {

            if (usoDeFerramenta("Efeitos secundários")) {

                return "Caiu nos Efeitos secundários, mas utilizou uma ferramenta para evitar";
            }

            players.get(playerAtual).mover(players.get(playerAtual).getPosicao2Anterior(), tamanhoTabueiro);

            return "Caiu nos Efeitos secundários, irá recuar até á casa onde estava no inicio do turno anterior";
        }
        if (abismoEFerramentas.get(players.get(playerAtual).getPosicao()).getTitulo().equals("Blue Screen of Death")) {

            if (usoDeFerramenta("Blue Screen of Death")) {

                return "Caiu num Blue Screen of Death, mas utilizou uma ferramenta para evitar";
            }

            players.get(playerAtual).setEstado("Derrotado");

            return "Caiu num Blue Screen of Death, Perdeu\n Better Luck next time;)";
        }
        if (abismoEFerramentas.get(players.get(playerAtual).getPosicao()).getTitulo().equals("Ciclo infinito")) {

            if (usoDeFerramenta("Ciclo infinito")) {

                return "Caiu num Ciclo infinito, mas utilizou uma ferramenta para evitar";
            }

            for (Programmer player : players) {
                if (player.getPosicao() == players.get(playerAtual).getPosicao() && player.getId() != players.get(playerAtual).getId()) {
                    player.setStuck("Livre");
                }
            }


            players.get(playerAtual).setStuck("Preso");

            return "Caiu num Ciclo infinito, Irá ficar preso até alguém o vir salvar;)";
        }
        if (abismoEFerramentas.get(players.get(playerAtual).getPosicao()).getTitulo().equals("Segmentation Fault")) { // saber se perde sempre ou so quando ia andar para tras
            int count = 0;
            for (Programmer programmer : players) {
                if (players.get(playerAtual).getPosicao() == programmer.getPosicao()) {
                    count++;
                }
            }
            if (count >= 2) {
                for (Programmer player : players) {

                    if (players.get(playerAtual).getPosicao() == player.getPosicao()) {
                        players.get(playerAtual).mover(-3, tamanhoTabueiro);

                    }
                }

                return "Caiu num Segmentation Fault, Todos os jogadores nesta casa recuam 3 casas)";
            }

            return "Caiu num Segmentation Fault, Não acontece nada, pois está sozinho na casa";

        }
        return reactToTool();
    }

    public String reactToTool() {

        if (abismoEFerramentas.get(players.get(playerAtual).getPosicao()).getTitulo().equals("Herança")) {

            if (!players.get(playerAtual).adicionaFerramenta(new Ferramenta(0))) {
                return "Caiu numa Herança, esta ferramenta já existe no seu conjunto de ferramentas";
            }

            return "Caiu numa Herança, esta ferramenta foi adicionada ao seu conjunto de ferramentas";
        }
        if (abismoEFerramentas.get(players.get(playerAtual).getPosicao()).getTitulo().equals("Programação funcional")) {
            if (!players.get(playerAtual).adicionaFerramenta(new Ferramenta(1))) {
                return "Caiu numa Programação funcional, esta ferramenta já existe no seu conjunto de ferramentas";
            }

            return "Caiu numa Programação funcional, esta ferramenta foi adicionada ao seu conjunto de ferramentas";
        }
        if (abismoEFerramentas.get(players.get(playerAtual).getPosicao()).getTitulo().equals("Testes unitários")) {

            if (!players.get(playerAtual).adicionaFerramenta(new Ferramenta(2))) {
                return "Caiu nos Testes unitários, esta ferramenta já existe no seu conjunto de ferramentas";
            }

            return "Caiu nos Testes unitários, esta ferramenta foi adicionada ao seu conjunto de ferramentas";
        }
        if (abismoEFerramentas.get(players.get(playerAtual).getPosicao()).getTitulo().equals("Tratamento de Excepções")) {
            if (!players.get(playerAtual).adicionaFerramenta(new Ferramenta(3))) {
                return "Caiu no Tratamento de Excepções, esta ferramenta já existe no seu conjunto de ferramentas";
            }
            return "Caiu no Tratamento de Excepções, esta ferramenta foi adicionada ao seu conjunto de ferramentas";
        }
        if (abismoEFerramentas.get(players.get(playerAtual).getPosicao()).getTitulo().equals("IDE")) {
            if (!players.get(playerAtual).adicionaFerramenta(new Ferramenta(4))) {
                return "Caiu no IDE, esta ferramenta já existe no seu conjunto de ferramentas";
            }
            return "Caiu no IDE, esta ferramenta foi adicionada ao seu conjunto de ferramentas";
        }
        if (abismoEFerramentas.get(players.get(playerAtual).getPosicao()).getTitulo().equals("Ajuda Do Professor")) {
            if (!players.get(playerAtual).adicionaFerramenta(new Ferramenta(5))) {
                return "Caiu na Ajuda Do Professor, esta ferramenta já existe no seu conjunto de ferramentas";
            }
            return "Caiu na Ajuda Do Professor, esta ferramenta foi adicionada ao seu conjunto de ferramentas";
        }

        return "Erro pois caiste numa casa desconhecida";

    }

    public String reactToAbyssOrTool() {


        if (abismoEFerramentas.containsKey(players.get(playerAtual).getPosicao())) {
            String resulado = reactToAbyss();
            mudaTurno();
            return resulado;
        }


        mudaTurno();
        return null;
    }

    public boolean gameIsOver() {

        for (Programmer player : players) {
            if (player.getPosicao() == tamanhoTabueiro) {
                return true;
            }
        }
        return false;

    }

    public List<String> getGameResults() {
        int i;
        ArrayList<String> resultados = new ArrayList<>();
        resultados.add("O GRANDE JOGO DO DEISI");
        resultados.add("");
        resultados.add("NR. DE TURNOS");
        resultados.add("" + numeroTotalDeTurnos);
        resultados.add("");
        resultados.add("VENCEDOR");
        players.sort(Comparator.comparingInt(Programmer::getPosicao).reversed());
        resultados.add(players.get(0).getName());
        resultados.add("");
        resultados.add("RESTANTES");
        for (i = 1; i < players.size() - 1; i++) {
            resultados.add(players.get(i).getName() + " " + players.get(i).getPosicao());
        }
        resultados.add(players.get(i).getName() + " " + players.get(i).getPosicao() + "");

        return resultados;
    }

    public JPanel getAuthorsPanel() {

        JPanel creditos = new JPanel();
        creditos.setSize(300, 300);
        ArrayList<JLabel> labels = new ArrayList<>();

        labels.add(new JLabel("Parte 1 do projeto de LP2\n"));
        labels.add(new JLabel("                                                                  "));
        labels.add(new JLabel("Programadores do DEISI GREAT GAME:"));
        labels.add(new JLabel("              Alexandre Costa            "));
        labels.add(new JLabel("              Daniel Granja              "));
        labels.add(new JLabel("                                                                  "));
        labels.add(new JLabel("Professores da cadeira de LP2 2021 / 2022"));
        labels.add(new JLabel("              Pedro Alves            "));
        labels.add(new JLabel("              Lúcio Ferreira              "));
        labels.add(new JLabel("              Bruno Ciperiano            "));


        for (JLabel label : labels) {
            creditos.add(label);
        }


        return creditos;
    }


}