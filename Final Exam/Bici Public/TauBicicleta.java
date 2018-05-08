// Tupla que guardara un conjunt de bicicletes
public class TauBicicleta {
    public int         numBicicletes;
    public Bicicleta[] taula;
    
    public TauBicicleta(int maxBicicletes) {
        taula = new Bicicleta[maxBicicletes];
        numBicicletes = 0;
    }
    public void afegirBicicleta(Bicicleta p) {
        if (numBicicletes < taula.length) {
            taula[numBicicletes] = p;
            numBicicletes = numBicicletes + 1;
        } else {
            System.out.println ("Estas excedint el nombre maxim de bicicletes");
        }
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
            aux = taula[i].preu / taula[i].pes;
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
            if (taula[i].pes < kg) {
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
