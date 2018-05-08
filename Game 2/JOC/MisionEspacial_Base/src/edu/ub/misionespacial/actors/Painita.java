package edu.ub.misionespacial.actors;

import edu.ub.misionespacial.Actor;
import edu.ub.misionespacial.Colisio;
import edu.ub.misionespacial.Constants;
import edu.ub.misionespacial.Context;
import edu.ub.misionespacial.Util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;


public class Painita extends AbstractActor{


	private int cantitat;
	private int color;
	private int amplada = 40;
	private int alcada = amplada;
	private Image imatge;


	public Painita(int color, int cantitat) {

		this.color = color;
		this.cantitat = cantitat;

		if (color % 2 == 0) {
			if (color == 0) {
				this.imatge = Util.carregarImatge("painita0.png", 0,0,this.amplada, this.alcada);
			} else {
				this.imatge = Util.carregarImatge("painita2.png", 0,0,this.amplada, this.alcada);
			}
		} else {
			this.imatge = Util.carregarImatge("painita1.png", 0,0,this.amplada, this.alcada);
		}

		setEstat(Constants.ESTAT_ACTIU);
	}

	public void tractarColisio(Colisio colisio) {

		Actor actor = colisio.getActor();
		

		if (actor instanceof Heroi) {
			Heroi heroi = (Heroi)actor;
			heroi.addPainita(this);
			setEstat(Constants.ESTAT_INACTIU);
		}
	}

	public int getCantitat() {
		return cantitat;
	}

	public int getColor() {
		return color;
	}



    public void actualitzar(Context context) {
    	// a cada iteracio del joc es crida a actualizar desde la classe Jack al metode actualizarJoc
        // no fem res, es estatic (no se mou)
    }

    public Rectangle getLimits() {
    	// es important per tractar les colisions des de la classe Jack al metode actualizarJoc
        return new Rectangle(getX(), getY(), amplada, alcada);
    }

    public void render(Graphics2D g) {
    	//Com dibuixar a la pantalla principal
        g.drawImage(imatge, getX(), getY(), observer);
    }
}