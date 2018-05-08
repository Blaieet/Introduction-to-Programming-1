import java.util.Scanner;
public class MenuBicicletes2 {
    public static int menuOpcions(Scanner sc) {
        System.out.println("\n(1) Inserir/afegir una bicicleta");
        System.out.println("(2) Bicicleta de cost preu per kg minim");
        System.out.println("(3) Cerca de la bicicleta inferior a un cert pes");
        System.out.println("(4) Sortir");
        System.out.println("Escull una de les opcions:");
        return sc.nextInt();
    }

    public static void main(String [] args) {
        Bicicleta     bici;
        TauBicicleta  tauBicis;
        int opcio;
        Scanner sc;
        sc = new Scanner(System.in);

        tauBicis = new TauBicicleta(25);
        opcio = menuOpcions(sc);
        while (opcio!=4) {
            // tractar element
            switch (opcio) {
                case 1: // Afegir bicicleta
                    bici = new Bicicleta();
                    bici.obteBicicleta(sc);
                    tauBicis.afegirBicicleta(bici);
                    break;
                case 2: // minim_preu_per_pes
                    bici = tauBicis.minimPreuPerPes();
                    System.out.println("Bicicleta de minim preu per kg "
                                       + bici);
                    break;

                case 3: // N'hi ha alguna de pes inferior a 1kg?
                    System.out.println("Llindar de pes?");
                    bici = tauBicis.inferiorPes(sc.nextInt());
                    if ( bici != null ) {
                        System.out.println("Bicicleta trobada\n " + bici);
                    } else {
                        System.out.println("Bicicleta no trobada ");
                    }
                    break;
                default:
                    System.out.println("opcio erronia");
                    break;
            }

            // obtencio seguent element
            opcio = menuOpcions(sc);
        }
        System.out.println("Programa acabat correctament!");
    } 
}
