import java.util.Scanner;
public class Menu {
	public static final int MAX = 4;
	public static void main(String [] args){
		
		Scanner sc;
		sc = new Scanner(System.in);

		TaulaEstudiants t = new TaulaEstudiants(MAX);
		Estudiant a = new Estudiant();
		Estudiant b = new Estudiant();


		a.obteEstudiant(sc);
		t.afegirEstudiant(a);

		b.obteEstudiant(sc);
		t.afegirEstudiant(b);
		System.out.println(a);
		System.out.println(b);
	}
}