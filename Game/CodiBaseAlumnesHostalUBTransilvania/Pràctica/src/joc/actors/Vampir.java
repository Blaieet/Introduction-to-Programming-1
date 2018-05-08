package joc.actors;


import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;

import joc.Actor;
import joc.Colisio;
import joc.Constants;
import joc.Context;
import joc.Util;


/**
 * Representa el vampir.
 * 
 * @author danieldelrio@ub.edu
 */
public class Vampir extends AbstractActor {

	private int darrerX;
        private int darrerY;
    
	private int direccio;
	private Image[] imatges;

	private static final int DANY = 2;

	public static final int AMPLADA = 40;
	public static final int ALCADA = 40;

	private static final int INC_X_NOU_PAS = 12;
	private static final int INC_Y_NOU_PAS = 12;
	
	private static final int NUM_PASSOS = 4;

	private int accDelta;
	private int pas;
	private int deltaX;
	private int deltaY;


	private static final float DANY_PER_SEGON = 1.0f;

	/**
	 * Constructor.
	 */
	public Vampir() {
		direccio = 0;
		accDelta = 0;
		pas = 0;
		deltaX = 4;
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
		darrerX = x;
        darrerY = y;
        
		if (estat == Constants.ESTAT_INACTIU) {
			Rectangle limits = ctx.getJoc().getHeroi().getLimits();
			Rectangle t = getLimits();

			if ((direccio == Constants.DIRECCIO_OEST && limits.getX() < t.getX()) ||
					(direccio == Constants.DIRECCIO_EST && limits.getX() > t.getX())) {

				// donem 20 pixels més per a que el vampir surti abans
				t.setBounds((int)limits.getX(), (int)t.getY(), 
						(int)t.getWidth() + 20, (int)t.getHeight() + 20);

				if (limits.intersects(t)) {
					estat = Constants.ESTAT_ACTIU;
				}
			}                        
		}
		else {
			int r = 1;
			if (direccio == Constants.DIRECCIO_OEST) {
				r = -1;
			}
			int auxX = x + deltaX * r;
			if (testMur(ctx, auxX, y)) {
				inicialitzar();
				estat = Constants.ESTAT_INACTIU;
			} else {
				x = auxX;
			}
			accDelta += deltaX;
		}
	}

	/**
	 * 
	 */
	public void tractarColisio(Colisio colisio) {
		Actor actor = colisio.getActor();
		actor.setVida(actor.getVida() - DANY);
	}

	/**
	 * 
	 */
	public void render(Graphics2D g) {
		Image image = imatges[ pas ];

		if (darrerX != x || darrerY != y) {
			pas = (accDelta / INC_X_NOU_PAS) % NUM_PASSOS;
		}

		g.drawImage(image, x, y, observer);
	}

	// private methods *********************************************************

	private void init() {
		imatges = new Image[NUM_PASSOS];

		// Imatges pel vampir
		imatges[0] = Util.carregarImatge("dracula.png", 0, 40, AMPLADA, ALCADA);
		imatges[1] = Util.carregarImatge("dracula.png", (AMPLADA), 40, AMPLADA, ALCADA);
		imatges[2] = Util.carregarImatge("dracula.png", (AMPLADA*2), 40, AMPLADA, ALCADA);
		imatges[3] = Util.carregarImatge("dracula.png", (AMPLADA*3), 40, AMPLADA, ALCADA);
		
	}

	private void calcularNivellVida(Context ctx) {
		long t = ctx.getTempsFrame();
		float dany = DANY_PER_SEGON * t / 1000.f;
		setVida(Math.max(0, getVida() - dany));
	}

}
