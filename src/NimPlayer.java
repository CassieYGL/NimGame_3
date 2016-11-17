
/**
 * This NimPlayer class is an abstract super calss, which incluedes construtors,
 * mutators, accessors and an abstract method 'removeStone', and it has two
 * derived classes: NimAIPlayer and NimHumanPlayer. Yuge Liang 28/May/2015
 */
import java.io.*;
import java.util.*;

public abstract class NimPlayer implements Serializable {

    private String userName;
    private String gName;
    private String fName;
    private int playedNum;
    private int wonNumber;
    private int wonRatio;

    public NimPlayer() {

    }

    public NimPlayer(String username, String gname, String fname) {
        userName = username;
        fName = gname;
        gName = fname;
        playedNum = 0;
        wonNumber = 0;
    }

    public NimPlayer(NimPlayer player) {
        userName = player.userName;
        gName = player.gName;
        fName = player.fName;
    }

    /*Accessor*/
    public String getName() {
        return userName;
    }

    public String getGvnName() {
        return gName;
    }

    public String getFmlName() {
        return fName;
    }

    public int getPlayedNum() {
        return playedNum;
    }

    public int getWonNum() {
        return wonNumber;
    }

    public int getWonRatio() {
        if (playedNum == 0) {
            wonRatio = 0;
        } else {
            wonRatio = (int) Math.round(wonNumber * 100.0 / playedNum);
        }
        return wonRatio;
    }


    /* Mutators */
    public void setFname(String name) {
        fName = name;
    }

    public void setGname(String name) {
        gName = name;
    }

    public void resetStats() {
        playedNum = 0;
        wonNumber = 0;
        wonRatio = 0;
    }

    public void setWons() {
        wonNumber += 1;
    }

    public void setPlays() {
        playedNum += 1;
    }

    public void setWonRatio() {
        if (playedNum == 0) {
            wonRatio = 0;
        } else {
            wonRatio = wonNumber * 100 / playedNum;
        }
    }

    public String toString() {
        return (userName + "," + gName + "," + fName + "," + playedNum
                + " games," + wonNumber + " wins");
    }

    //A abstract method that will be overridded by NimAIPlayer 
    //and NimHumanPlayer.
    public abstract String removeStone(int max, int left);

}
