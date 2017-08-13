import java.io.*;
import java.util.*;
/**
 * @author aparrnaa
 * Creation Date: 07/14/2017
 * Last Modified Date: 07/15/2017
 * Description:
 * The class prints a welcome message for the user.
 * it then creates an object of the P2A3_Raghuraman_AUTOPLAY_aparrnar class and makes a call to getDetailsForAutoPlay method to input the details about the game
 */
public class P2A3_Raghuraman_aparrnar {
	/**
	 * @param args
	 * @throws IOException
	 * Invoke the welcomeUser() method to print a customized welcome message to the user
	 * create an object of P2A3_Raghuraman_AUTOPLAY_aparrnar and make a call to getDetailsForAutoPlay()
	 */
	public static void main(String args[]) throws IOException
	{
		P2A2_Raghuraman_aparrnar obj = new P2A2_Raghuraman_aparrnar();
		Scanner input = new Scanner(System.in);
		boolean doAgain = true, userChoiceNotOK = false;
		System.out.println("Hi User! Welcome to ChipARoonie");
        System.out.println("Enter your name");
        String username = input.next();
        obj.welcomeUser(username);
        do {
        	System.out.println("Press 1 for manual mode and 2 for auto play mode and 0 for exit");
            int userChoice = input.nextInt();
            switch (userChoice) {
                case 0:
                    System.exit(0);
                    break;
                case 1:
                    System.out.println("----------------------------------");
                    System.out.println("You have chosen Manual Mode");
                    System.out.println("----------------------------------");
                    P2A3_Raghuraman_GAME_aparrnar gameObject = new P2A3_Raghuraman_GAME_aparrnar();
                    System.out.println("Enter number of players");
                    int numPlayers = input.nextInt();
                    doAgain = true;
                    gameObject.loadWordAndHint();
                    gameObject.startGame(numPlayers); 
                    break;
                case 2:
                    System.out.println("----------------------------------");
                    System.out.println("You have chosen Auto Player Mode");
                    System.out.println("----------------------------------");
                    P2A3_Raghuraman_AUTOPLAY_aparrnar autoPlayObject = new P2A3_Raghuraman_AUTOPLAY_aparrnar();
            		autoPlayObject.getDetailsForAutoPlay();
                    break;
                default:
                    userChoiceNotOK = true;
                    System.out.println("Invalid choice! Enter again");
            }
        } while (userChoiceNotOK == true ||doAgain == true);
	}
}
