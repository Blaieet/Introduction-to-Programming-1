/*

Feu un programa que llegeixi una frase i imprimeixi la quantitat de caràcters que té abans del
primer espai en blanc. Nota: La lectura s’ha de fer amb el mètode nextLine()

*/
import java.util.Scanner;
public class PrimerBlanc{
  public static void main(String[]args){
    int contador = 0;
    int numerocaracters;
    String frase;
    
    Scanner sc;
    sc = new Scanner(System.in);
    frase = new String(sc.nextLine());


    
    while (frase.charAt(contador) != ' '){
    contador++;
    }
    numerocaracters = contador;
    System.out.println(numerocaracters);
    
    
  }
}