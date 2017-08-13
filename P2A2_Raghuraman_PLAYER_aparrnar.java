import java.io.*;
import java.util.*;

/**
 * @author aparrnaa
 * Creation Date: 07/14/2017
 * Last Modified Date: 07/15/2017
 * Description:
 * The class describes a player
 */
public class P2A2_Raghuraman_PLAYER_aparrnar {
	public String name; //player name
	public boolean gameStatus; //true if the player is in the game, false if the player is out of the game
	public int wrongTicks; //number of wrong guesses made by the player
	public String bombColor; //bomb color for that player based on the number of wrong guesses
	/**
	 * Default constructor
	 */
	P2A2_Raghuraman_PLAYER_aparrnar()
	{
		this.name = null;
		this.gameStatus = true;
		this.wrongTicks = 0;
		this.bombColor = "NO COLOR";
	}
	
	/**
	 * @param playerName- name of the player
	 */
	P2A2_Raghuraman_PLAYER_aparrnar(String playerName)
	{
		name = playerName;
		gameStatus = true;
		wrongTicks = 0;
		this.bombColor = "NO COLOR";
	}
	
	/**
	 * Increment the number of wrong guesses
	 */
	public void incrementTicks()
	{
		this.wrongTicks++;
	}
	
	/**
	 * @return gameStatus - whether player is in the game or not
	 */
	public boolean getGameStatus()
	{
		return gameStatus;
	}
	
	/**
	 * @param status - set the gameStatus to true or false
	 */
	public void setGameStatus(boolean status)
	{
		this.gameStatus = status;
	}
	
	/**
	 * @return bombColor - color of the bomb based on number of wrong guesses
	 */
	public String getBombColor()
	{
		return this.bombColor;
	}
	
	/**
	 * @param wrongTicks -number of wrong guesses made by the player
	 */
	public void setBombColor(int wrongTicks) {
		switch(wrongTicks)
		{
		case 0: bombColor = "NO COLOR"; 
		break;
		case 1: bombColor = "RED"; 
		break;
		case 2: bombColor = "ORANGE"; 
		break;
		case 3: bombColor = "YELLOW"; 
		break;
		case 4: bombColor = "GREEN";
		break;
		case 5: bombColor = "BLUE"; 
		break;
		case 6: bombColor = "PURPLE";
		break;
		default: bombColor = "INVALID";
		break;
		}
	}

	/**
	 * @return name - name of the player
	 */
	public String getName() {
		return name;
	}
}
