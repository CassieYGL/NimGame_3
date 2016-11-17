# NimGame_3

This is a Java version implementation of Nim.

In this version, a hunman player versus an AI player controlled autimatically by the computer.

No matter what the human user moves, a victory strategy is guaranteed for the AI player.

The Nimsys class implements a series of operations about the information of
users involved in the system of Nim as well as starting a new game.
 
The  NimAIPlayer class is provided as an Intelligent robot which holds two win stratedies 
 both for original Nim game and advanced Nim game. It is derived from the super
 calss NimPlayer.	
 
Move is an interface which has a method called 'runGame'. By implementing
this interface, both NimGame(the original one) and AdvancedNim can run a game according to their specific rules.

 The AdvancedNim class is another type of Nim game, implementing an interface 'Move' to 
 distinguish from original NimGame. It overrides the method 'runGame' to play an 
 advanced Nim game.
