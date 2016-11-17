/*
 * This is an interface which has a method called 'runGame'. By implementing
   this interface, both NimGame(the original one) and AdvancedNim can run a
   game according to their specific rules.
   Yuge Liang 28/May/2015
 */


public interface Move {
    public void runGame(String[] info,int a, int b) ;
}
