package joc.actors;



import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import joc.Colisio;
import joc.Constants;
import joc.Context;
import joc.Hostal;
import joc.Main;
import joc.Porta;
import joc.Util;
import joc.actors.Lletres;


/**
 * Representa el nostre heroi. L'heroi manté una llista amb les lletres trobades
 * 
 * @author danieldelrio@ub.edu
 */
public class Heroi extends AbstractActor {

	private int darrerX;
	private int darrerY;

	private Image[] imatges;

	public static final int AMPLADA = 31; 
	public static final int ALCADA = 31;

	private boolean pas;
	private int deltaX;
	private int deltaY;
	private int direccio;

	private float invisibilitat; //la invisibilitat del heroi sera de tipus float
	
	private static final float DANY_PER_SEGON = 1.0f;

	private static final float INVISIBILITAT_PER_SEGON = 2.0f; //Variable que em treu la invisibilitat

	protected String paraula= ""; //Declaro l'String a buscar

	private boolean mavisTrobada = false; //Boolea que hem dira si he trobat la Mavis

	private boolean totesLletresTrobades = false; //boolea que m'indica si he trobat totes les lletres

	/**
	 * Constructor.
	 */
	public Heroi() {
		init();
		
		pas = false;
		deltaX = 10;
		deltaY = 8;
		direccio = -1;
	}
        
	public void inicialitzar() {
		super.inicialitzar();
	}

    
	        
	/**
	 * Obté un rectangle ambs el límits de l'heroi.
	 * 
	 * @return els límits, x,y, amplada i alçada
	 */
	public Rectangle getLimits() {
		return new Rectangle(x, y, AMPLADA, ALCADA);
	}

	public void actualitzar(Context ctx) {
		darrerX = x;
		darrerY = y;

		calcularNivellVida(ctx);
		calcularNivellInvisibilitat(ctx);
		comprobarLletres();

		
		int desX = 0; 
		int desY = 0;

		if (ctx.isKeyPressed(Context.KEY_DOWN)) {
			desY = 1;
			direccio = Constants.DIRECCIO_SUD;            
		} else if (ctx.isKeyPressed(Context.KEY_UP)) { 
			desY = -1;
			direccio = Constants.DIRECCIO_NORD;
		} else if (ctx.isKeyPressed(Context.KEY_LEFT)) {
			desX = -1;
			direccio = Constants.DIRECCIO_OEST;
		} else if (ctx.isKeyPressed(Context.KEY_RIGHT)) {
			desX = 1;
			direccio = Constants.DIRECCIO_EST;
		}

		int auxX = x + (int)(deltaX * desX);
		int auxY = y + (int)(deltaY * desY);        

		Porta porta = testPorta(ctx, auxX, auxY);
		if (porta != null && porta.getNumPlantaDesti() != -1 && 
				porta.getNumHabitacioDesti() != -1) {

			Hostal hostal = ctx.getJoc().getHostal();    
			hostal.setPlanta(porta.getNumPlantaDesti());
			hostal.setNumHabitacio(porta.getNumHabitacioDesti());
			int[] posicio = porta.getPosicioHabitacioDesti();
			if (posicio != null) {
				x = posicio[0];
				y = posicio[1];
			}
		} else if (!testMur(ctx, auxX, auxY)) {
			x = auxX;
			y = auxY;
		}


	}

	public void tractarColisio(Colisio colisio) {
	}

	public void render(Graphics2D g) {
		Image image = imatges[0];
		if (direccio == Constants.DIRECCIO_NORD) {
			if (pas) {
				image = imatges[0];
			} else {
				image = imatges[1];
			}
			// invertim el pas
			if (darrerX != x) {
				pas = !pas;
			}
		} else if (direccio == Constants.DIRECCIO_SUD) {
			if (pas) {
				image = imatges[2];
			} else {
				image = imatges[3];
			}            
			// invertim el pas
			if (darrerX != x) {
				pas = !pas;
			}

		} else if (direccio == Constants.DIRECCIO_EST) {
			if (pas) {
				image = imatges[4];
			} else {
				image = imatges[5];
			}            
			// invertim el pas
			if (darrerX != x) {
				pas = !pas;
			}

		} else if (direccio == Constants.DIRECCIO_OEST) {
			if (pas) {
				image = imatges[6];
			} else {
				image = imatges[7];
			}            
			// invertim el pas
			if (darrerX != x) {
				pas = !pas;
			}
		}
		g.drawImage(image, x, y, observer);
	}

