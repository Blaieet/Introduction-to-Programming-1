import java.util.Scanner;
public class GestioCinema {
	public static void main(String [] args) {
		
		Scanner sc = new Scanner(System.in);
		Cinema 		numCinema;
		numCinema = new Cinema(10);
		menuOpcions(sc, numCinema);
	}

	public static void menuOpcions(Scanner sc, Cinema numCinema) {

		Sala temp;

		int opcio = 0, fila, columna;

		while (opcio != 6) {
			
			System.out.println("1. Afegeix sala al cine");
			System.out.println("2. Ocupar butaca d'una sala");
			System.out.println("3. Lliurar butaca d'una sala");
			System.out.println("4. Lliurar totes les butaques del cine");
			System.out.println("5. Mostrar totes les sales del cine");
			System.out.println("6. Sortir");

			System.out.println("	");

			opcio = sc.nextInt();

			if (opcio < 1) {
				System.out.println("Introdueix un número vàlid");
				opcio = sc.nextInt();
			}

			switch (opcio) {
				case 1:
               		temp = new Sala(sc);
                	numCinema.afegirSala(temp);
                	System.out.println("Sala afegida amb exit");
                	break;
      			case 2:
                    numCinema.ocuparButaca(sc);
                    System.out.println("Butaca reservada amb exit");
                    break;
                case 3:
                    cines.lliurarButaca(sc);
                    System.out.println("Butaca des-ocupada amb exit");
                    break;
           		case 4:
                	numCinema.lliurarTotes();
                	break;
            	case 5:
            		numCinema.mostrarSala();
                	break;
            }
        }
    }
}
