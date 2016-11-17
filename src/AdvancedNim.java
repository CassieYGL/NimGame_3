
/*
 * This class is another type of Nim game, implementing an interface 'Move' to 
 distinguish from original NimGame. It overrides the method 'runGame' to play an 
 advanced Nim game.
 Yuge Liang 28/05/2015
 */
import java.io.Serializable;
import java.util.Scanner;

public class AdvancedNim implements Move, Serializable {

    private int leftStone;
    private int upperBound;
    private NimHumanPlayer playerA;
    private NimHumanPlayer playerB;
    private NimAIPlayer AIone;

    transient Scanner reader;

    /*constructor*/
    public AdvancedNim() {
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

    public void runGame(String[] info, int a, int b) {

        int initialNum = Integer.parseInt(info[0]);

        // define a flag to mark which player wins
        int i = 0, flag = 0;
        int m = 0, n = 0;
        System.out.println();
        System.out.println("Initial stone count: " + info[0]);
        System.out.printf("Stones display:");
        for (i = 1; i <= initialNum; i++) {
            System.out.print(" <" + i + ",*>");
        }
        System.out.println();
        System.out.println("Player 1: " + getPlayerA().getGvnName() + " "
                + getPlayerA().getFmlName());
        System.out.println("Player 2: " + getPlayerB().getGvnName() + " "
                + getPlayerB().getFmlName());

        System.out.println();
        System.out.printf(initialNum + " stones left:");
        for (i = 1; i <= initialNum; i++) {
            System.out.print(" <" + i + ",*>");
        }

        System.out.println();
        setLeft(initialNum);

        String move = "";
        boolean[] available = new boolean[initialNum];
        for (i = 0; i < initialNum; i++) {
            available[i] = true;
        }
        while (getLeft() > 0) {

            boolean done = false;
            System.out.println(getPlayerA().getGvnName()
                    + "'s turn - which to remove?");
            getPlayerA().setScanner(reader);
            move = getPlayerA().advancedMove();

            //Handle exception when user's input is not valid.
            while (!done) {
                try {

                    String[] input = move.split(" ");

                    if (Integer.parseInt(input[1]) > 2) {
                        throw new Exception();
                    } else if (Integer.parseInt(input[0]) > initialNum
                            || (Integer.parseInt(input[0])
                            + Integer.parseInt(input[1]) - 1) > initialNum) {
                        throw new Exception();
                    } else if (available[Integer.parseInt(input[0]) - 1]
                            == false) {
                        throw new Exception();
                    } else if (available[Integer.parseInt(input[0]) - 1] == true
                            && available[Integer.parseInt(input[0])
                            + Integer.parseInt(input[1]) - 2] == false) {
                        throw new Exception();
                    }
                    done = true;

                } catch (Exception e) {
                    System.out.println("Invalid move.");
                    System.out.println();
                    System.out.printf(getLeft() + " stones left:");
                    for (i = 0; i < available.length; i++) {
                        if (available[i] == true) {
                            System.out.print(" <" + (i + 1) + ",*>");
                        }
                        if (available[i] == false) {
                            System.out.print(" <" + (i + 1) + ",x>");
                        }
                    }
                    //Input again.
                    System.out.print("\n");
                    System.out.println(getPlayerA().getGvnName()
                            + "'s turn - which to remove?");
                    getPlayerA().setScanner(reader);
                    move = getPlayerA().advancedMove();

                }
            }

            /*According to valid input, modifiy current remained stones 
             as removed.*/
            String[] ss = move.split(" ");
            m = Integer.parseInt(ss[0]);
            n = Integer.parseInt(ss[1]);

            setLeft(getLeft() - n);

            // if there are no stones remaining, then break 
            if (getLeft() == 0) {
                break;
            }

            if (n == 1) {
                available[m - 1] = false;
            } else {
                available[m - 1] = false;
                available[m] = false;
            }

            System.out.println();
            System.out.printf(getLeft() + " stones left:");
            for (i = 0; i < available.length; i++) {
                if (available[i] == true) {
                    System.out.print(" <" + (i + 1) + ",*>");
                }
                if (available[i] == false) {
                    System.out.print(" <" + (i + 1) + ",x>");
                }
            }
            System.out.println();
            flag = 1;

            if (getLeft() == 0) {
                break;
            }
            
            done = false;
            System.out.println(getPlayerB().getGvnName() + "'s turn - "
                    + "which to remove?");
            getPlayerB().setScanner(reader);
            move = getPlayerB().advancedMove();

            //Handle exception when user's input is not valid.
            while (!done) {
                try {

                    String[] input = move.split(" ");
                    if (Integer.parseInt(input[1]) > 2) {
                        throw new Exception();
                    } else if (Integer.parseInt(input[0]) > initialNum
                            || (Integer.parseInt(input[0])
                            + Integer.parseInt(input[1]) - 1) > initialNum) {
                        throw new Exception();
                    } else if (available[Integer.parseInt(input[0]) - 1]
                            == false) {
                        throw new Exception();
                    } else if (available[Integer.parseInt(input[0]) - 1] == true
                            && available[Integer.parseInt(input[0])
                            + Integer.parseInt(input[1]) - 2] == false) {
                        throw new Exception();
                    }
                    done = true;

                } catch (Exception e) {
                    System.out.println("Invalid move.");
                    System.out.println();
                    System.out.printf(getLeft() + " stones left:");
                    for (i = 0; i < available.length; i++) {
                        if (available[i] == true) {
                            System.out.print(" <" + (i + 1) + ",*>");
                        }
                        if (available[i] == false) {
                            System.out.print(" <" + (i + 1) + ",x>");
                        }
                    }
                    //Input again.
                    System.out.print("\n");
                    System.out.println(getPlayerB().getGvnName()
                            + "'s turn - which to remove?");
                    getPlayerB().setScanner(reader);
                    move = getPlayerB().advancedMove();

                }
            }

            /*According to valid input, modifiy current remained stones 
             as removed.*/
            ss = move.split(" ");
            m = Integer.parseInt(ss[0]);
            n = Integer.parseInt(ss[1]);

            setLeft(getLeft() - n);

            // if there are no stones remaining, then break 
            if (getLeft() == 0) {
                break;
            }

            if (n == 1) {
                available[m - 1] = false;
            } else {
                available[m - 1] = false;
                available[m] = false;
            }

            System.out.println();
            System.out.printf(getLeft() + " stones left:");
            for (i = 0; i < available.length; i++) {
                if (available[i] == true) {
                    System.out.print(" <" + (i + 1) + ",*>");
                }
                if (available[i] == false) {
                    System.out.print(" <" + (i + 1) + ",x>");
                }
            }
            System.out.println();
            flag = 0;
        }
        System.out.println();
        System.out.println("Game Over");

        if (flag == 0) {
            getPlayerA().setWons();
            System.out.println(getPlayerA().getGvnName() + " "
                    + getPlayerA().getFmlName() + " wins!");
        } else {
            getPlayerB().setWons();
            System.out.println(getPlayerB().getGvnName() + " "
                    + getPlayerB().getFmlName() + " wins!");
        }

    }

}
