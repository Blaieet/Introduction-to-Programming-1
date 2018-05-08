import java.util.*;

class Usuari {
	public String nom;
	public int edat;

	public void obtenirUsuari(Scanner sc) {
		System.out.println("Nom d'usuari loco?");
		nom = sc.next();
		System.out.println("Edat lokasO?");
		edat = sc.nextInt();
	}
}