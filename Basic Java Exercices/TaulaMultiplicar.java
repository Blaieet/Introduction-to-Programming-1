import java.util.Scanner;
public class TaulaMultiplicar
{
	public static void main( String[ ] args )
	{
	  System.out.println("Dona'm un número entre 0 i 9: ");
	  Scanner scan = new Scanner(System.in);
	  int numero = scan.nextInt();

	  /*
	    si
	      numero > 9 -> "Mal intro"
	      numero <= 9 -> "Print tabla"
	    fsi
	 */
	  
	  
	  if (numero > 9)
	    System.out.println("Eres gilipollas?? He dicho un numero del 0 al 9 i tu has puesto "+numero);
	  else
	    for (int numeros = 0 ; numeros <= 10; numeros++)
	      System.out.println(numeros * numero);
	    
	  
	}
}