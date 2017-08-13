import java.io.IOException;
import java.util.*;
/**
 * @author aparrnaa
 * Creation Date: 07/14/2017
 * Last Modified Date: 07/15/2017
 * Description:
 * The class does the following:
 * Prompts the player to enter their name
 * Prints welcome message for the player
 * Randomly generates the guessWord from the contents of a file
 * Allows player to play in single or multi player mode
 */
public class P2A2_Raghuraman_aparrnar {

	public String guessWord; //stores the word to be guessed
	public String guessHint; //stores the hint for the guessword
    public StringBuilder tempGuessedWord; //stores a string containing the guessed characters of a word at a given point in time
    
    /**
     * @param username
     * The function takes a username as input and prints a customized welcome message for the user and also displays the game rules
     */
    public void welcomeUser(String username) {
        System.out.println("Hello " + username);
        System.out.println("Welcome to the game!");
        System.out.println("----------------------------------");
        System.out.println("ChipARoonie");
        System.out.println("----------------------------------");
        System.out.println("Instructions for the game");
        System.out.println("----------------------------------");
        System.out.println("The game of ChipARoonie! challenges a player to guess a secret word to defuse the color bomb by guessing different letters contained within that word.  Every wrong guess gets the bomb closer to going off.");
        System.out.println("The player wins by guessing all of the correct letters in the secret word. When the player wins the game, you should print to the console that he has won, along with the secret word.");
        System.out.println("For each round of the game, the player is prompted to input a guessed letter to see if that letter is in the secret word");
        System.out.println("If the guessed letter is contained in the secret word, the player has won that round, and the guessed word thus far is printed (consisting of blank underscores and corrrectly guessed letters) and the player earns a tick");
        System.out.println("The ticks add up. A player can only accumulate 6 incorrect ticks or he loses the game and the bomb goes off");
        System.out.println("For each round that the player guesses a letter incorrectly, you should also print out the color that the player has failed thus far, based on how many ticks the player has:");
        System.out.println("Each tick will correspond to the bomb exploding sooner, for each incorrectly guessed letter:");
        System.out.println("1 tick = red");
        System.out.println("2 ticks = orange");
        System.out.println("3 ticks = yellow");
        System.out.println("4 ticks = green");
        System.out.println("5 ticks = blue");
        System.out.println("6 ticks = purple");
        System.out.println("BOOM!!!");
        System.out.println("The player wins by guessing all of the correct letters in the secret word.");
        System.out.println("----------------------------------");

    }

    /**
     * @param player - the array of player objects
     * @param playerCounter - counter for player objects
     * The function prints the name of the player who has won the game
     */
    public int guessIsRight(P2A2_Raghuraman_PLAYER_aparrnar player[], int playerCounter)
    {
    	
        System.out.println("YAYYY! You won the game!");
        player[playerCounter].setGameStatus(false); //particular player's game status set to "Not in game"
        System.out.println("Player " + (player[playerCounter].getName()) + " has won the game!");
        return 1;
    }
    
    /**
     * @param player - the array of player objects
     * @param playerCounter- counter for player objects
     * @return an integer value to say whether a player has left the game or not
     * The function updates the ticks of a player and the bomb color after a wrong guess
     * It also prints the word guessed by the user upto that point
     */
    public int guessIsWrong(P2A2_Raghuraman_PLAYER_aparrnar player[], int playerCounter)
    {
    	System.out.println("Sorry! Wrong Guess!");
        if (player[playerCounter].wrongTicks != 5) //player still has guesses left
        {
            player[playerCounter].incrementTicks(); //increment number of bad guesses
            System.out.println("You have made " + player[playerCounter].wrongTicks + " wrong guesses!");
            player[playerCounter].setBombColor(player[playerCounter].wrongTicks); //set bomb color based on number of wrong guesses
            System.out.println("Current Bomb color is at " + player[playerCounter].getBombColor());
            if(player[playerCounter].wrongTicks == 1) //first incorrect guess
            	System.out.println("The bomb has started ticking!");
        } else {
            System.out.println("You have exhausted all your guesses!");
            player[playerCounter].incrementTicks();
            player[playerCounter].setBombColor(player[playerCounter].wrongTicks);
            System.out.println("Bomb color set to " + player[playerCounter].getBombColor());
            System.out.println("BOOOOOM! Bomb exploded! Sorry, you lost " + (player[playerCounter].getName()));
            player[playerCounter].setGameStatus(false); //particular player's game status set to "Not in game"
            return 1;
        }

        System.out.println("Guessed Word until now: " + tempGuessedWord.toString());
        System.out.println("Your Hint is: " + guessHint); //Print the hint for the guessWord
        return 0;
    }
    
