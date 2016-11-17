
/**
 * This NimGame class includes constructors, mutators and accessors. It also
 * executes the process of running an original Nim game. Yuge Liang 28/May/2015
 */
import java.io.Serializable;
import java.util.Scanner;

public class NimGame implements Serializable, Move {

    private int leftStone;
    private int upperBound;
    private NimHumanPlayer playerA;
    private NimHumanPlayer playerB;
    private NimAIPlayer AIone;
    private NimAIPlayer AItwo;

    transient Scanner reader;

    /*constructor*/
    public NimGame() {
        leftStone = 0;
        upperBound = 0;

    }

    /*Accessors*/
    public int getLeft() {
        return leftStone;
    }

    public int getUpBound() {
        return upperBound;
    }

    public NimHumanPlayer getPlayerA() {
        return playerA;
    }

    public NimHumanPlayer getPlayerB() {
        return playerB;
    }

    public NimAIPlayer getAIone() {
        return AIone;
    }

    public NimAIPlayer getAItwo() {
        return AItwo;
    }

    /*Mutators*/
    public void setScanner(Scanner keyboard) {

        reader = keyboard;
    }

    public void setUpBound(int number) {
        upperBound = number;
    }

    public void setLeft(int number) {
        leftStone = number;
    }

    public void setPlayerA(NimHumanPlayer player) {
        this.playerA = player;
    }

    public void setPlayerB(NimHumanPlayer player) {
        this.playerB = player;
    }

    public void setAIone(NimAIPlayer player) {
        AIone = player;
    }

    public void setAItwo(NimAIPlayer player) {
        AItwo = player;
    }

    //Run a game of Nim
    public void runGame(String[] info, int a, int b) {
        System.out.println();
        System.out.println("Initial stone count: " + info[0]);
        System.out.println("Maximum stone removal: " + info[1]);

        //Decide if the user is a human player or not by the value of a. 
        if (a == 0) {
            System.out.println("Player 1: " + getPlayerA().getGvnName() + " "
                    + getPlayerA().getFmlName());
        } else {
            System.out.println("Player 1: " + getAIone().getGvnName() + " "
                    + getAIone().getFmlName());
        }

        //Decide if the user is a human player or not by the value of b. 
        if (b == 0) {
            System.out.println("Player 2: " + getPlayerB().getGvnName() + " "
                    + getPlayerB().getFmlName());
        } else {
            System.out.println("Player 1: " + getAItwo().getGvnName() + " "
                    + getAItwo().getFmlName());
        }

        int initialNum = Integer.parseInt(info[0]);
        setUpBound(Integer.parseInt(info[1]));
        setLeft(initialNum);
        int i;

        // define a flag to mark which player wins
        int flag = 0;
        int move = 0;

        System.out.println();
        System.out.printf(initialNum + " stones left:");
        for (i = 1; i <= initialNum; i++) {
            System.out.print(" *");
        }
        System.out.println();

        //begin to move
        while (getLeft() >= 1) {

            if (a == 0) {
                System.out.println(getPlayerA().getGvnName()
                        + "'s turn - remove how many?");
                getPlayerA().setScanner(reader);
                move = Integer.parseInt(
                        getPlayerA().removeStone(getUpBound(), getLeft()));

            } else {
                System.out.println(getAIone().getGvnName()
                        + "'s turn - remove how many?");
                move = Integer.parseInt(
                        getAIone().removeStone(getUpBound(), getLeft()));
            }

            setLeft(getLeft() - move);

            // if there are no stones remaining, then break 
            if (getLeft() == 0) {
                break;
            }
            System.out.println();
            System.out.printf(getLeft() + " stones left:");
            for (i = 1; i <= getLeft(); i++) {
                System.out.print(" *");
            }
            System.out.println();
            flag = 1;

            // if there are no stones remaining, then break 
            if (getLeft() == 0) {
                break;
            }

            if (b == 0) {
                System.out.println(getPlayerB().getGvnName() + "'s turn - "
                        + "remove how many?");
                getPlayerB().setScanner(reader);
                move = Integer.parseInt(getPlayerB().removeStone(
                        getUpBound(), getLeft()));

            } else {
                System.out.println(getAItwo().getGvnName() + "'s turn - "
                        + "remove how many?");
                move = Integer.parseInt(getAItwo().removeStone(
                        getUpBound(), getLeft()));
            }

            setLeft(getLeft() - move);

            // if there are no stones remaining, then break 
            if (getLeft() == 0) {
                break;
            }
            System.out.println();
            System.out.printf(getLeft() + " stones left:");
            for (i = 1; i <= getLeft(); i++) {
                System.out.print(" *");
            }
            System.out.println();
            flag = 0;
        }
        System.out.println();
        System.out.println("Game Over");

        if (flag == 1) {
            if (a == 0) {
                getPlayerA().setWons();
                System.out.println(getPlayerA().getGvnName() + " "
                        + getPlayerA().getFmlName() + " wins!");
            } else {
                getAIone().setWons();
                System.out.println(getAIone().getGvnName() + " "
                        + getAIone().getFmlName() + " wins!");
            }
        } else {
            if (b == 0) {
                getPlayerB().setWons();
                System.out.println(getPlayerB().getGvnName() + " "
                        + getPlayerB().getFmlName() + " wins!");
            } else {
                getAItwo().setWons();
                System.out.println(getAItwo().getGvnName() + " "
                        + getAItwo().getFmlName() + " wins!");
            }
        }

    }

}
