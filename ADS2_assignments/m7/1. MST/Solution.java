/**
 * harinatha 
 */

import java.util.Scanner;
public final class Solution{
	Solution(){

	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String abb = sc.nextLine();
		int a = Integer.parseInt(abb);
		String baa = sc.nextLine();

		int b = Integer.parseInt(baa);
		EdgeWeightedGraph ewg = new EdgeWeightedGraph(a, b, sc);
		KruskalMST kmst = new KruskalMST(ewg);
		System.out.printf("%.5f", kmst.weight());

	}
}