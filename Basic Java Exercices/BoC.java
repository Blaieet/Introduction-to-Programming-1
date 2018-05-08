import java.util.*;
public class BoC {
   public static void main(String [] args) {
      /*Scanner text = new Scanner (System.in); */
      String frase = ("jajajaj b i c");
      int countb, countc;
      countb = 0;
      countc = 0;
      for (int i = 0; i < frase.length(); i++) {
         if (frase.charAt(i) == 'b') {
            countb = countb + 1;
         }
         if (frase.charAt(i) == 'c') {
            countc = countc + 1;
         }
      }
      if (countb != countc) {
         if (countb > countc) {
            System.out.println("Ha sorit mes vegades la lletra b; concretament, "+countb+" vegades");
         }
         if (countb < countc) {
            System.out.println("Ha sorit mes vegades la lletra c; concretament, "+countc+" vegades");
         }
      } else {
         System.out.println("Han sortit tantes b com c; concretament, "+countc+" vegades");
      }
   }
}