import java.util.*;

public class MenuTaxi {
	public static int menuOpcions(Scanner sc) {
		System.out.println("Afegeix un taxi");

		System.out.println("Sortir");
		System.out.println("Escull una de les opcions:");
		return sc.nextInt();
	}

	public static void main(String [] args) {
		Taxi    t;
		TaulaTaxi  taulaT;
		int opcio;
		Scanner sc;
		sc = new Scanner(System.in);

		taulaT = new TaulaTaxi(3);
		opcio = menuOpcions(sc);
		while (opcio != 2) {
			switch(opcio) {
				case 1:
				t = new Taxi();
				t.obteTaxi(sc);
				taulaT.afegirTaxi(t);
				break;
			
			}
			opcio = menuOpcions(sc);
		}
	System.out.println("Programa acabat correctament!");
    }
}
