import java.util.*;
import java.io.*;

/**
 * @author aparrnaa
 * Creation Date: 07/14/2017
 * Last Modified Date: 07/15/2017
 * Description:
 * The class provides the mechanism to auto play the game with multiple/single players for a defined number of times as given by user
 */
public class P2A3_Raghuraman_AUTOPLAY_aparrnar extends P2A3_Raghuraman_GAME_aparrnar {
	public static HashMap <Integer, String> gameResults = new HashMap <Integer, String>();
		/**
	     * @param numberOfPlayers - the number of players playing the game
	     * This function starts the game with the number of players
	     * Generates a letter randomly and updates the guessword to see if the user guess is right/wrong.
	     * @return String- containing winner of game
	     */
	@Override
	public String startGame(int numberOfPlayers) {
        boolean totalGameStatus = false; //to indicate if the game has been finished, track when to stop the game, false indicates that the game is not over
        boolean badGuess = false; //check if player guesses a valid string
        boolean doAgain = false;
        char playerCharGuess;
        String winningPlayer = null;
        int numOfGameLeavers = 0; //keep track of number of players leaving the game
        tempGuessedWord = new StringBuilder(); 
        int playerCounter; //counter to loop through players
        HashMap < Integer, ArrayList < Character >> charactersEntered = new HashMap < Integer, ArrayList < Character >> (); //keep track of the characters entered by each of the players
        //the key is the playerID and the value is the arraylist of characters entered by that player

        for (int counter = 0; counter < guessWord.length(); counter++)
            tempGuessedWord.append("_"); //initialize tempGuessedWord with _ equal to the length of the word to be guessed

        boolean charFound = false; //set to true if the character guessed by player is in the string, false otherwise
        System.out.println("----------------------------------");
        System.out.println("Game starting!");
        System.out.println("----------------------------------");
        P2A2_Raghuraman_PLAYER_aparrnar player[] = new P2A2_Raghuraman_PLAYER_aparrnar[numberOfPlayers];
        initializePlayers(player, numberOfPlayers); 
        System.out.println("Your word is ");
        System.out.println();
        for (int dashCounter = 0; dashCounter < guessWord.length(); dashCounter++) //Printing dashes for the chosen guessWord length
            System.out.print("_");
        System.out.println();
        System.out.println("Your Hint is: " + guessHint); //Print the hint for the guessWord
        do {
            for (playerCounter = 0; playerCounter < numberOfPlayers; playerCounter++) {
                if (player[playerCounter].getGameStatus() == true) //allow player to play only if status of that player is "in the game"
                {
                    System.out.println("----------------------------------");
                    System.out.println("Turn of " + player[playerCounter].getName());
                    System.out.println("----------------------------------");
                    System.out.println("Guessing..");
                            do {
                                playerCharGuess = generateRandomCharacter();
                                System.out.println("The character guessed is "+playerCharGuess);
                            	badGuess = checkBadGuess(playerCharGuess, playerCounter, charactersEntered);
                            } while (badGuess == true);
                            updateEnteredCharacters(charactersEntered, playerCounter,playerCharGuess);
                            charFound = updateUserGuessedWord(tempGuessedWord, playerCharGuess);
                           
                            if (charFound == false) //the guessed character is not in the string
                            {
                            	numOfGameLeavers += guessIsWrong(player, playerCounter);
                            } else //character guessed by player found in string
                            {
                            	System.out.println("Correct Guess!");
                                if (tempGuessedWord.toString().equalsIgnoreCase(guessWord)) //player has guessed the word
                                {
                                	int val = guessIsRight(player, playerCounter);
                                	winningPlayer = player[playerCounter].getName();
                                	totalGameStatus = true;
                                	if(val == 1)
                                		return winningPlayer;
                                	
                                } else {
                                    System.out.println("Guessed Word until now: " + tempGuessedWord.toString());
                                    System.out.println("You have made " + player[playerCounter].wrongTicks + " wrong guesses!");
                                    System.out.println("Current Bomb color is at " + player[playerCounter].getBombColor());
                                    System.out.println("Your Hint is: " + guessHint); //Print the hint for the guessWord
                                }
                            }
                } //if
                boolean trial = checkForNoPlayers(numberOfPlayers, numOfGameLeavers, winningPlayer); 
                if(trial) 
                	return "No player";
            } //for
        } while (totalGameStatus == false);
		return "dummy"; //control does not reach here
	}  
     
	 /**
     * @param player - the array of player objects
     * @param numberOfPlayers - the number of players playing the game
     * The function initializes the player objects with their names
     */
    @Override
	public void initializePlayers(P2A2_Raghuraman_PLAYER_aparrnar player[], int numberOfPlayers)
    {
    	//initialize players after getting their names
        for (int playerCounter = 0; playerCounter < numberOfPlayers; playerCounter++) {
            String name = "Player "+playerCounter;
            player[playerCounter] = new P2A2_Raghuraman_PLAYER_aparrnar(name);
        }
    }
	/**
	 * @return character
	 * Generates a random character and return
	 */
	public Character generateRandomCharacter()
	{
		Random rand = new Random();
		char c = (char)(rand.nextInt(26) + 'a');
		return c;
	}
	/**
	 * @throws IOException
	 * Get from user the number of players, number of times the game is to be played
	 * Pick the guessWord and hint
	 * Make a call to startGame() for the given number of times to run the game and display the winner of all games in a consolidated format
	 */
	public void getDetailsForAutoPlay() throws IOException
    {
    	Scanner input = new Scanner(System.in);
    	String filename;
    	System.out.println("Enter the filename containing words and hints");
    	filename = input.next();
    	P2A2_Raghuraman_QUESTION_aparrnar question = new P2A2_Raghuraman_QUESTION_aparrnar(filename);
    	System.out.println("----------------------------------");
    	System.out.println("Computer Auto Play Details");
    	System.out.println("----------------------------------");
    	System.out.println("Enter the number of players");
    	int numPlayers = input.nextInt();
    	System.out.println("Enter the number of times the game should be played: ");
    	int numTimes = input.nextInt();
    	for(int counter = 1; counter <=  numTimes; counter++)
    	{
    		System.out.println("Game: "+(counter));
    		question.getFileContent();
            guessWord = question.getWord();
            guessHint = question.getHint();
    		String winResult = startGame(numPlayers);
    		gameResults.put(counter, winResult);
    	}
    	printResults();
    	
    }
	
	/**
	 * Print the result in a table format
	 */
	public void printResults()
	{
		System.out.println("-------------------");
	    System.out.printf("|%5s | %5s|", "Game Number", "Winner");
	    System.out.println();
	    System.out.println("-------------------");

	    for(int objectCounter = 1; objectCounter <= gameResults.size(); objectCounter++)
	    {
	      System.out.format("|%5d | %10s|", objectCounter, gameResults.get(objectCounter) );
	      System.out.println();
	      System.out.println("-------------------");
	    }
	    
	}
    }
    