    /**
     * @param player - the array of player objects
     * @param numberOfPlayers - the number of players playing the game
     * The function initializes the player objects with their names
     */
    public void initializePlayers(P2A2_Raghuraman_PLAYER_aparrnar player[], int numberOfPlayers)
    {
    	//initialize players after getting their names
        for (int playerCounter = 0; playerCounter < numberOfPlayers; playerCounter++) {
            System.out.println("Enter Player " + (playerCounter + 1) + " name:");
            Scanner input3 = new Scanner(System.in);
            String name = input3.next();
            player[playerCounter] = new P2A2_Raghuraman_PLAYER_aparrnar(name);
        }
    }

    /**
     * @param tempGuessedWord - the String containing the word guessed until this stage
     * @param playerCharGuess - character guessed by the player
     * @return charFound- return if playerCharGuess is found in guessWord or not
     * Check if the character guessed by the player is in the guessWord or not
     */
    public boolean updateUserGuessedWord(StringBuilder tempGuessedWord, Character playerCharGuess)
    {
    	boolean charFound = false;
    	//update tempGuessedWord based on the character guessed
        for (int charCounter = 0; charCounter < guessWord.length(); charCounter++) {
            if (guessWord.charAt(charCounter) == playerCharGuess && tempGuessedWord.charAt(charCounter) == '_') //check for the occurence of the userguessed character in the actual string, and ensure that the previously found characters are not replaced
            {
                tempGuessedWord.setCharAt(charCounter, playerCharGuess); //append to tempGuessedWord
                charFound = true;
            } else if (tempGuessedWord.charAt(charCounter) != '_') //skip already guessed characters in the tempGuessedWord
                continue;
            else
                tempGuessedWord.setCharAt(charCounter, '_'); //not guessed yet, so append _
        }
        return charFound;
    }
    
    /**
     * @param pchar - character guessed by the player
     * @param playerCtr - counter for player objects
     * @param charEntered - HashMap containing the characters entered by each player in all guesses till the current stage
     * @return true if a non-alphabetic character is entered or a character is entered by the player for a second time, false otherwise
     * Checks for the validity of the input character entered by player
     */
    public boolean checkBadGuess(Character pchar, int playerCtr, HashMap< Integer, ArrayList < Character >> charEntered)
    {
    	if (!Character.isLetter(pchar)) //player does not enter a valid alphabet
        {
            System.out.println("Enter a valid alphabet!");
            return true;
        } else if (charEntered.get(playerCtr) != null && charEntered.get(playerCtr).contains(pchar)) {
            System.out.println("Character already entered! Please enter again");
            return true;
        }
        else
    	return false;
    }
    
    /**
     * @param numberOfPlayers - the number of players playing the game
     * @param numOfGameLeavers - integer to keep track of the number of players who are out of the game until the current stage
     * Check if all players are out of the game at a particular stage of the game
     * @return true if all players have exited, else false
     */
    public boolean checkForNoPlayers(int numberOfPlayers, int numOfGameLeavers, String winningPlayer)
    {
    	if (numberOfPlayers == numOfGameLeavers) {
            System.out.println("All players have exited the game..");
            System.out.println("Nobody has won the game");
            winningPlayer = "No Player";
            return true;
        }
    	else
    		return false;
    }
    
