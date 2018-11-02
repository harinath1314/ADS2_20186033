/**
 * WORDNET CODECAMP.
 * author harinath reddy
 * date 2-11-18.
 */
import java.util.Scanner;
/**
 * Class for solution.
 */
public class Solution {
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

		// if(task.equals("Graph")){
		// 	System.out.println(wn.di);

		// }
		switch (task) {
		case"Graph":
			try {
				WordNet wn = new WordNet(synset, hypernym);
            System.out.println(wn.di);



			}
			catch(Exception e) {
				System.out.println(e.getMessage());
			}
			break;
		case"Queries":
			try{

				WordNet wn = new WordNet(synset, hypernym);
				while(sc.hasNextLine()){
					String[] qyery = sc.nextLine().split(" ");
					if(qyery[0] == null || qyery[1] == null){
						throw new Exception("IllegalArgumentException");
					}
				}

			}catch(Exception e){
				System.out.println(e.getMessage());
			}

		}




	}
}
