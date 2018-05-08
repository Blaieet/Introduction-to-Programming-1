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
 * La torxa proporciona un decrement en el nivell de vida de l'actor que 
 * col·lisiona amb ella.
 * 
 * @author danieldelrio@ub.edu
 */
public class Torxa extends AbstractActor {

	private int pas;
    public static final int AMPLADA = 28;
	public static final int ALCADA = 30;
	private static final int DANY = 2;
	
	private static double CHANGE_EVERY_MILLIS = 1000;
	
	private double millisUltimCanvi;
	
    private Image[] imatges;
    
    /**
     * Crea un nou objecte del tipus Torxa
     */
    public Torxa() {
        setEstat(Constants.ESTAT_ACTIU);
        millisUltimCanvi = System.currentTimeMillis();
        pas = 0;
        init();
    }
        
 
    public void actualitzar(Context context) {
        double currentMillis = System.currentTimeMillis();
        if(currentMillis >= millisUltimCanvi + CHANGE_EVERY_MILLIS) {
        	pas = (pas + 1) % 2;
        	millisUltimCanvi = currentMillis;
        }
    }

    public Rectangle getLimits() {
        return new Rectangle(getX(), getY(), AMPLADA, ALCADA);
    }

    public void tractarColisio(Colisio colisio) {
    	Actor actor = colisio.getActor();
		actor.setVida(actor.getVida() - DANY);    
    }

    public void render(Graphics2D g) {
        g.drawImage(imatges[pas], getX(), getY(), observer);
    }
    

    private void init() {
	imatges = new Image[2];

	// Imatges per la torxa
	imatges[0] = Util.carregarImatge("foc2.png", 0, 0, AMPLADA, ALCADA);
	imatges[1] = Util.carregarImatge("foc2.png", 28, 0, AMPLADA, ALCADA);
		
    }
           
}
