import java.util.*;
class Taxi {

	private String conductor;
	private int matricula;
	private float kilometres;

	public void setConductor(String c) {
		conductor = c;
	}

	public void setMatricula(int m) {
		matricula = m;
	}

	public void setKilometres(float km) {
		kilometres = km;
	}

	public String getConductor() {
		return conductor;
	}

	public int getMatricula() {
		return matricula;
	}

	public float getKilometres() {
		return kilometres;
	}

	public void obteTaxi(Scanner sc) {
		System.out.println("Nom del taxista?");
		conductor = sc.next();
		System.out.println("Quina matricula té?");
		matricula = sc.nextInt();
		System.out.println("Quans kilometres has fet?");
		kilometres = sc.nextFloat();
	}


}