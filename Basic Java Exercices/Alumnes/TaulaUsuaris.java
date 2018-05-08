class TaulaUsuaris {

	public Usuari [] taula;
	public int posicioTaula;

	public TaulaUsuaris(int n) {
		taula = new Usuari[n];
		posicioTaula = 0;


	}

	public void afegirUsuari(Usuari a) {
		if (posicioTaula < taula.length) {
			taula[posicioTaula] = a;
			posicioTaula++;
		} else {
			System.out.println("HO SENTO NO TENIM PROUS SERVERS COM PER AFEGIR TANTS USUARIS");
		}
	}

	public float mitjanaedat() {
		int i = 0; float suma= 0;

		while (i < posicioTaula) {
			suma = suma + taula[i].edat;
			i++;
		}
		return suma/(float)posicioTaula;
	}
}