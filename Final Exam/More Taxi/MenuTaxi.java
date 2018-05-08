import java.util.*;

public class MenuTaxi {
	public static int menuOpcions(Scanner sc) {
		System.out.println("Escull una de les opcions:");
		System.out.println("1.Afegeix un taxi");
		System.out.println("2.Sortir");
		return sc.nextInt();
	}

	public static void main(String [] args) {
		Taxi    t;
		TauTaxi  taulaT;
		int opcio;
		Scanner sc;
		sc = new Scanner(System.in);

		taulaT = new TauTaxi(3);
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
