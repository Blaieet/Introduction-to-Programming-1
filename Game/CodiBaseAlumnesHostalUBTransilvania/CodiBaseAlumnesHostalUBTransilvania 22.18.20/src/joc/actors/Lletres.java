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


public class Lletres extends AbstractActor { 

    private String fitxer;
    private char lletres; //caracters de la paraula "CLAU"
    private Image image;
    private int amplada;
    private int alcada;


	public Lletres(String fitxer, char lletres, int imgPosX, int imgPosY, int imgAmplada, int imgAlcada) {

        this.lletres = lletres;
		this.image = Util.carregarImatge(fitxer, imgPosX, imgPosY, imgAmplada, imgAlcada);
		this.amplada = imgAmplada;
		this.alcada = imgAlcada;
		this.fitxer = fitxer;
		setEstat(Constants.ESTAT_ACTIU);
	}
    public float getLletres() { //Metode que em retorna les lletres que tinc
        return lletres;
    }

	public void actualitzar(Context context) {
        // no fem res, es estàtic
    }

	public Rectangle getLimits() {
        return new Rectangle(getX(), getY(), amplada, alcada);
    }

    

    public void tractarColisio(Colisio colisio) {
        Actor actor = colisio.getActor();
        Heroi heroi = (Heroi)actor; //Cast d'heroi
        heroi.addLletres(lletres); //Afegeix les lletres
        setEstat(Constants.ESTAT_INACTIU);
    }

    public void render(Graphics2D g) {
        g.drawImage(image, getX(), getY(), observer);
        g.setColor(Color.CYAN);
    }
       
}


