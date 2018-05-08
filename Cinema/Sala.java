import java.util.*;

public class Sala {
	char[][] matriuButaques;

	int numfiles;
	int numButaquesF;

	// Començo a crear el constructor

	public Sala(Scanner sc) {

		System.out.println("Digues el numero de files");

		numfiles = sc.nextInt();

		System.out.println("Digues el numero de butaques");


		numButaquesF = sc.nextInt();

		//Creo la Matriu

		matriuButaques = new char[numfiles][numButaquesF]; //Butaques buides

		//Omplo la Matriu

		for(int i = 0; i<numfiles; i++) {

			for(int j = 0; j<numButaquesF; j++) {

				matriuButaques[i][j] = 'L';
			}
		}
	}
	public void indicarButaca(Scanner sc) {

		int fila;
		int columna;

		System.out.println("A quina fila vols estar?");

		fila = sc.nextInt();

		System.out.println("A quina butaca vols sentarte?");

		columna = sc.nextInt();

		/*
			Si
				fila < numero de files i (columna < numero de butaques) -> ocupa la butaca

				fila >= numero del files o (columna > numero de columnes) -> No pot ser

			fsi
		*/

		if (fila < numfiles && columna < numButaquesF) {
			/*

				si 
					matriuButaques == 'O' -> "La butaca esta ocupada"

					matriuButaques == 'L' -> Reserva la butaca
				fsi
			*/
			if (matriuButaques[fila][columna] == 'O') {

				System.out.println("Ho sento, la butaca ja esta ocupada");
			} else {

				matriuButaques[fila][columna] = 'O';
			}
			System.out.println("Butaca reservada");

			//Faig que em salti de fila:
			System.out.println("	") ;
		}
		else {

			System.out.println("Butaca mal introduida, no existeix");
		}
	}
	//Deixo lliure butaca
	public void indicarLliurarButaca(Scanner sc) {

		int fila;
		int columna;

		System.out.println("A quina fila esta la butaca que vols alliberar?");

		fila = sc.nextInt();

		System.out.println("A quina columna esta la butaca que vols alliberar?");

		columna = sc.nextInt();

		/*

			si
				fila < numero de files i (columna < numero de butaques)-> desallibera la butaca

				fila >= numero del files o (columna > numero de columnes) -> No pot ser

			fsi
		*/

		if (fila < numfiles && columna < numButaquesF) {

			if (matriuButaques[fila][columna] == 'O') {

				matriuButaques[fila][columna] = 'L';
			} else {
				System.out.println("La butaca ja esta lliure!");
			}

			System.out.println("Butaca des-ocupada");

			System.out.println("	");

		} else {
			System.out.println("No existeix la butaca");
		}

	}
	//ALLIBER-HO LA SALA
	public void indicarLliurarTotes() {

	//Simplement creo una nova matriu

		for(int i = 0; i<numfiles; i++) {

			for(int j = 0; j<numButaquesF; j++) {

				matriuButaques[i][j] = 'L';
			}
		}
	}

	//PRINT SALA

	public void indicarMostrarSala() {

		//Creo la matriu a imprimir i despres cridare aquest metode recursivament sobre els elements de la llista

		for (int i=0; i < numfiles; i++) {

			for (int j = 0; j < numButaquesF; j++) {

				System.out.print("	" + Character.toString(matriuButaques[i][j]));
			}

			System.out.println("	");
		}
	}
}
