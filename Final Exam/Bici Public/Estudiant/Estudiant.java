import java.util.Scanner;
public class Estudiant {
	private int matricula;
	String nom;
	String ciutat;

	public void obteEstudiant(Scanner sc) {
		System.out.println("QUina es la teva matricula");
		matricula = sc.nextInt();
		System.out.println("Quin es el teu nom");
		nom = sc.next();
		System.out.println("Quina es la teva ciutat");
		ciutat = sc.next();
		
	}

	public String toString() {
		return matricula+" "+nom+" "+ciutat;
	}
}