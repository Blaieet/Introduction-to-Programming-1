import java.util.*;
public class Anagrama {
	public static void main (String [] args) {
		Scanner sc = new Scanner(System.in);
		boolean esanagrama;
		String paraula1 = sc.nextLine();
		String paraula2 = sc.nextLine();
		for (int i = 0; i < paraula1.length(); i++) {
			if (paraula2.indexOf(paraula1.charAt(i)) < 0)
				System.out.println(false);
				System.out.println(paraula2.indexOf(paraula1.charAt(i)));
		}
		System.out.println(true);
		
	}
}