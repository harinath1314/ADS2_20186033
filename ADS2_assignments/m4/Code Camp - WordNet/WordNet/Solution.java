/**
 * WORDNET CODECAMP.
 * author harinath reddy 
 * date 2-11-18.
 */
import java.util.Scanner;
/**
 * Class for solution.
 */
public class Solution{
	/**
	 * main method starts here.
	 *
	 * @param      args  The arguments
	 */
	public static void main(final String[] args) {
		Scanner sc = new Scanner(System.in);
		String synset = sc.nextLine();
		String hypernym = sc.nextLine();
		String task = sc.nextLine();
		if(task.equals("Graph")){
		WordNet wn = new WordNet(synset, hypernym);
			
		}



		
		
	}
}
