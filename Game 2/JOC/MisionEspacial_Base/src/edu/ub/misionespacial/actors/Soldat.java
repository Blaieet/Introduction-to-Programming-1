
package edu.ub.misionespacial.actors;

import edu.ub.misionespacial.Constants;
import edu.ub.misionespacial.Colisio;
import edu.ub.misionespacial.Context;
import edu.ub.misionespacial.Actor;
import edu.ub.misionespacial.Habitacio;
import edu.ub.misionespacial.Util;
import edu.ub.misionespacial.actors.AbstractActor;
import edu.ub.misionespacial.actors.ArmaHeroi;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

/**
 * Soldats que vigilen la fortalesa, decrementan la vida de Jack
 */
public class Soldat extends AbstractActor {

    private Image[] icones;
    private int currentIcona = 0;
    private int direccio;
    
    private static final int DANY = 1;
    private static final int INCREMENT_POSX = 1;
    private static final int INCREMENT_POSY = 1;
    
    private int targetX =0;
    private int targetY =0;
    private boolean hasTarget=false;
      
    private static final Rectangle DIMENSIONS_ICONA = new Rectangle(0, 0, 60, 30);
    
    public Soldat() {
        init();
    }
    
    @Override
    public void inicialitzar() {
        super.inicialitzar();
        direccio = (int)(Math.random() * 4) + 1;
    }


    public void tractarColisio(Colisio colisio) {

        Actor actor = colisio.getActor();

        if (actor instanceof Heroi) {
            Heroi heroi = (Heroi)actor;//Cast d'heroi
            actor.setVida(0); //Si te menys de 20, mata'l directament
        } else if (actor instanceof ArmaHeroi) {
            setEstat(Constants.ESTAT_INACTIU);
            actor.setEstat(Constants.ESTAT_INACTIU);
        }

    }
    
    /**
     * Sobreescribe el metodo para perseguir al héroe
     * 
     * @param context
     */
    public void actualitzar(Context context) {
        
        Rectangle limits = new Rectangle(getX(), getY(), DIMENSIONS_ICONA.width, DIMENSIONS_ICONA.height);
        
        //establece el target si no lo tiene o lo ha alcanzado
        if(!hasTarget || hasReachedTarget(limits, targetX, targetY))
        {
            targetX = context.getJoc().getHeroi().getPosicio()[0];
            targetY = context.getJoc().getHeroi().getPosicio()[1];
            hasTarget = true;
        }
        
        int posX = getX();
        int posY = getY();
        
        //de esta manera no se quedan totalmente parados cuando no pueden avanzar en diagonal
        int aleatori = (int)(Math.random() * 2);
        
        if (aleatori == 0) {
	        if(getX() > targetX)
	            posX -= INCREMENT_POSX;
	        else if(getX() < targetX)
	            posX += INCREMENT_POSX;
	        else if(getY() > targetY)
	            posY -= INCREMENT_POSY;
	        else if(getY() < targetY)
	            posY += INCREMENT_POSY;
        } else {
	        if(getY() > targetY)
	            posY -= INCREMENT_POSY;
	        else if(getY() < targetY)
	            posY += INCREMENT_POSY;
	        else if(getX() > targetX)
	            posX -= INCREMENT_POSX;
	        else if(getX() < targetX)
	            posX += INCREMENT_POSX;
        }
        
  
        Habitacio h = context.getHabitacio();
        limits = new Rectangle(posX, posY, DIMENSIONS_ICONA.width, DIMENSIONS_ICONA.height);
        
        if (testMur(context, posX, posY) || testPorta(context, posX, posY)!=null)
        {
            hasTarget = false;
        }
        else {
            setPosicio(posX, posY);
        }
        
    }
        
    public boolean hasReachedTarget(Rectangle limits, int targetX, int targetY)
    {
        return limits.getX() <= targetX && limits.getX()+limits.width >= targetX &&
                limits.getY() <= targetY && limits.getY() + limits.height >= targetY;
    }
    
    public Rectangle getLimits() {
        return new Rectangle(getX(), getY(), DIMENSIONS_ICONA.width, 
                DIMENSIONS_ICONA.height);
    }

    public void render(Graphics2D g) {
        g.drawImage(icones[currentIcona], getX(), getY(), observer);
    }

    // private methods *********************************************************
    
    private void init() {
    	icones = new Image [2];
    	BufferedImage iTmp = Util.carregarImatge("alien.png", 
                DIMENSIONS_ICONA.x, DIMENSIONS_ICONA.y, 
                DIMENSIONS_ICONA.width, DIMENSIONS_ICONA.height); 
    	icones[0] = Util.flipImatgeHor(iTmp);
    	icones[1] = iTmp;
        
    }
    
}
