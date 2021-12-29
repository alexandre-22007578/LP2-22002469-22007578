package pt.ulusofona.lp2.deisiGreatGame;

import javax.swing.*;
import java.io.*;
import java.util.*;

public class GameManager {
    private int dado;
    private int tamanhoTabuleiro;
    private int playerAtual;
    private int numeroTotalDeTurnos;
    private ArrayList<Programmer> players = new ArrayList<>();
    private HashMap<Integer, AbismoOrFerramenta> abismosEFerramentas = new HashMap<>();


    public GameManager() {
    }


    public HashMap<Integer, AbismoOrFerramenta> getAbismosEFerramentas() {
        return abismosEFerramentas;
    }

    public void createAbismoEAdiciona(int id, int posicao) {


        switch (id) {
            case 0 -> abismosEFerramentas.put(posicao, new ErroDeSintaxe(id));
            case 1 -> abismosEFerramentas.put(posicao, new ErroDeLogica(id));
            case 2 -> abismosEFerramentas.put(posicao, new Exceptionn(id));
            case 3 -> abismosEFerramentas.put(posicao, new FileNotFoundException(id));
            case 4 -> abismosEFerramentas.put(posicao, new CrashAkaRebentanco(id));
            case 5 -> abismosEFerramentas.put(posicao, new DuplicatedCode(id));
            case 6 -> abismosEFerramentas.put(posicao, new EfeitosSecundarios(id));
            case 7 -> abismosEFerramentas.put(posicao, new BlueScreeOfDeath(id));
            case 8 -> abismosEFerramentas.put(posicao, new CicloInfinito(id));
            case 9 -> abismosEFerramentas.put(posicao, new SegmentationFault(id));


        }
    }

    public void createFerramentaEAdiciona(int id, int posicao) {
        switch (id) {
            case 0 -> abismosEFerramentas.put(posicao, new Heranca(id));
            case 1 -> abismosEFerramentas.put(posicao, new ProgramacaoFuncional(id));
            case 2 -> abismosEFerramentas.put(posicao, new TstUnitarios(id));
            case 3 -> abismosEFerramentas.put(posicao, new TratamentoDeExcepcoes(id));
            case 4 -> abismosEFerramentas.put(posicao, new Ide(id));
            case 5 -> abismosEFerramentas.put(posicao, new AjudaDoProfessor(id));

        }
    }

    public void createInitialBoard(String[][] playerInfo, int worldSize) throws InvalidInitialBoardException {
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
            throw new InvalidInitialBoardException("Tamanho so tabuleiro ou numero de jogadores inválidos", 0, -1);
        }
        try {
            for (String[] strings : playerInfo) {
                id = Integer.parseInt(String.valueOf(strings[0]));
                if (strings[1] == null || strings[1].equals("")) {
                    throw new InvalidInitialBoardException("Nome do jogador inválido", 0, -1);
                }
                nome = strings[1];
                linguagensProgramacao = String.valueOf(strings[2]);
                color = switch (strings[3]) {
                    case "Purple" -> ProgrammerColor.PURPLE;
                    case "Green" -> ProgrammerColor.GREEN;
                    case "Brown" -> ProgrammerColor.BROWN;
                    case "Blue" -> ProgrammerColor.BLUE;
                    default -> throw new InvalidInitialBoardException("Cor do jogador invalido", 0, -1);
                };
                if (id < 1 || !(ids.add(id)) || !(cores.add(color))) {
                    throw new InvalidInitialBoardException("Id invalido ou repetido ou cor repetida", 0, -1);
                }
                players.add(new Programmer(id, nome, color, linguagensProgramacao));


            }
            this.tamanhoTabuleiro = worldSize;
        } catch (InvalidInitialBoardException e) {

            if (e.getMessage() == null) {
                throw new InvalidInitialBoardException("Parametros fora do tipo  de variável pedida", 0, -1);
            } else {
                throw e;
            }

        }

