package joc.actors;


import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;

import joc.Actor;
import joc.Colisio;
import joc.Constants;
import joc.Context;
import joc.Util;
import joc.actors.Heroi;



/**
 * Representa el monstre
 * 
 * @author danieldelrio@ub.edu
 */
public class Monstre extends AbstractActor {

	private int darrerX; //Cordenades que faran que el monstre xoqui contra el mur i agafi una nova direccio
    private int darrerY;
    
	private int direccio;
	private Image[] imatges;

	private static final int DANYINV = 5; //Dany que treu d'invisibilitat si l'heroi te +20 

	//Dimensions del monstre
	public static final int AMPLADA = 32; 
	public static final int ALCADA = 46;

	private static final int INC_X_NOU_PAS = 10;
	private static final int INC_Y_NOU_PAS = 10;
	
	private static final int NUM_PASSOS = 3;

	//Moviments
	private int accDelta;
	private boolean pas;
	private int deltaX;
	private int deltaY;



	/**
	 * Constructor.
	 */

	public Monstre() {

		direccio = (int) (Math.random()*4)+1; //Agafa una direccio random, marcada pels numeros 1,2,3,4, que signifiquen nord, sud, oest, est a la clase Constants
		accDelta = 0;
		pas = false;
		deltaX = 8;
		deltaY = 8;
		init();
	}

	public void inicialitzar() {
		super.inicialitzar();
	}

	/**
	 * Obté un rectangle ambs el límits del vampir.
	 * 
	 * @return els límits, x,y, amplada i alçada
	 */
	public Rectangle getLimits() {
		return new Rectangle(x, y, AMPLADA, ALCADA);
	}

	/**
     * Estableix la direcció del vampir
     */
    public void setDireccio(int direccio) {
        this.direccio = direccio;
    }
    
    /**
     * 
     */



    public void actualitzar(Context ctx) {
        darrerX= x;
        darrerY = y;
        
        int desX = 0;
        int desY = 0;

        //Per cada direccio posible, asignali una direccio a Constants        
    	if (direccio == 2) {
    		desY = 1;
			direccio = Constants.DIRECCIO_SUD;

		} else if (direccio == 1) {
			desY=-1;
			direccio = Constants.DIRECCIO_NORD;

		} else if (direccio == 4) {
			desX=-1;
			direccio = Constants.DIRECCIO_OEST;

		} else if (direccio == 3) {
			desX=1;
			direccio = Constants.DIRECCIO_EST;

		}
		
		//Conversio
		int auxX = x + (int)(deltaX * desX);
		int auxY = y + (int)(deltaY * desY);
	
		//Que pasa si no es troba un mur
		if (!testMur(ctx, auxX, auxY)) {
		
			x = auxX;
			y = auxY;

		} else {

			direccio = (int)(Math.random() * 4) +1; //Random de direccio un altre cop
		}
		
	}

	/**
	 * 
	 */
	public void tractarColisio(Colisio colisio) {

		Actor actor = colisio.getActor();
		Heroi heroi = (Heroi)actor;//Cast d'heroi

		if (heroi.getInvisibilitat() >= 20) { //Si la invisibilitat del heroi es major que 20...
			heroi.setInvisibilitat(heroi.getInvisibilitat() - DANYINV); //Treuli nomes 5 d'invisibilitat
		} else {
			actor.setVida(0); //Si te menys de 20, mata'l directament
		}
	}



	/**
	 * 
	 */
	public void render(Graphics2D g) {

		Image image = imatges[0];

		//Aqui, per cada direccio agafa una imatge corresponents al monstre anant cap a dalt, cap a baix, esquerre o dreta
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

	// private methods *********************************************************
	

	private void init() {
		imatges = new Image[8]; 

		// Imatges pel monstre i les coordenades
		imatges[0] = Util.carregarImatge("undeadsorcerer01.png", 0, 143, AMPLADA, ALCADA);
		imatges[1] = Util.carregarImatge("undeadsorcerer01.png", (0+AMPLADA), 143, AMPLADA, ALCADA);


		imatges[2] = Util.carregarImatge("undeadsorcerer01.png", 0, 0, AMPLADA, ALCADA);
		imatges[3] = Util.carregarImatge("undeadsorcerer01.png", (0+AMPLADA), 0, AMPLADA, ALCADA);

		imatges[4] = Util.carregarImatge("undeadsorcerer01.png", 0, 95, AMPLADA, ALCADA);
		imatges[5] = Util.carregarImatge("undeadsorcerer01.png", (0 + AMPLADA), 95, AMPLADA, ALCADA);
		
		// Imatges per caminar cap al OEST		
		imatges[6] = Util.carregarImatge("undeadsorcerer01.png", 0, 45, AMPLADA, ALCADA);
		imatges[7] = Util.carregarImatge("undeadsorcerer01.png", (0 + AMPLADA), 45, AMPLADA, ALCADA);
		
	}

}