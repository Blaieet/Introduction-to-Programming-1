import java.util.*;
public class TaulaQuadrat {
    public static void main(String [] args) {
        System.out.println("Dona'm un numero mes gran que 1");
        Scanner sc = new Scanner(System.in);
        Int numero = sc.nextLine();
        if (numero < 1) {
        	System.out.println("He dit mes gran que 1");
        } else {
        	for (int i; i < numero; i++); {
        		System.out.print(i + numero*i);
        	}
        }
    }
}
