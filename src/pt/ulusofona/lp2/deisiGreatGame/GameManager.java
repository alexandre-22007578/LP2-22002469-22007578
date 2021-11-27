package pt.ulusofona.lp2.deisiGreatGame;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class GameManager {

    int tamanhoTabueiro;
    int playerAtual;
    int numeroTotalDeTurnos;


    ArrayList<Programmer> players=new ArrayList<>();

    public GameManager() {
    }

    public boolean createInitialBoard(String[][] playerInfo, int boardSize) {
        numeroTotalDeTurnos = 1;
        players = new ArrayList<>();
        int id;
        String nome;
        ProgrammerColor color;
        String linguagensProgramacao;
        HashSet<Integer> ids = new HashSet<>();
        playerAtual = 0;

        if (boardSize < playerInfo.length * 2 || playerInfo.length < 2 || playerInfo.length > 4) {
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
                this.tamanhoTabueiro = boardSize;

            }

        } catch (Exception e) {
            return false;
        }

        players.sort(Comparator.comparingInt((Programmer) -> Programmer.id));
        return true;
    }

    public List<Programmer> getProgrammers(boolean includeDefeated){
        return players;
    }

    public String getImagePng(int position) {



        if (position == tamanhoTabueiro) {
            return "podium.png.jpg";
        }
        return "";

    }

    public ArrayList<Programmer> getProgrammers() {
        return players;
    }

    public ArrayList<Programmer> getProgrammers(int position) {

        ArrayList<Programmer> programmersInPosition = new ArrayList<>();
        if (position < 1 || position > tamanhoTabueiro - 1) {
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

    public int getCurrentPlayerID() {
        return players.get(playerAtual).getId();

    }

    public boolean moveCurrentPlayer(int nrPositions) {

        if (nrPositions < 1 || nrPositions > 6) {
            return false;
        }
        if (players.get(playerAtual).getPosicao() + nrPositions <= tamanhoTabueiro) {
            players.get(playerAtual).aumentaPosicao(nrPositions);
        } else {

            players.get(playerAtual).diminuiPosicao(nrPositions, tamanhoTabueiro);

        }


        switch (playerAtual) {
            case 0:

                playerAtual++;
                break;
            case 1:
                if (players.size() != 2) {
                    playerAtual++;

                } else {
                    playerAtual = 0;

                }

                break;
            case 2:
                if (players.size() != 3) {
                    playerAtual++;

                } else {
                    playerAtual = 0;

                }
                break;
            case 3:
                playerAtual = 0;

                break;
        }


        numeroTotalDeTurnos++;
        return true;
    }

    public boolean gameIsOver() {

        for (Programmer player : players) {
            if (player.getPosicao() == tamanhoTabueiro) {
                return true;
            }
        }
        return false;

    }

    public ArrayList<String> getGameResults() {
        int i;
        ArrayList<String> resultados = new ArrayList<>();
        resultados.add("O GRANDE JOGO DO DEISI");
        resultados.add("");
        resultados.add("NR. DE TURNOS");
        resultados.add("" + numeroTotalDeTurnos);
        resultados.add("");
        resultados.add("VENCEDOR");
        players.sort(Comparator.comparingInt((Programmer position) -> position.posicao).reversed());
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