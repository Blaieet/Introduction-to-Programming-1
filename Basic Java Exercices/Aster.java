import java.util.*;
public class Aster {
    public static void main (String [] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Dona'm un numero enter");
        int n;
        n = sc.nextInt();
        if (n <= 0) {
            System.out.println("He dit mes gran que 0");
        }
        for (int i = 0; i => n;i++) {
                System.out.println("*");
        }
    }
}