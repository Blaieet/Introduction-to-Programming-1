import java.util.*;
public class As {
    public static void main(String [] args) {
        System.out.println("Dona'm una jugada de p�ker ");
        Scanner sc = new Scanner(System.in);
        String jugada = sc.nextLine();
        int count;
        count=0;
        for (int i = 0; i < jugada.length(); i++) {
         if (frase.charAt(i) == 'A') {
            count = count + 1;
            }
        }
        if (count == 0) {
            System.out.println("No hi ha cap As loco");
        }
        if (count == 1) {
            System.out.println("Cont� nom�s un As loco");
        }
        if (count > 1) {
            System.out.println("Hi ha m�s d'un As locaso");
        }
    }
}