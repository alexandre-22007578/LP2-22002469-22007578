package pt.ulusofona.lp2.deisiGreatGame;

import javax.swing.*;
import java.util.*;

public class GameManager {
    private int dado;
    private int tamanhoTabueiro;
    private int playerAtual;
    private int numeroTotalDeTurnos;


    ArrayList<Programmer> players = new ArrayList<>();
    ArrayList<Programmer> playersDerrotados = new ArrayList<>();
    HashMap<Integer, AbismoOrFerramenta> abismoEFerramentas = new HashMap<>();


    public GameManager() {
    }

    public void createAbismoEAdiciona(int id, int posicao) {


        switch (id) {
            case 0 -> abismoEFerramentas.put(posicao, new ErroDeSintaxe(id));
            case 1 -> abismoEFerramentas.put(posicao, new ErroDeLogica(id));
            case 2 -> abismoEFerramentas.put(posicao, new Exceptionn(id));
            case 3 -> abismoEFerramentas.put(posicao, new FileNotFoundException(id));
            case 4 -> abismoEFerramentas.put(posicao, new CrashAkaRebentanco(id));
            case 5 -> abismoEFerramentas.put(posicao, new DuplicatedCode(id));
            case 6 -> abismoEFerramentas.put(posicao, new EfeitosSecundarios(id));
            case 7 -> abismoEFerramentas.put(posicao, new BlueScreeOfDeath(id));
            case 8 -> abismoEFerramentas.put(posicao, new CicloInfinito(id));
            case 9 -> abismoEFerramentas.put(posicao, new SegmentationFault(id));


        }
    }

    public void createFerramentaEAdiciona(int id, int posicao) {
        switch (id) {
            case 0 -> abismoEFerramentas.put(posicao, new Heranca(id));
            case 1 -> abismoEFerramentas.put(posicao, new ProgramacaoFuncional(id));
            case 2 -> abismoEFerramentas.put(posicao, new TstUnitarios(id));
            case 3 -> abismoEFerramentas.put(posicao, new TratamentoDeExcepcoes(id));
            case 4 -> abismoEFerramentas.put(posicao, new Ide(id));
            case 5 -> abismoEFerramentas.put(posicao, new AjudaDoProfessor(id));

        }
    }

    public boolean createInitialBoard(String[][] playerInfo, int worldSize) {
        numeroTotalDeTurnos = 1;
        players.clear();
        int id;
        String nome;
        ProgrammerColor color;
        String linguagensProgramacao;
        HashSet<Integer> ids = new HashSet<>();
        HashSet<ProgrammerColor> cores = new HashSet<>();
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
                if (id < 1 || !(ids.add(id)) || !(cores.add(color))) {
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

                    createAbismoEAdiciona(id, posicao);

                }
                if (abismoOrFerramenta == 1) {
                    if (id < 0 || id > 5) { // check  id ferramenta
                        return false;
                    }
                    createFerramentaEAdiciona(id, posicao);
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

        ArrayList<Programmer> jogadores = new ArrayList<>(players);


        if (includeDefeated) {

            jogadores.addAll(playersDerrotados);
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

        for (int i = 0; i < players.size(); i++) {
            if (i == players.size() - 1) {
                info.append(players.get(i).getProggramesInfo());
            } else {
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
            if (players.get(playerAtual).getStuck()) {
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

    public String reactToAbyssOrTool() {
        int posicao = players.get(playerAtual).getPosicao();
        int posicaoAnterior = players.get(playerAtual).getPosicaoAnterior();
        int posicaoAnterior2 = players.get(playerAtual).getPosicao2Anterior();

        if (abismoEFerramentas.containsKey(players.get(playerAtual).getPosicao())) {

            players.get(playerAtual).adicionaFerramenta(abismoEFerramentas.get(players.get(playerAtual).getPosicao()));


            boolean daCounter = false;
            for (int i = 0; i < players.get(playerAtual).getFerramentas().size(); i++) {

                daCounter = players.get(playerAtual).getFerramentas().get(i).daCounter(abismoEFerramentas.get(players.get(playerAtual).getPosicao()).getTitulo());
                if (daCounter) {
                    players.get(playerAtual).retiraFerramenta(players.get(playerAtual).getFerramentas().get(i));
                    break;
                }
            }
            String resulado = abismoEFerramentas.get(players.get(playerAtual).getPosicao()).reactToAbyssOrTool(daCounter);


            try {
             players.get(playerAtual).mover(-abismoEFerramentas.get(players.get(playerAtual).getPosicao()).getQuantidadePosicoesAnda(dado, posicao, posicaoAnterior, posicaoAnterior2, daCounter), tamanhoTabueiro);

            } catch (Exception e) {

                if (!daCounter) {


                    if (e.getMessage().equals("Ciclo infinito")) {
                        for (Programmer player : players) {
                            if (player.getPosicao() == players.get(playerAtual).getPosicao() && player.getId() != players.get(playerAtual).getId()) {
                                player.mudarEstadoLivre();
                            }
                        }
                        players.get(playerAtual).mudarEstadoPreso();
                        String resultado = abismoEFerramentas.get(players.get(playerAtual).getPosicao()).reactToAbyssOrTool(false);
                        mudaTurno();
                        return resultado;
                    }

                    if (e.getMessage().equals("Segmentation Fault")) {
                        int count = 0;
                        for (Programmer programmer : players) {
                            if (players.get(playerAtual).getPosicao() == programmer.getPosicao()) {
                                count++;
                            }
                        }
                        if (count >= 2) {
                            for (Programmer player : players) {

                                if (players.get(playerAtual).getPosicao() == player.getPosicao()) {
                                    player.mover(-3, tamanhoTabueiro);

                                }
                            }
                            mudaTurno();
                            return "Caiu num Segmentation Fault, Todos os jogadores nesta casa recuam 3 casas)";
                        }
                        String resultado = abismoEFerramentas.get(players.get(playerAtual).getPosicao()).reactToAbyssOrTool(false);
                        mudaTurno();
                        return resultado;
                    }
                    if (e.getMessage().equals("Blue Screen of Death")) {

                        players.get(playerAtual).perdeu();

                        players.remove(playerAtual);
                        if (playerAtual == players.size() + 1) {
                            playerAtual = 0;
                        }
                        numeroTotalDeTurnos++;
                        return "Caiu num Blue Screen of Death, Perdeu\n Better Luck next time;)";
                    }
                }

            }

            mudaTurno();
            return resulado;
        }


        mudaTurno();
        return null;
    }

    public boolean gameIsOver() {

        if (players.size() == 1) {
            return true;
        }

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

        labels.add(new JLabel("Parte 2 do projeto de LP2\n"));
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