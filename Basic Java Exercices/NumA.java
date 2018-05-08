import java.util.*;
public class NumA {
   public static void main(String [] args) {
      Scanner text = new Scanner (System.in);
      String word = ("Holaa Loco Puta vida aqui treballant");
      int count = 0;
      for (int i = 0; i < word.length(); i++) {
         if (word.charAt(i) == 'a')
         {
            count++;
         }
      }
      System.out.println(count);
   }
}

