/*
 NimAIPlayer.java
	
 This class is provided as an Intelligent robot which holds two win stratedies 
 both for original Nim game and advanced Nim game. It is derived from the super
 calss NimPlayer.	
 Yuge Liang 28/05/2015 
 */

import java.io.*;
import java.util.Scanner;
import java.util.StringTokenizer;

public class NimAIPlayer extends NimPlayer implements Testable, Serializable {

    transient Scanner reader;

    public NimAIPlayer() {
    }

    public NimAIPlayer(String username, String gname, String fname) {
        super(username, gname, fname);
    }

    public NimAIPlayer(NimAIPlayer player) {
        super(player);
    }

    public void setScanner(Scanner keyboard) {

        reader = keyboard;
    }

    public String removeStone(int max, int left) {
        String move = "1";
        if (left > 2) {
            if ((left - 1) % (max + 1) != 0) {
                int k = (left - 1) / (max + 1);
                move = Integer.toString(left - (k * (max + 1) + 1));
            }
        } else {
            move = "1";
        }
        return move;
    }

    public String advancedMove(boolean[] available, String lastMove) {
        // the implementation of the victory guaranteed strategy 
        String move = "";

        int i = 0, first = 0, second = 0, third = 0, forth = 0, count = 0;
        int mid = 0;
        int indexA = 0, indexB = 0, indexC = 0, indexD = 0;
        int position = 0, number = 1;

        if (lastMove == "") {
            if (available.length > 2) {
                if (available.length % 2 == 0) {
                    position = available.length / 2;
                    number = 2;
                } else {
                    position = available.length / 2 + 1;
                    number = 1;

                }
            } else if (available.length == 2) {
                position = 1;
                number = 2;
            } else {
                position = 1;
                number = 1;

            }
            move = Integer.toString(position) + " " + Integer.toString(number);
        } else {

            String stf = "";

            for (i = 0; i < available.length; i++) {
                if (available[i] == true) {
                    stf = stf + "T";

                }
                if (available[i] == false) {
                    stf = stf + "F";
                }
            }

            StringTokenizer st = new StringTokenizer(stf, "F");
            count = st.countTokens();

            String[] bu = new String[st.countTokens()];
            i = 0;
            while (st.hasMoreTokens()) {
                bu[i] = st.nextToken();
                i += 1;
            }

            if (count == 1) {
                for (i = 0; i < available.length; i++) {
                    if (available[i] == true) {
                        indexA = i;
                        break;
                    }

                }

                if (bu[0].length() % 2 == 0 && bu[0].length() != 1) {
                    position = bu[0].length() / 2 + indexA;
                    number = 2;
                } else if (bu[0].length() % 2 != 0) {
                    position = bu[0].length() / 2 + indexA + 1;
                    number = 1;
                } else {
                    number = 1;
                    position = indexA + 1;
                }
                move = Integer.toString(position) + " "
                        + Integer.toString(number);

            } else if (count == 2) {

                first = bu[0].length();
                second = bu[1].length();

                for (i = 0; i < available.length; i++) {
                    if (available[i] == true) {
                        indexA = i;
                        break;
                    }

                }
                for (i = indexA + first; i < available.length; i++) {
                    if (available[i] == true) {
                        indexB = i;
                        break;
                    }
                }

                if (first < second) {
                    if ((first + second) % 2 != 0) {
                        mid = (first + second) / 2;
                        position = mid - first + indexB + 1;
                        number = 1;

                    } else {
                        mid = (first + second) / 2 - 1;
                        position = mid - first + indexB + 1;
                        number = 2;
                    }
                } else if (first > second) {

                    if ((first + second) % 2 != 0) {
                        mid = (first + second) / 2;
                        position = mid + indexA + 1;
                        number = 1;
                    } else {
                        mid = (first + second) / 2 - 1;
                        position = mid + indexA + 1;
                        number = 2;
                    }
                } else {
                    position = indexA + 1;
                    if (first == 1) {
                        number = 1;
                    } else {
                        number = 2;
                    }
                }
                move = Integer.toString(position) + " " + Integer.toString(number);
            } else if (count == 3) {   //3 heaps
                int flag = 0;
                first = bu[0].length();
                second = bu[1].length();
                third = bu[2].length();
                for (i = 0; i < available.length; i++) {
                    if (available[i] == true) {
                        indexA = i;
                        break;
                    }

                }
                for (i = indexA + first; i < available.length; i++) {
                    if (available[i] == true) {
                        indexB = i;
                        break;
                    }

                }

                for (i = indexB + second; i < available.length; i++) {
                    if (available[i] == true) {
                        indexC = i;
                        break;
                    }

                }

                String str1 = Integer.toBinaryString(first);
                String str2 = Integer.toBinaryString(second);
                String str3 = Integer.toBinaryString(third);

                byte b1[] = new byte[4];
                b1[3] = (byte) (first & 0xFF);
                b1[2] = (byte) (first >> 8 & 0xFF);
                b1[1] = (byte) (first >> 16 & 0xFF);
                b1[0] = (byte) (first >> 24 & 0xFF);

                byte b2[] = new byte[4];
                b2[3] = (byte) (second & 0xFF);
                b2[2] = (byte) (second >> 8 & 0xFF);
                b2[1] = (byte) (second >> 16 & 0xFF);
                b2[0] = (byte) (second >> 24 & 0xFF);

                byte b3[] = new byte[4];
                b3[3] = (byte) (third & 0xFF);
                b3[2] = (byte) (third >> 8 & 0xFF);
                b3[1] = (byte) (third >> 16 & 0xFF);
                b3[0] = (byte) (third >> 24 & 0xFF);

                for (i = 0; i < 4; i++) {
                    if ((b1[i] ^ b2[i] ^ b3[i]) != 0) {
                        flag = 1;
                        break;
                    }
                }

                // not balance, has a chance to win
                if (flag == 1) {
                    if (first + second + third == 3) {//1,1,1
                        position = indexA + 1;
                        number = 1;
                    } else if (first + second + third == 4) {//1,1,2

                        if (first == 2) {
                            position = indexA + 1;

                        } else if (second == 2) {
                            position = indexB + 1;
                        } else {
                            position = indexC + 1;
                        }
                        number = 2;

                    } else if (first + second + third == 5) {
                        //1,1,3 or 1,2,2                        
                        int max = first;
                        if (second > max) {
                            max = second;
                        }
                        if (third > max) {
                            max = third;
                        }
                        if (max == 3) {
                            if (first == 3) {
                                position = indexA + 2;

                            } else if (second == 3) {
                                position = indexB + 2;
                            } else {
                                position = indexC + 2;
                            }
                        } else if (max == 2) {
                            if (first == 1) {
                                position = indexA + 1;
                            } else if (second == 2) {
                                position = indexB + 1;
                            } else {
                                position = indexC + 1;
                            }
                        }
                        number = 1;
                    } else if (first + second + third == 7) {//1,2,4 or 1,3,3
                        if (first == 3 || first == 4) {
                            position = indexA + 1;

                        } else if (second == 3 || second == 4) {
                            position = indexB + 1;
                        } else {
                            position = indexC + 1;
                        }
                        number = 1;
                    }
                } else {
                    position = indexA + 1;
                    number = 1;
                }
                move = Integer.toString(position) + " " + Integer.toString(number);
            } else if (count == 4) {   //4 heaps
                int flag = 0;
                first = bu[0].length();
                second = bu[1].length();
                third = bu[2].length();
                forth = bu[3].length();

                for (i = 0; i < available.length; i++) {
                    if (available[i] == true) {
                        indexA = i;
                        break;
                    }

                }

                for (i = indexA + first; i < available.length; i++) {
                    if (available[i] == true) {
                        indexB = i;
                        break;
                    }

                }

                for (i = indexB + second; i < available.length; i++) {
                    if (available[i] == true) {
                        indexC = i;
                        break;
                    }

                }
                for (i = indexC + third; i < available.length; i++) {
                    if (available[i] == true) {
                        indexD = i;
                        break;
                    }
                }

                String str1 = Integer.toBinaryString(first);
                String str2 = Integer.toBinaryString(second);
                String str3 = Integer.toBinaryString(third);
                String str4 = Integer.toBinaryString(forth);

                byte b1[] = new byte[4];
                b1[3] = (byte) (first & 0xFF);
                b1[2] = (byte) (first >> 8 & 0xFF);
                b1[1] = (byte) (first >> 16 & 0xFF);
                b1[0] = (byte) (first >> 24 & 0xFF);

                byte b2[] = new byte[4];
                b2[3] = (byte) (second & 0xFF);
                b2[2] = (byte) (second >> 8 & 0xFF);
                b2[1] = (byte) (second >> 16 & 0xFF);
                b2[0] = (byte) (second >> 24 & 0xFF);

                byte b3[] = new byte[4];
                b3[3] = (byte) (third & 0xFF);
                b3[2] = (byte) (third >> 8 & 0xFF);
                b3[1] = (byte) (third >> 16 & 0xFF);
                b3[0] = (byte) (third >> 24 & 0xFF);

                byte b4[] = new byte[4];
                b3[3] = (byte) (forth & 0xFF);
                b3[2] = (byte) (forth >> 8 & 0xFF);
                b3[1] = (byte) (forth >> 16 & 0xFF);
                b3[0] = (byte) (forth >> 24 & 0xFF);

                for (i = 0; i < 4; i++) {
                    if ((b1[i] ^ b2[i] ^ b3[i] ^ b4[i]) != 0) {
                        flag = 1;
                        break;
                    }
                }

                if (flag == 1) {

                    //1,1,1,4 or 1,1,2,3 not balance
                    if (first + second + third + forth == 7) {

                        if (first == 1) {
                            position = indexA + 1;

                        } else if (second == 1) {
                            position = indexB + 1;
                        } else {
                            position = indexC + 1;
                        }
                        number = 1;
                    } else if (first + second + third + forth == 5) { //1,1,2,1   

                        if (first == 2) {
                            position = indexA + 1;

                        } else if (second == 2) {
                            position = indexB + 1;
                        } else if (third == 2) {
                            position = indexC + 1;
                        } else if(forth==2){
                            position = indexD + 1;
                        }
                        number = 1;
                    } else if (first + second + third + forth == 6) {//1,1,1,3 

                        if (first == 3) {
                            position = indexA + 1;

                        } else if (second == 3) {
                            position = indexB + 1;
                        } else {
                            position = indexC + 1;
                        }
                        number = 2;
                    }
                } else { //already balanced
                    position = indexA + 1;
                    number = 1;
                }
           move = Integer.toString(position) + " " + Integer.toString(number);
            }
        }
        return move;
    }
}
