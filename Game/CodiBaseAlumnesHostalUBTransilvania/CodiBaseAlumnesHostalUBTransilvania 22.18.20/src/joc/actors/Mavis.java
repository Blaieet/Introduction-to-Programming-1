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




public class Mavis extends AbstractActor {

	private Image image;
	private int amplada;
	private int alcada;
	private String nom;
	private String fitxer;

    //Estableixo com es Mavis
	public Mavis() {
		this.nom = "Mavis"; //Nom
		this.image = Util.carregarImatge("princess.png", 0, 0, 36, 42); //Dimensions
		this.amplada = 36;
		this.alcada = 42;
		this.fitxer = "princess.png"; //Arxiu
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
        Heroi heroi = (Heroi)actor; //cast d'heroi
        heroi.setMavisTrobada(); //Quan trobi la Mavis, crida el metode que canvia MavisTrobada a true
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