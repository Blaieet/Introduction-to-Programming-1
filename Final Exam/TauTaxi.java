import java.util.*;


class TauTaxi {

	private int numTaxi;
	private Taxis [] taula;

	public TaulaTaxi(int maxTaxis) {
		taula = new Taxi[numTaxi];
		numTaxi = 0;
	}

	public void afegirTaxi(Taxi c) {
		if (taula.length > numTaxi) {
			taula[numTaxi] = c;
			numTaxi++;
		} else {
			System.out.println("Ho sento, no existeixes");
		}
	}
}