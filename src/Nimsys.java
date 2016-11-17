
/**
 * This Nimsys class implements a series of operations about the information of
 * users involved in the system of Nim as well as starting a new game. Yuge
 * Liang 05/May/2015
 */
import java.util.Scanner;
import java.util.ArrayList;
import java.util.*;
import java.util.Collections;
import java.io.*;

public class Nimsys implements Serializable {

    private static ArrayList<NimPlayer> playerList = new ArrayList<NimPlayer>();
    private static NimGame oneGame = new NimGame();
    private static AdvancedNim oneAdvanced = new AdvancedNim();
    static transient Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.println("Welcome to Nim");
        boolean done = false;
        String filename = "players.dat";
        File file = new File(filename);
         
        while (!done) {
            try {
                ObjectInputStream inputStream
                        = new ObjectInputStream(new FileInputStream(filename));
                done = true;
                try {
                    while(true) { 
                    playerList.add((NimPlayer) inputStream.readObject());
                    }
                } catch (EOFException e) {
                    //System.out.println(e.getMessage());
                    //System.out.println();
                }
//            } catch (FileNotFoundException e) {
//                System.out.println("Cannot find file players.dat");
            } catch (ClassNotFoundException e) {
//                System.out.println("Cannot find object");
            } catch (IOException e) {
//                System.out.println(e.getMessage());
//                System.out.println("Problems with input from players.dat");
            }

            System.out.println();
            System.out.print(">");
            String ss = null;

            //Choose the respective method according to a specific command.
            while (true) {
                String prompt = scanner.nextLine();
                done = false;
                while (!done) {
                    try {
                        String[] check = prompt.split(" ");
                        if (check[0].equals("addplayer")) {
                            String toDelete = "addplayer ";
                            ss = prompt.substring(toDelete.length());
                            if (!ss.contains(",") || ss.split(",").length < 3) {
                                throw new InvalidArgCountException();
                            } else {
                                done = true;
                                addPlayer(ss);
                                System.out.println();
                                System.out.print(">");
                            }

                        } else if (check[0].equals("addaiplayer")) {
                            String toDelete = "addaiplayer ";
                            ss = prompt.substring(toDelete.length());
                            if (!ss.contains(",") || ss.split(",").length < 3) {
                                throw new InvalidArgCountException();
                            } else {
                                done = true;
                                addAIPlayer(ss);
                                System.out.println();
                                System.out.print(">");
                            }
                        } else if (check[0].equals("removeplayer")) {
//                            if (check.length == 1) {
//                                throw new InvalidArgCountException();
//                            } else {
                            done = true;
                            String toDelete = "removeplayer ";
                            if (prompt.length() == toDelete.length() - 1) {
                                System.out.println("Are you sure you want to "
                                        + "remove all players? (y/n)");
                                if (scanner.nextLine().equals("y")) {
                                    removeAll();
                                    System.out.println();
                                    System.out.print(">");
                                } else {
                                    System.out.println();
                                    System.out.print(">");
                                }
                            } else {
                                ss = prompt.substring(toDelete.length());
                                removePlayer(ss);
                                System.out.println();
                                System.out.print(">");
                            }
                            //}
                        } else if (check[0].equals("editplayer")) {
                            String toDelete = "editplayer ";
                            ss = prompt.substring(toDelete.length());
                            if (!(ss.contains(","))
                                    || ss.split(",").length < 3) {
                                throw new InvalidArgCountException();
                            }
                            done = true;
                            editPlayer(ss);
                            System.out.println();
                            System.out.print(">");

                        } else if (check[0].equals("displayplayer")) {
                            done = true;
                            String toDelete = "displayplayer ";
                            if (prompt.length() == toDelete.length() - 1) {
                                displayAll();
                                System.out.println();
                                System.out.print(">");
                            } else {
                                ss = prompt.substring(toDelete.length());
                                displayer(ss);
                                System.out.println();
                                System.out.print(">");
                            }
                        } else if (check[0].equals("startgame")) {
                            String toDelete = "startgame ";
                            ss = prompt.substring(toDelete.length());
                            if (!(ss.contains(","))
                                    || ss.split(",").length < 4) {
                                throw new InvalidArgCountException();
                            } else {
                                done = true;
                                startCheck(ss);
                                System.out.println();
                                System.out.print(">");
                            }
                        } else if (check[0].equals("startadvancedgame")) {
                            String toDelete = "startadvancedgame ";
                            ss = prompt.substring(toDelete.length());
                            if (!(ss.contains(","))
                                    || ss.split(",").length < 3) {
                                throw new InvalidArgCountException();
                            } else {
                                done = true;
                                startAdvanced(ss);
                                System.out.println();
                                System.out.print(">");
                            }
                        } else if (check[0].equals("rankings")) {
                            done = true;
                            rankAll();
                            System.out.println();
                            System.out.print(">");
                        } else if (check[0].equals("resetstats")) {
                            done = true;
                            String toDelete = "resetstats";
                            if (prompt.length() == toDelete.length()) {
                                System.out.println("Are you sure you want to "
                                        + "reset all player statistics? (y/n)");
                                if (scanner.nextLine().equals("y")) {
                                    resetAll();
                                    System.out.println();
                                    System.out.print(">");
                                } else {
                                    System.out.println();
                                    System.out.print(">");
                                }
                            } else {
                                ss = prompt.substring(toDelete.length());
                                resetstats(ss);
                                System.out.println();
                                System.out.print(">");
                            }
                        } else if (check[0].equals("exit")) {

                            done = true;
                            try {
                                ObjectOutputStream output
                                        = new ObjectOutputStream(
                                                new FileOutputStream(filename));

                                for (int i = 0; i < playerList.size(); i++) {
                                    output.writeObject(playerList.get(i));
                                    //outputStream.writeUTF();
                                }

                                output.close();
                            } catch (IOException e) {
                                System.out.println("Problems with output.");
                            }
                            System.out.println();
                            System.exit(0);

                        } else {
                            throw new InvalidCommandException("'" + check[0]
                                    + "' is not a valid command.");
                        }
                    } catch (InvalidArgCountException e) {
                        System.out.println(e.getMessage());
                        System.out.println();
                        System.out.print(">");
                        prompt = scanner.nextLine();
                    } catch (InvalidCommandException e) {
                        System.out.println(e.getMessage());
                        System.out.println();
                        System.out.print(">");
                        prompt = scanner.nextLine();
                    }

                }
            }
        }
    }

    //Allow new players to be added to the system.
    public static void addPlayer(String names) {
        String[] ss = names.split(",");

        //Check if the username has been existed
        if (!checkExist(ss[0])) {
            NimHumanPlayer player = new NimHumanPlayer(ss[0], ss[1], ss[2]);
            playerList.add(player);
        } else {
            System.out.println("The player already exists.");
        }
    }

    //Allow new AIplayers to be added to the system.
    public static void addAIPlayer(String names) {
        String[] ss = names.split(",");

        //Check if the username has been existed
        if (!checkExist(ss[0])) {
            NimAIPlayer player = new NimAIPlayer(ss[0], ss[1], ss[2]);
            playerList.add(player);
        } else {
            System.out.println("The AIplayer already exists.");
        }

    }

    //Allow users to be removed from the game
    public static void removePlayer(String name) {

        //define a flag to indicate whether the username exists
        boolean find = false;

        for (NimPlayer indexPlayer : playerList) {
            if (indexPlayer.getName().equals(name)) {
                playerList.remove(indexPlayer);
                find = true;
                break;
            }
        }
        if (!find) {
            System.out.println("The player does not exist.");
        }
    }

    //Remove all users
    public static void removeAll() {
        playerList.removeAll(playerList);
    }

    //Check whether a given username exists
    public static boolean checkExist(String username) {
        boolean flag = false;
        for (NimPlayer indexPlayer : playerList) {
            if (indexPlayer.getName().equals(username)) {
                flag = true;
                break;
            }
        }
        return flag;
    }

    //Allow player details to be edited
    public static void editPlayer(String names) {
        String[] ss = names.split(",");
        boolean find = false;
        for (NimPlayer indexPlayer : playerList) {
            if (indexPlayer.getName().equals(ss[0])) {
                indexPlayer.setFname(ss[1]);
                indexPlayer.setGname(ss[2]);
                find = true;
                break;
            }
        }
        if (!find) {
            System.out.println("The player does not exist.");
        }
    }

    //Display a user
    public static void displayer(String name) {
        boolean find = false;
        for (NimPlayer someOne : playerList) {
            if (someOne.getName().equals(name)) {
                System.out.println(playerList.get(playerList.indexOf(someOne)));
                find = true;
            }
        }
        if (!find) {
            System.out.println("The player does not exist.");
        }
    }

    //Display all users
    public static void displayAll() {

        //The displayed list is sorted in an alphabetical order of the username
        //Collections.sort(playerList);
        Collections.sort(playerList, new Comparator<NimPlayer>() {

            public int compare(NimPlayer arg0, NimPlayer arg1) {
                return arg0.getName().compareTo(arg1.getName());
            }
        }
        );

        for (NimPlayer each : playerList) {
            System.out.println(each);
        }
    }

    //Reset a given user
    public static void resetstats(String name) {
        boolean find = false;
        for (NimPlayer someOne : playerList) {
            if (someOne.getName().equals(name)) {
                playerList.get(playerList.indexOf(someOne)).resetStats();
                find = true;
            }
        }
        if (!find) {
            System.out.println("The player does not exist.");
        }
    }

    //Reset all users
    public static void resetAll() {
        for (NimPlayer each : playerList) {
            playerList.get(playerList.indexOf(each)).resetStats();
        }
    }

    //Before running a game, check if the given usernames are valid.
    public static void startCheck(String info) {

        String[] beginInfo = info.split(",");

        boolean checkA = false;
        boolean checkB = false;
        int a = 0, b = 0;
        for (int i = 0; i < playerList.size(); i++) {
            String name = beginInfo[2];
            if (playerList.get(i).getName().equals(name)) {

                if (playerList.get(i) instanceof NimAIPlayer) {
                    oneGame.setAIone((NimAIPlayer) playerList.get(i));
                    oneGame.getAIone().setPlays();
                    a = 1;
                } else if (playerList.get(i) instanceof NimHumanPlayer) {
                    oneGame.setPlayerA((NimHumanPlayer) playerList.get(i));
                    oneGame.getPlayerA().setPlays();
                    a = 0;
                }
                checkA = true;
                break;
            }
        }
        for (int i = 0; i < playerList.size(); i++) {
            String name = beginInfo[3];
            if (playerList.get(i).getName().equals(name)) {

                if (playerList.get(i) instanceof NimAIPlayer) {
                    oneGame.setAItwo((NimAIPlayer) playerList.get(i));
                    oneGame.getAItwo().setPlays();
                    b = 1;
                } else {
                    oneGame.setPlayerB((NimHumanPlayer) playerList.get(i));
                    oneGame.getPlayerB().setPlays();
                    b = 0;
                }
                checkB = true;
                break;
            }
        }
        if (checkA == false || checkB == false) {
            System.out.println("One of the players does not exist.");
        } else {
            oneGame.setScanner(scanner);
            oneGame.runGame(beginInfo, a, b);
        }
    }

    //Output a list of player rankings
    public static void rankAll() {

        /*The ranking list is sorted in a descending order of winning ratio
         Users with the same winning ratio are sorted according to 
         the alphabetical order */
        Collections.sort(playerList, new Comparator<NimPlayer>() {

            public int compare(NimPlayer arg0, NimPlayer arg1) {
                int i = arg0.getWonRatio() < arg1.getWonRatio() ? 1
                        : (arg0.getWonRatio() == arg1.getWonRatio() ? 0 : -1);
                if (i == 0) {
                    return arg0.getName().compareTo(arg1.getName());
                } else {
                    return i;
                }
            }
        }
        );

        //Only the top 10 players are dispalyed on the ranking list
        if (playerList.size() <= 10) {
            for (NimPlayer each : playerList) {
                if (each.getWonRatio() == 100) {
                    System.out.println(each.getWonRatio() + "% | "
                            + (each.getPlayedNum() < 10 ? "0"
                                    + each.getPlayedNum() : each.getPlayedNum())
                            + " games | " + each.getGvnName()
                            + " " + each.getFmlName());
                } else if (each.getWonRatio() >= 10
                        && (each.getWonRatio() < 100)) {
                    System.out.println(each.getWonRatio() + "%  | "
                            + (each.getPlayedNum() < 10 ? "0"
                                    + each.getPlayedNum() : each.getPlayedNum())
                            + " games | " + each.getGvnName() + " "
                            + each.getFmlName());
                } else {
                    System.out.println(each.getWonRatio() + "%   | "
                            + (each.getPlayedNum() < 10 ? "0"
                                    + each.getPlayedNum() : each.getPlayedNum())
                            + " games | " + each.getGvnName() + " "
                            + each.getFmlName());
                }
            }
        } else {
            for (int i = 0; i < 10; i++) {
                if (playerList.get(i).getWonRatio() == 100) {
                    System.out.println(playerList.get(i).getWonRatio() + "% | "
                            + (playerList.get(i).getPlayedNum() < 10 ? "0"
                                    + playerList.get(i).getPlayedNum()
                                    : playerList.get(i).getPlayedNum())
                            + " games | " + playerList.get(i).getGvnName()
                            + " " + playerList.get(i).getFmlName());
                } else if (playerList.get(i).getWonRatio() >= 10
                        && (playerList.get(i).getWonRatio() < 100)) {
                    System.out.println(playerList.get(i).getWonRatio() + "%  | "
                            + (playerList.get(i).getPlayedNum() < 10 ? "0"
                                    + playerList.get(i).getPlayedNum()
                                    : playerList.get(i).getPlayedNum())
                            + " games | " + playerList.get(i).getGvnName() + " "
                            + playerList.get(i).getFmlName());
                } else {
                    System.out.println(playerList.get(i).getWonRatio()
                            + "%   | "
                            + (playerList.get(i).getPlayedNum() < 10 ? "0"
                                    + playerList.get(i).getPlayedNum()
                                    : playerList.get(i).getPlayedNum())
                            + " games | " + playerList.get(i).getGvnName() + " "
                            + playerList.get(i).getFmlName());
                }
            }
        }

    }

    //Before running an advanced Nim, check if the given usernames are valid.
    public static void startAdvanced(String info) {
        String[] beginInfo = info.split(",");

        //Two flags for mark whether the user is a human player or not.
        int a = 0, b = 0;

        boolean checkA = false;
        boolean checkB = false;
        for (int i = 0; i < playerList.size(); i++) {
            String name = beginInfo[1];
            if (playerList.get(i).getName().equals(name)) {

                oneAdvanced.setPlayerA((NimHumanPlayer) playerList.get(i));
                oneAdvanced.getPlayerA().setPlays();
                checkA = true;
                break;
            }
        }
        for (int i = 0; i < playerList.size(); i++) {
            String name = beginInfo[2];
            if (playerList.get(i).getName().equals(name)) {
                oneAdvanced.setPlayerB((NimHumanPlayer) playerList.get(i));
                oneAdvanced.getPlayerB().setPlays();
                checkB = true;
                break;
            }
        }
        if (checkA == false || checkB == false) {
            System.out.println("One of the players does not exist.");
        } else {
            oneAdvanced.setScanner(scanner);
            oneAdvanced.runGame(beginInfo, a, b);
        }
    }
}
