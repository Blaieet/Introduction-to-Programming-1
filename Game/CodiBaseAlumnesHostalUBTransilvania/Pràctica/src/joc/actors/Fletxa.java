
package joc.actors;


import joc.Actor;
import joc.Colisio;
import joc.Constants;
import joc.Context;
import joc.Util;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;


/**
 * Una fletxa enverinada! Cada fletxa resta 33 punts de vida al jugador.
 * 
 * @author danieldelrio@ub.edu
 */
public class Fletxa extends AbstractActor {

    private Image[] imatges;
    
    private int direccio;
    private int deltaX;
        
    private static final int AMPLADA = 27;
    private static final int ALCADA = 15;
    private static final float DANY = 25.f;
    
    public Fletxa() {
        direccio = 0;
        deltaX = 4;
        init();        
    }
    
    @Override
    public void inicialitzar() {
        super.inicialitzar();
        estat = Constants.ESTAT_INACTIU;
    }
    
    /**
     * Estableix la direcció de la fletxa.
     * 
     * @param direccio Una de las constants <code>Constants.DIRECCIO_OEST</code>
     * , <code>Constants.DIRECCIO_EST</code>
     */
    public void setDireccio(int direccio) {
        this.direccio = direccio;
    }
        
    public Rectangle getLimits() {
        return new Rectangle(x, y, AMPLADA, ALCADA);
    }

    public void actualitzar(Context ctx) {
        if (estat == Constants.ESTAT_INACTIU) {
            Rectangle limits = ctx.getJoc().getHeroi().getLimits();
            Rectangle t = getLimits();
            
            if ((direccio == Constants.DIRECCIO_OEST && limits.getX() < t.getX()) ||
                    (direccio == Constants.DIRECCIO_EST && limits.getX() > t.getX())) {
                
                // donem 20 pixels més per a que la fletxa surti abans
                t.setBounds((int)limits.getX(), (int)t.getY(), 
                        (int)t.getWidth() + 20, (int)t.getHeight() + 20);

                if (limits.intersects(t)) {
                    estat = Constants.ESTAT_ACTIU;
                }
            }                        
        } else {
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
        }
    }
        
    public void tractarColisio(Colisio colisio) {
        Actor actor = colisio.getActor();
        actor.setVida(Math.min(100.0f, actor.getVida() - DANY));
        inicialitzar();
        setEstat(Constants.ESTAT_INACTIU);        
    }

    public void render(Graphics2D g) {
        if (estat == Constants.ESTAT_ACTIU) {
            if (direccio == Constants.DIRECCIO_EST) {                
                g.drawImage(imatges[1], x, y, observer);
            } else {
                g.drawImage(imatges[0], x, y, observer);                
            }
        }
    }
    
    // private methods *********************************************************
    
    private void init() {
        imatges = new Image[2];
        // direcció oest
        imatges[0] = Util.carregarImatge("fletxa.png", 0, 0, AMPLADA, ALCADA);
        // direcció est
        imatges[1] = Util.carregarImatge("fletxa.png", 30, 0, AMPLADA, ALCADA);        
        estat = Constants.ESTAT_INACTIU;
    }
    
}
