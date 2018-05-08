
package edu.ub.misionespacial.actors;


import edu.ub.misionespacial.Actor;
import edu.ub.misionespacial.Colisio;
import edu.ub.misionespacial.Constants;
import edu.ub.misionespacial.Context;
import edu.ub.misionespacial.Util;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;


/**
 * ArmaHeroi, representa el arma del heroe.
 * 
 * 
 */
public class ArmaHeroi extends AbstractActor {

    private Image imatge;
    
    private int direccio;
    private int deltaX;
    private int deltaY;
        
    private static final int AMPLADA = 26;
    private static final int ALCADA = 26;
    private static final float DANY = 2f;
    
    public ArmaHeroi() {
        direccio = 0;
        deltaX = 4;
        deltaY = 4;
        init();        
    }
    
    @Override
    public void inicialitzar() {
        super.inicialitzar();
        estat = Constants.ESTAT_INACTIU;
    }
    
    public void disparar(int xBola, int yBola, int direccioBola) {
        if (estat == Constants.ESTAT_INACTIU) {
            x = xBola;
            y = yBola;
            direccio = direccioBola;
            estat = Constants.ESTAT_ACTIU;
        }
    }

    public Rectangle getLimits() {
        Rectangle r;

        if (direccio == Constants.DIRECCIO_OEST ||
            direccio == Constants.DIRECCIO_EST) {
            r = new Rectangle(x, y, AMPLADA, ALCADA);
        } else {
            r = new Rectangle(x, y, ALCADA, AMPLADA);
        }

        return r;
    }

    public void actualitzar(Context ctx) {
        if (estat == Constants.ESTAT_ACTIU) {
            int r = 0;
            int l = 0;

            if (direccio == Constants.DIRECCIO_OEST) {
                r = -1;
            } else if (direccio == Constants.DIRECCIO_EST) {
                r = 1;
            } else if (direccio == Constants.DIRECCIO_SUD) {
                l = 1;
            } else if (direccio == Constants.DIRECCIO_NORD) {
                l = -1;
            }

            int auxX = x + deltaX * r;
            int auxY = y + deltaY * l;

            if (testMur(ctx, auxX, auxY) || testPorta(ctx, auxX, auxY)!=null) { 
                inicialitzar();
                estat = Constants.ESTAT_INACTIU;
            } else {
                x = auxX;
                y = auxY;
            }
        }
    }

    public void tractarColisio(Colisio colisio) {
    }

    public void render(Graphics2D g) {
        if (estat == Constants.ESTAT_ACTIU) {
            g.drawImage(imatge, x, y, observer);
        }
    }
    
    // private methods *********************************************************
    
    private void init() {
        imatge = Util.carregarImatge("laserBall.png", 0, 0, AMPLADA, ALCADA);      
        estat = Constants.ESTAT_INACTIU;
    }
    
}
