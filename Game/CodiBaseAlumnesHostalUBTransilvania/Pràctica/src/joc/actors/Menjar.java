package joc.actors;


import joc.Actor;
import joc.Colisio;
import joc.Constants;
import joc.Context;
import joc.Util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;


/**
 * El menjar proporciona un increment en el nivell de vida de l'actor que 
 * col·lisiona amb ella.
 * 
 * @author danieldelrio@ub.edu
 */
public class Menjar extends AbstractActor {

    private float calories;
    private Image image;
    private int amplada;
    private int alcada;
    private String nom;
    private String fitxer;

    
    /**
     * Crea un nou objecte del tipus Menjar amb una subimage del fitxer
     * <code>objectes.png</code>, el nom i calories proporcionades. Les calories
     * aporten un guany proporcional en la vida del jugador.
     * 
     * @param nom Nom
     * @param calories guany de vida 
     * @param imgPosX posició x de la subimage
     * @param imgPosY posició y de la subimage
     * @param imgAmplada amplada de la subimage
     * @param imgAlcada alçada de la subimage
     */
    public Menjar(String nom, String fitxer, float calories, int imgPosX, int imgPosY, int imgAmplada, int imgAlcada) {
        this.nom = nom;
        this.calories = calories;
        this.image = Util.carregarImatge(fitxer, imgPosX, imgPosY, imgAmplada, imgAlcada);
        this.amplada = imgAmplada;
        this.alcada = imgAlcada;
        this.fitxer = fitxer;
        setEstat(Constants.ESTAT_ACTIU);
    }
        
    /**
     * Obté el número de calories.
     * 
     * @return el número de calories
     */
    public float getCalories() {
        return calories;
    }
    
    public void setCalories(float calories) {
        this.calories = calories;
    }

    public void actualitzar(Context context) {
        // no fem res, es estàtic
    }

    public Rectangle getLimits() {
        return new Rectangle(getX(), getY(), amplada, alcada);
    }

    public void tractarColisio(Colisio colisio) {
        Actor actor = colisio.getActor();
        actor.setVida(Math.max(0, Math.min(100.0f, actor.getVida() + calories)));
        setEstat(Constants.ESTAT_INACTIU);        
    }

    public void render(Graphics2D g) {
        g.drawImage(image, getX(), getY(), observer);
        g.setColor(Color.CYAN);
        Font f = new Font("Dialog", Font.BOLD, 12);
        g.setFont(f);
        g.drawString(nom, getX(), getY() - 3);
    }
           
}