        players.sort(Comparator.comparingInt(Programmer::getId));

    }

    public void createInitialBoard(String[][] playerInfo, int worldSize, String[][] abyssesAndTools) throws InvalidInitialBoardException {

        abismosEFerramentas.clear();
        int abismoOrFerramenta;
        int id ;
        int posicao;

        try {

            for (String[] abyssesAndTool : abyssesAndTools) {
                abismoOrFerramenta = Integer.parseInt(String.valueOf(abyssesAndTool[0]));
                posicao = Integer.parseInt(String.valueOf(abyssesAndTool[2]));
                id = Integer.parseInt(String.valueOf(abyssesAndTool[1]));

                if (posicao > worldSize || posicao < 0) { // check posicao
                    throw new InvalidInitialBoardException("posicao errada", 0, -1);
                }
                if (abismoOrFerramenta != 0 && abismoOrFerramenta != 1) { // check abismo ou ferramenta
                    throw new InvalidInitialBoardException("identificador de ferramenta ou abismo inválido", 0, -1);
                }

                if (abismoOrFerramenta == 0) {
                    if (id < 0 || id > 9) { // check  id abismos

                        throw new InvalidInitialBoardException("ID abismo invalido", 1, id);
                    }

                    createAbismoEAdiciona(id, posicao);

                }
                if (abismoOrFerramenta == 1) {
                    if (id < 0 || id > 5) { // check  id ferramenta

                        throw new InvalidInitialBoardException("ID ferramenta invalido", 2, id);

                    }
                    createFerramentaEAdiciona(id, posicao);
                }
            }
        } catch (InvalidInitialBoardException e) {
            if (e.getMessage() == null) {
                throw new InvalidInitialBoardException("Parametros fora do tipo  de variável pedida", 0, -1);
            } else {
                throw e;
            }
        }


        createInitialBoard(playerInfo, worldSize);
    }

    public String getTitle(int position) {

        if (position < 0 || position > tamanhoTabuleiro) {
            return null;
        }
        if (abismosEFerramentas.containsKey(position)) {
            return abismosEFerramentas.get(position).getTitulo();
        }
        return null;
    }

    public String getImagePng(int position) {

        if (position < 0 || position > tamanhoTabuleiro) {
            return null;
        }

        if (position == tamanhoTabuleiro) {
            return "podium.png";
        }

        if (abismosEFerramentas.containsKey(position)) {
            return abismosEFerramentas.get(position).getTitulo() + ".png";
        }


        return null;

    }

    public List<Programmer> getProgrammers(boolean includeDefeated) {

        ArrayList<Programmer> jogadores = new ArrayList<>();


        if (!includeDefeated) {

            for (Programmer player : players) {
                if (player.getEstado().equals("Em Jogo")) {
                    jogadores.add(player);
                }
            }
            return jogadores;
        }
        return players;

    }

    public List<Programmer> getProgrammers(int position) {

        ArrayList<Programmer> programmersInPosition = new ArrayList<>();
        if (position < 1 || position > tamanhoTabuleiro) {
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
                info.append(players.get(i).getProgrammersInfo());
            } else {
                info.append(players.get(i).getProgrammersInfo()).append(" | ");
            }
        }

        return info.toString();
    }

    public int getCurrentPlayerID() {
        String estado = players.get(playerAtual).getEstado();

        if (estado.equals("Derrotado")) {

            if (playerAtual == players.size() - 1) {
                playerAtual = 0;
            } else {
                playerAtual++;
            }
            return getCurrentPlayerID();
        }
        return players.get(playerAtual).getId();

    }

    public boolean moveCurrentPlayer(int nrSpaces) {

        dado = nrSpaces;
        if (nrSpaces < 1 || nrSpaces > 6) {
            return false;
        }

        if (abismosEFerramentas.containsKey(players.get(playerAtual).getPosicao())) { // so para o ciclo infinito
            if (players.get(playerAtual).getStuck()) {
                return false;
            }
        }

        players.get(playerAtual).mover(nrSpaces, tamanhoTabuleiro);


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


        if (abismosEFerramentas.containsKey(players.get(playerAtual).getPosicao())) {

            players.get(playerAtual).adicionaFerramenta(abismosEFerramentas.get(players.get(playerAtual).getPosicao()));


            boolean daCounter = false;
            for (int i = 0; i < players.get(playerAtual).getFerramentas().size(); i++) {

                daCounter = players.get(playerAtual).getFerramentas().get(i).daCounter(abismosEFerramentas.get(players.get(playerAtual).getPosicao()).getTitulo());
                if (daCounter) {
                    players.get(playerAtual).retiraFerramenta(players.get(playerAtual).getFerramentas().get(i));
                    break;
                }
            }
            String resulado = abismosEFerramentas.get(players.get(playerAtual).getPosicao()).move(dado, players.get(playerAtual), tamanhoTabuleiro, daCounter, players);

            mudaTurno();
            return resulado;
        }

        mudaTurno();
        return null;
    }

    public boolean gameIsOver() {

        if (getProgrammers(false).size() == 1) {
            return true;
        }

        for (Programmer player : getProgrammers(false)) {
            if (player.getPosicao() == tamanhoTabuleiro) {
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
        players.sort(Comparator.comparingInt(Programmer::getPosicao).reversed().thenComparing(Programmer::getName));
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

        labels.add(new JLabel("Parte 3 do projeto de LP2\n"));
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


    public boolean saveGame(File file){

        try{

            FileWriter writer=new FileWriter(file);
            PrintWriter print=new PrintWriter(writer);

        }catch (Exception e){
            return false;
        }


        return true;
    }

    public boolean loadGame(File file){

        try{

            FileReader reader=new FileReader(file);
            BufferedReader buffered=new BufferedReader(reader);

        }catch (Exception e){
            return false;
        }
        return true;
    }



}