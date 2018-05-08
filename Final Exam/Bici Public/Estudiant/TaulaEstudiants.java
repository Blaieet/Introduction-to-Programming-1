public class TaulaEstudiants {
	Estudiant [] taula;
	int numEstudiants;

	public TaulaEstudiants(int max) {
		taula = new Estudiant[max];
		numEstudiants = 0;
	}

	public void afegirEstudiant(Estudiant a) {
		if(numEstudiants < taula.length) {
			taula[numEstudiants] = a;
			numEstudiants++;

		}else {
			System.out.println("Has afegit masses estudiants lokaso");

		}

	}
}