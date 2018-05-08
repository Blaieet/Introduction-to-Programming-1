import java.util.*;

public class UsersClass {
	public static final int MAX_USERS = 4;
	public static void main(String [] args){
		Scanner sc = new Scanner(System.in);
		TaulaUsuaris t = new TaulaUsuaris(MAX_USERS);
		Usuari a;

		for (int i = 0; i < MAX_USERS; i++) {
			a = new Usuari();
			a.obtenirUsuari(sc);
			t.afegirUsuari(a);
		}
		System.out.println("La mitjana d'edats es "+t.mitjanaedat());
	}
}