import java.util.Scanner;
public class Amstrong {
	public static void main (String [] args) {
	int n, q, d, suma;
	boolean trobat;
	Scanner sc;
	sc = new Scanner(System.in);
	trobat = false;
	suma = 0;
	n = sc.nextInt ();
	q = n;
	while (q!=0 && !trobat) {
		d = q % 10; // digit a tractar
		suma = suma + d*d*d;
		if (suma > n) {
		// ja no pot ser un nombre Amstrong
		trobat = true;
		} else {
		q = q / 10;
		}
		}
		if (! trobat && suma == n) {
		System.out.println(n + " es un nombre Amstrong");
		} else {
		System.out.println(n + " no es un nombre Amstrong");
		}
	}
	
}