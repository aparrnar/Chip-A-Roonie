import java.io.*;
import java.util.*;

/**
 * @author aparrnaa
 * Creation Date: 07/14/2017
 * Last Modified Date: 07/15/2017
 * Description:
 * THe class describes a question containing a word and hint for the word
 */
public class P2A2_Raghuraman_QUESTION_aparrnar {
		public static ArrayList <String> words = new ArrayList <String>();
		public static ArrayList <String> hints = new ArrayList <String>();
		public static String filename;
		private static int wordIndex;
		/**
		 * Default constructor
		 */
		public P2A2_Raghuraman_QUESTION_aparrnar()
		{
			filename = null;
			words = null;
			hints = null;		
			wordIndex = -1;
		}
		
		/**
		 * @param file - filename containing the words and hints
		 */
		public P2A2_Raghuraman_QUESTION_aparrnar(String file)
		{
			filename = file;
			wordIndex = -1;
		}
		
		/**
		 * @throws IOException
		 * Reads the content of file and initializes the words and hints
		 */
		public static void getFileContent() throws IOException
		{
			BufferedReader br = new BufferedReader(new FileReader(filename));
		    String line = null;
		    while ((line = br.readLine()) != null) {
		       // process the line.
		    String[] lineSplits = line.split(",");
		    words.add(lineSplits[0]);
		    hints.add(lineSplits[1]);
		    }
		    br.close();
		}
		
		/**
		 * @return String - return a random word from the list of words
		 */
		public static String getWord()
		{
			Random rand = new Random();
			int index = rand.nextInt((words.size() - 0)) + 0; 
			wordIndex = index;
			return words.get(index);
		}
		
		/**
		 * @return hint corresponding to the word generated
		 */
		public static String getHint()
		{
			return hints.get(wordIndex);
		}
		
			
}
