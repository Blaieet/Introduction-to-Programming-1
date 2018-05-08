import java.util.*;
public class SumaQuadrats {
	public static void main (String [ ] args) {
		int n,m;
		System.out.println("Dona'm dos numeros");
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		m = sc.nextInt();

		for (i = n; i < m; i++) {
			System.out.println(i*i);
		}
	}
}