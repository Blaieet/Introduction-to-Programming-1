import java.util.*;
public class Augmenta {
    public static void main (String [] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Dona'm un nobre n més gran que 0");
        int n, mesnumeros;
        n = sc.nextInt();
        System.out.println("Dona'm mes enters i et dire si son mes grans que el numero anterior!");
        for (int i = 0; i < n; i++) {
            mesnumeros = sc.nextInt();
        /* si
         *
         *      mesnumeros > n -> añadir a lista
         *
         *      sino -> continuar
         *          
         *
         *
         *
         * fsi
         * */
            if (mesnumeros >= n) {
                System.out.println(mesnumeros);    
            }
        }
    }
}