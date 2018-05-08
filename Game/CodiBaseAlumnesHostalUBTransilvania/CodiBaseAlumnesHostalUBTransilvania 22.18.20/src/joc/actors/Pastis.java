package joc.actors;


import joc.Actor;
import joc.actors.Heroi;
import joc.Colisio;
import joc.Constants;
import joc.Context;
import joc.Util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;


public class Pastis extends AbstractActor {

    //Atributs
	private float invisibilitat;
	private Image image;
	private int amplada;
	private int alcada;
	private String nom;
	private String fitxer;

	public Pastis(String nom, String fitxer, float invisibilitat, int imgPosX, int imgPosY, int imgAmplada, int imgAlcada) {

		this.nom = nom;
		this.invisibilitat = invisibilitat;
		this.image = Util.carregarImatge(fitxer, imgPosX, imgPosY, imgAmplada, imgAlcada);
		this.amplada = imgAmplada;
		this.alcada = imgAlcada;
		this.fitxer = fitxer;
		setEstat(Constants.ESTAT_ACTIU);

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
        heroi.setInvisibilitat(Math.max(0, Math.min(100.0f, heroi.getInvisibilitat() + invisibilitat))); //Modifica la invisibilitat, ja que s'ha trobat un pastis
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