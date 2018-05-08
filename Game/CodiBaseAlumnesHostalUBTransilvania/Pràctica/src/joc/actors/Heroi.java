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
	
	private static final float DANY_PER_SEGON = 1.0f;

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

    public String getLletres() {
    	//Deu ser modificat pel alumne!!!!!
        return "CLAU";
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
