/* Autor: Blai Ras
 * Data: 20/10/2015
 * Programa: Kiriki

import java.util.Scanner;
import java.util.Random;
public class Kiriki {
	public static void main( String[ ] args ) {
		int numero1, numero2, numero3, numero4,puntuacio1,puntuacio2,puntuaciofinal;
		puntuacio1=0;
		puntuacio2=0;
		Random aleatori = new Random();
		numero1 = aleatori.nextInt(6)+1;
		numero2 = aleatori.nextInt(6)+1;
		System.out.println("El primer jugador ha tret un "+numero1+" i un " +numero2);
		numero3 = aleatori.nextInt(6)+1;
		numero4 = aleatori.nextInt(6)+1; 
		System.out.println("El segon jugador ha tret un "+numero3+" i un " +numero4);
		/*
		 *si
		 *	numero 1 diferente que numero2
		 *		si es un Kiriki -> puntuación1=50
		 *		else
		 *			puntuacio1=suma de numero1+numero2
		 *	else
		 *		puntuacion1=suma de numero1+numero2 mas 20 puntos
		 *	si numero3 diferente del numero 4
		 *		si es un kiriki -> puntuacion2=50
		 *		else
		 *			puntuacion2=suma del numero3+numero4
		 *	else
		 *		puntuacion=suma del numero3+numero4 y 20 puntos
		 *
		 *	si puntuacion1 es diferente puntuacion2
		 *		si puntuacion1>puntuacion2 es mas grande i es un kiriki -> imprime KIRIKI ganador 1
		 *		si no es un kirki i puntuacion1>puntuacion 2 -> imprime jugador1
		 *		si puntuacion1<puntuacion2 i es un Kiriki -> imprime ganador 2 por Kiriki
		 *		si puntuacion<puntuacion2 i no es un kiriki -> imprime ganador2
		 *	else
		 *		Han empatado
		 */
		
		if (numero1 != numero2) {
			if ((numero1 == 1 && numero2 == 2) || (numero1==2 && numero2==1)) {
				puntuacio1=50;
			} else {
				puntuacio1=numero1+numero2;
			}
		} else {
			puntuacio1= numero1+numero2+20;
		}
		if (numero3 != numero4) {
			if ((numero3 == 1 && numero4 ==2) || (numero3==2 && numero4==1)) {
				puntuacio2=50;
			} else {
				puntuacio2=numero3 + numero4;
			}
		} else {
			puntuacio2 = numero3 + numero4 +20;
		}
		System.out.println("Punts del primer jugador: "+puntuacio1);
		System.out.println("Punts del segon jugador: "+puntuacio2);
		if (puntuacio1 != puntuacio2) {
			if (((numero1 == 1 && numero2 == 2) || (numero1==2 && numero2==1)) && (puntuacio1 > puntuacio2)) {
				System.out.println("Guanya el primer jugador perquè ha tret un KIRIKI");
			}
			if ((puntuacio1 !=50) && (puntuacio1 > puntuacio2)) {
				System.out.println("Guanya el primer jugador");
			}
			if (((numero3 == 1 && numero4 == 2) || (numero3 ==2 && numero4 ==1)) && (puntuacio1 < puntuacio2)) {
				System.out.println("Guanya el segon jugador perquè ha tret un KIRIKI");
			}
			if ((puntuacio2 !=50) && (puntuacio1 < puntuacio2)) {
				System.out.println("Guanya el segon jugador");
			}
		} else {
			System.out.println("Heu empatat!");
		}
	}
}
		

