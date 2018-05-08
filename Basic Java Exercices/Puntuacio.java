import java.util.*;
public class Puntuacio {
  public static void main (String [] args) {
  System.out.println("Dona'm una jugada de Póker ");
  Scanner sc = new Scanner(System.in);
  String jugada;
  jugada = sc.nextLine();
  int countA = 0, countR = 0, countD=0, countJ=0;
  for (int i = 0; i < jugada.length(); i++) {
    if (jugada.charAt(i) == 'A') {
      countA++;
      }
    if (jugada.charAt(i) == 'R') {
      countR++;
      }
    if (jugada.charAt(i) == 'D') {
      countD++;
      }
    if (jugada.charAt(i) == 'J') {
      countJ++;
      }
    }
    int puntuaciofinal = ((countA*20)+(countR*15)+(countD*10)+(countJ*5));
    System.out.println("La jugada té una puntació de "+puntuaciofinal+" punts");
    }
}
      