	public float getInvisibilitat() { //Metode que hem retorna la invisibilitat que tinc
		return invisibilitat;

	}


	public void setInvisibilitat(float novaInvisibilitat) { //Metode que m'estableix que la invisibilitat va de 0 a 100
		this.invisibilitat = Math.min(100, novaInvisibilitat);
	}
	


	private void calcularNivellInvisibilitat(Context ctx) { //Metode que hem calcula la invisibilitat que guanyo o perdo
		long t = ctx.getTempsFrame();
		float invisibilitatPerduda = INVISIBILITAT_PER_SEGON * t / 100.0f;
		setInvisibilitat(Math.max(0, getInvisibilitat() - invisibilitatPerduda));
	}

	
	public void addLletres(char e) { //Metode que afegeix les lletres
		 boolean lletraTrobada = false;
		 for (int i = 0; i < paraula.length(); i++) {
		 	if (paraula.charAt(i) == e) {
		 		lletraTrobada = true;
		 	} 
		 }
		if(!lletraTrobada) {
			this.paraula= paraula + e;
		}
	}



	public String getLletres() { //Metode que hem retorna la paraula, que es un boolea, i saber quantes tinc
		return paraula;
	}

	public void comprobarLletres(){ //Metode que m'indica si he agafat totes les lletres de la paraula "CLAU"
     int count = 0;
     System.out.println("De moment tens formada la paraula "+paraula);
     for (int i=0;i<paraula.length();i++){ 

      		for (int j=0; j < Constants.PARAULA_MAGICA.length(); j++){

       			if (paraula.charAt(i) == Constants.PARAULA_MAGICA.charAt(j)){
         			count++;
       			}
      		}
     	}

     	if (count == (Constants.PARAULA_MAGICA.length())){
      		this.totesLletresTrobades = true; //canvia el boolea que m'indica si he trobat totes les paraules a true
     	}
    
    }







    /*
	public void comprobarLletres() {
		int count = 0;
		for (int i = 0; i < paraula.length(); i++) {
			for (int j=0; j< Constants.PARAULA_MAGICA.length(); j++) {
				if (paraula.charAt(i) == Constants.PARAULA_MAGICA.charAt(j)) {
					count++;
				}
			}
		}
		if (count == (Constants.PARAULA_MAGICA.length())) { 
			this.totesLletresTrobades= true; 
		}
		System.out.println(totesLletresTrobades);
		System.out.println(paraula);
		System.out.println(count);
	}
	*/	

	public void setMavisTrobada() {
    	this.mavisTrobada = true;

    }
    public boolean getMavisTrobada(){
    	return mavisTrobada;	
    }

    public boolean getLletresTrobades() { 
    	return totesLletresTrobades; 
    }




	

	// private methods *********************************************************

	private void init() {
		imatges = new Image[8];
		
		// Imatges per caminar cap al NORD
		imatges[0] = Util.carregarImatge("heroi.png", 0, 105, AMPLADA, ALCADA);
		imatges[1] = Util.carregarImatge("heroi.png", (0 + AMPLADA), 105, AMPLADA, ALCADA);
		
		// Imatges per caminar cap al SUD		
		imatges[2] = Util.carregarImatge("heroi.png", 0, 0, AMPLADA, ALCADA);
		imatges[3] = Util.carregarImatge("heroi.png", (0 + AMPLADA), 0, AMPLADA, ALCADA);
		
		// Imatges per caminar cap al EST		
		imatges[4] = Util.carregarImatge("heroi.png", 0, 70, AMPLADA, ALCADA);
		imatges[5] = Util.carregarImatge("heroi.png", (0 + AMPLADA), 70, AMPLADA, ALCADA);
		
		// Imatges per caminar cap al OEST		
		imatges[6] = Util.carregarImatge("heroi.png", 0, 35, AMPLADA, ALCADA);
		imatges[7] = Util.carregarImatge("heroi.png", (0 + AMPLADA), 35, AMPLADA, ALCADA);
		
		
	}

	private void calcularNivellVida(Context ctx) {
		long t = ctx.getTempsFrame();
		float dany = DANY_PER_SEGON * t / 1000.f;
		setVida(Math.max(0, getVida() - dany));
	}

  

}
