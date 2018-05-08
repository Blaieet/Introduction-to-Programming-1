import java.util.*;
public class TaulaQuadrat {
    public static void main(String [] args) {
        System.out.println("Dona'm un numero mes gran que 1");
        Scanner sc = new Scanner(System.in);
        int numero;
        numero = sc.nextInt();
        if (numero < 1) {
        	System.out.println("He dit mes gran que 1");
        } else {
        	for (int i = 1; i <= numero; i++) {
        		System.out.println(i);
        		System.out.println(i*i);
        	}
        }
    }
}
