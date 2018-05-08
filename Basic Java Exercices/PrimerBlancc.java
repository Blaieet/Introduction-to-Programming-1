import java.util.*;
public class PrimerBlancc {
   public static void main(String [] args) {
      System.out.print("Digue'm la teva frase loco: ");
      Scanner sc = new Scanner(System.in);
      int count;
      String paraula = sc.nextLine();
      for (int i = 0; i < paraula.length(); i++) {
         if (paraula.charAt(i) == ' ') {
            System.out.println(i);
         }
      }
   }
}