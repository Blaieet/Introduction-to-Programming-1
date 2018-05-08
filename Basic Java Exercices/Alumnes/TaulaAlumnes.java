class TaulaAlumnes {
  public Alumne [] taula;
  public int nAlumnes;
  
  public TaulaAlumnes(int n) {
    taula = new Alumne[n];
    nAlumnes = 0;
    
    }
  public void afegirAlumne(Alumne a) {
	if(nAlumnes < taula.length) {
	    taula[nAlumnes] = a;
	    nAlumnes++;
	} else {
	    System.out.println("Has ocupat maxm numero de alumnes");
	}
}
  public float mitjanaNotes() {
    int i=0; float suma=0.0f;
    while (i < nAlumnes) {
      suma = suma + taula[i].nota;
      i++;
    }
    return suma/(float)nAlumnes;
   }
}