    /**
     * @param charactersEntered - HashMap containing the characters entered by each player in all guesses till the current stage
     * @param playerCounter - counter for player objects
     * @param playerCharGuess - character guessed by the player
     * Update the characters entered by each player
     */
    public void updateEnteredCharacters(HashMap< Integer, ArrayList < Character >> charactersEntered, int playerCounter, Character playerCharGuess)
    {
    	if (charactersEntered.containsKey(playerCounter)) {
            charactersEntered.get(playerCounter).add(playerCharGuess); //add the character to the list of characters known
        } else {
            charactersEntered.put(playerCounter, new ArrayList <Character>());
            charactersEntered.get(playerCounter).add(playerCharGuess);
        }
    }
    
    /**
     * @param numberOfPlayers - the number of players playing the game
     * This function starts the game with the number of players
     * Takes an input from the user as either a word or letter and updates the word to see if the user guess is right/wrong.
     * @return String- containing winner of game
     */
    public String startGame(int numberOfPlayers) {
    		String winningPlayer = null;
            boolean totalGameStatus = false; //to indicate if the game has been finished, track when to stop the game, false indicates that the game is not over
            boolean badGuess = false; //check if player guesses a valid string
            boolean doAgain = false;
            String playerGuess;
            char playerCharGuess;
            int numOfGameLeavers = 0; //keep track of number of players leaving the game
            tempGuessedWord = new StringBuilder(); 
            int playerCounter; //counter to loop through players
            HashMap < Integer, ArrayList < Character >> charactersEntered = new HashMap < Integer, ArrayList < Character >> (); //keep track of the characters entered by each of the players
            //the key is the playerID and the value is the arraylist of characters entered by that player

            for (int counter = 0; counter < guessWord.length(); counter++)
                tempGuessedWord.append("_"); //initialize tempGuessedWord with _ equal to the length of the word to be guessed

            boolean charFound = false; //set to true if the character guessed by player is in the string, false otherwise
            Scanner input3 = new Scanner(System.in);
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
                        System.out.println("Turn of player " + player[playerCounter].getName());
                        System.out.println("----------------------------------");
                        System.out.println("Do you want to guess a word or a letter? Press 1 for word and 2 for letter");
                        System.out.println("----------------------------------");
                        int userChoice = input3.nextInt();
                        switch (userChoice) {
                            case 1: //word is guessed

                                do {
                                    System.out.println("Enter the word to be guessed");
                                    playerGuess = input3.next();
                                    if (playerGuess.trim().length() == 0) //player enters space as a string
                                    {
                                        badGuess = true;
                                    }
                                } while (badGuess == true);
                                
                                if (playerGuess.equalsIgnoreCase(guessWord)) //player has guessed the correct word
                                {
                                	int val = guessIsRight(player, playerCounter);
                                	winningPlayer = player[playerCounter].getName();
                                	if(val == 1)
                                		return winningPlayer;
                                	totalGameStatus = true;
                                    
                                } else //player made the wrong guess
                                {
                                	numOfGameLeavers += guessIsWrong(player, playerCounter);
                                }
                                break;

                            case 2:
                                do {
                                	System.out.println("Enter the character to be guessed");
                                    playerCharGuess = input3.next().charAt(0);
                                	badGuess = checkBadGuess(playerCharGuess, playerCounter, charactersEntered);
                                } while (badGuess == true);
                                updateEnteredCharacters(charactersEntered, playerCounter,playerCharGuess);
                                charFound = updateUserGuessedWord(tempGuessedWord, playerCharGuess);
                               
                                if (charFound == false) //the guessed character is not in the string
                                {
                                	numOfGameLeavers = guessIsWrong(player, playerCounter);
                                } else //character guessed by player found in string
                                {
                                	System.out.println("Correct Guess!");
                                    if (tempGuessedWord.toString().equalsIgnoreCase(guessWord)) //player has guessed the word
                                    {
                                    	int val = guessIsRight(player, playerCounter);
                                    	winningPlayer = player[playerCounter].getName();
                                    	if(val == 1)
                                    		return winningPlayer;
                                    } else {
                                        System.out.println("Guessed Word until now: " + tempGuessedWord.toString());
                                        System.out.println("You have made " + player[playerCounter].wrongTicks + " wrong guesses!");
                                        System.out.println("Current Bomb color is at " + player[playerCounter].getBombColor());
                                        System.out.println("Your Hint is: " + guessHint); //Print the hint for the guessWord
                                    }
                                }
                                break;
                            case 0:
                                System.out.println("Exiting the game...");
                                System.exit(0);
                                break;
                            default:
                                System.out.println("Enter a valid choice between 0, 1 or 2");
                                doAgain = true;
                        } //switch
                    } //if
                    boolean trial = checkForNoPlayers(numberOfPlayers, numOfGameLeavers, winningPlayer); 
                    if(trial)
	                	   return "No Player";
                } //for
            } while (totalGameStatus == false || doAgain == true);
			return "dummy"; //control does not reach this place
        }
        
    /**
     * @param args- an array of Strings
     * @throws IOException
     * Prints the welcome message for the user
     * Gets the filename containing words and clues from the user
     * Prompts the user if they want to play in single or multi player mode
     * Invokes the startGame() function to start the game
     */
    public static void main(String args[]) throws IOException {
        Scanner input = new Scanner(System.in);
        int numberOfPlayers; //used in case of multi player game
        P2A2_Raghuraman_aparrnar obj = new P2A2_Raghuraman_aparrnar();
        String filename;
        boolean doAgain = false;
        System.out.println("Hi User! Welcome to ChipARoonie");
        System.out.println("Enter your name");
        String username = input.next();
        obj.welcomeUser(username); //print Welcome Message
        System.out.println("Enter the name of the file containing the words and hints");
        filename = input.next();
        boolean playerValNotOK = false, userChoiceNotOK = false;
        System.out.println("Let us start the game! ");
        P2A2_Raghuraman_QUESTION_aparrnar question = new P2A2_Raghuraman_QUESTION_aparrnar(filename);
        do {
        	question.getFileContent();
            obj.guessWord = question.getWord();
            obj.guessHint = question.getHint();
            System.out.println("Do you want to play in single or multiplayer mode or exit the game? Press 0 for exit, 1 for Single Player mode and 2 for Multi Player mode");
            int userChoice = input.nextInt();
            switch (userChoice) {
                case 0:
                    System.exit(0);
                    break;
                case 1:
                    System.out.println("----------------------------------");
                    System.out.println("You have chosen Single Player Mode");
                    System.out.println("----------------------------------");
                    doAgain = true;
                    obj.startGame(1); //numberOfPlayers = 1 for single player mode 
                    break;
                case 2:
                    System.out.println("----------------------------------");
                    System.out.println("You have chosen Multi Player Mode");
                    System.out.println("----------------------------------");
                    System.out.println("Enter the number of players");
                    do {
                        playerValNotOK = false;
                        numberOfPlayers = input.nextInt();
                        if (numberOfPlayers < 1) {
                            System.out.println("The number of players in game should be minimum of 1");
                            System.out.println("Please enter a number greater than or equal to 1");
                            playerValNotOK = true;
                        }
                    } while (playerValNotOK == true);
                    doAgain = true;
                    obj.startGame(numberOfPlayers);
                    break;
                default:
                    userChoiceNotOK = true;
                    System.out.println("Invalid choice! Enter again");
            }
        } while (userChoiceNotOK == true ||doAgain == true);
    }
}