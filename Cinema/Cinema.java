import java.util.*;
public class Cinema {

	int countSales;

	Sala[] sales;

	//Faig el constructor

	public Cinema(int numCines) {

		sales = new Sala[numCines];

		countSales = 0;
	}

	public void afegirSala(Sala temp) {

		sales[countSales] = temp;
		countSales++;
	}

	public void ocuparButaca(Scanner sc) {

		int numSala;

		System.out.println("A quina sala vols ocupar la butaca?");

		numSala = sc.nextInt();

		/*

			si
				(numSala <= countSales) AND numSala >= 0 -> ocupar butaca de la sala(indicarButaca)

				(numSala > countSales) AND numSala < 0 -> No pot ser
			fsi
		*/
		if (numSala >= 0 && numSala <= countSales) {

			sales[numSala].indicarButaca(sc);

		} else {
			
			System.out.println("No existeix la sala!");
		}

	}

	public void lliurarButaca (Scanner sc) {
		int numSala;
		System.out.println("De quina sala vols des-ocupar la butaca?");
		numSala = sc.nextInt();

		/*

			si
				numSala <= countSala AND numSala >= 0 -> indicarButaca de sala en questió
				numSala > countSala AND numSala < 0 -> No pot ser
			fsi
		*/

		if (numSala < countSales && numSala >= 0) {
			
			sales[numSala].indicarLliurarButaca(sc);

		} else {
			System.out.println("No existeix la sala!");
		}
	}

	public void lliurarTotes() {

		for(int i = 0; i < countSales; i++) {
			sales[i].indicarLliurarTotes();
		}

		System.out.println("Sala buidada!");

	}

	public void mostrarSala() {
		for(int i = 0; i < countSales; i++) {

			System.out.println("\n Imprimint sala ");

			System.out.println(i);

			System.out.println(":\n");
			sales[i].indicarMostrarSala();
		}
	}
}