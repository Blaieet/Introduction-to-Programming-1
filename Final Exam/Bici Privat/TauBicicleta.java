// Tupla que guardarà un conjunt de bicicletes
public class TauBicicleta {
    private int       numBicicletes;
    private Bicicleta[] taula;
    
    public TauBicicleta(int maxBicicletes) {
	taula = new Bicicleta[maxBicicletes];
	numBicicletes = 0;
    }
    public void afegirBicicleta(Bicicleta p) {
	if (numBicicletes < taula.length) {
	    taula[numBicicletes] = p;
	    numBicicletes = numBicicletes + 1;
	} else {
	    System.out.println ("Estas excedint el nombre màxim de bicicletes");
	}
    }
    public int getNumBicicletes() {
	return numBicicletes;
    }
    public Bicicleta obtBicicletaAt(int i) {
	return taula[i];
    }
    public Bicicleta minimPreuPerPes() {
	int iminim;
	int i;
	double minim;
	double aux;
	i = 0;
	minim = Integer.MAX_VALUE;
	iminim = 0;
	while ( i < numBicicletes ) {
	    aux = taula[i].getPreu() / taula[i].getPes();
	    if ( minim > aux) {
		minim = aux;
		iminim = i;
	    }
	    i = i + 1;
	}
	return (taula[iminim]);
    }
    public Bicicleta inferiorPes(double kg) {
	boolean   trobat;
	int       i;
	Bicicleta b;

	trobat = false;
	i = 0;
	while ( i < numBicicletes && !trobat) {
	    if (taula[i].getPes() < kg) {
		trobat = true;
	    } else {
		i = i + 1;
	    }
	}
	if (trobat) {
	    b = taula[i];
	} else {
	    b = null;
	}
	return(b);
    }
}
