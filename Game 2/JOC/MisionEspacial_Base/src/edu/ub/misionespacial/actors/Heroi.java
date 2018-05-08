
package edu.ub.misionespacial.actors;

import edu.ub.misionespacial.Actor;
import edu.ub.misionespacial.Constants;
import edu.ub.misionespacial.Colisio;
import edu.ub.misionespacial.Context;
import edu.ub.misionespacial.Habitacio;
import edu.ub.misionespacial.Util;
import edu.ub.misionespacial.Porta;
import edu.ub.misionespacial.Fortalesa;
import edu.ub.misionespacial.actors.ArmaHeroi;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

/**
 * Representa el nostre heroi. L'heroi mante una llista amb els cristals de painita trobats
 * 
 */
public class Heroi extends AbstractActor {   
    private Image[] imatges;
    
    public static final int AMPLADA = 22;
    public static final int ALCADA = 32;
    public static final int MIN_GUANYAR_JOC = 90;
    
    private static final int FRAMES_CHANGE = 1;
                
    private boolean armaActivada;

    private boolean bPosChanged = false;
    private int nFramesToChange = FRAMES_CHANGE;
    int lastImage = 0;
    private int deltaX = 10;
    private int deltaY = 8;
    private int direccio = -1;
    
    private static final float DANY_PER_SEGON = 1.0f;

    protected int cantitat;
    private int contador[] = new int[3];
    
    /**
     * Constructor.
     */
    public Heroi() {
        init();
    }
    
    public void inicialitzar() {
        cantitat = 0;
        super.inicialitzar();
        armaActivada = false;
        for (int i = 0; i < contador.length; i++){
            contador[i] = 0;
        }
    }
    
    
    /**
     * Obte un rectangle ambs el limits de l'heroi.
     * 
     * @return els limits, x,y, amplada i alcada
     */
    public Rectangle getLimits() {
        return new Rectangle(x, y, AMPLADA, ALCADA);
    }
    
    public void actualitzar(Context ctx) {
        calcularNivellVida(ctx);
        int desX = 0; 
        int desY = 0;
        bPosChanged = false;
                
        if (ctx.isKeyPressed(Context.KEY_DOWN)) {
            desY = 1;
            direccio = Constants.DIRECCIO_SUD;
            bPosChanged = true;
        } else if (ctx.isKeyPressed(Context.KEY_UP)) { 
            desY = -1;
            direccio = Constants.DIRECCIO_NORD;
            bPosChanged = true;
        } else if (ctx.isKeyPressed(Context.KEY_LEFT)) {
            desX = -1;
            direccio = Constants.DIRECCIO_OEST;
            bPosChanged = true;
        } else if (ctx.isKeyPressed(Context.KEY_RIGHT)) {
            desX = 1;
            direccio = Constants.DIRECCIO_EST;
            bPosChanged = true;
        } else if (ctx.isKeyPressed(Context.KEY_SPACE)) {
            if (armaActivada) {
                ArmaHeroi armaHeroi = (ArmaHeroi)ctx.getJoc().getArmaHeroi();
                armaHeroi.disparar(x, y, direccio);
            }
            bPosChanged = false;
        }
        
        int auxX = x + (int)(deltaX * desX);
        int auxY = y + (int)(deltaY * desY);        
                
        Porta porta = testPorta(ctx, auxX, auxY);
        if (porta != null && porta.getNumPlantaDesti() != -1 && 
                porta.getNumHabitacioDesti() != -1) {
            
            Fortalesa fortalesa = ctx.getJoc().getFortalesa();            
            fortalesa.setPlanta(porta.getNumPlantaDesti());
            fortalesa.setNumHabitacio(porta.getNumHabitacioDesti());
            int[] posicio = porta.getPosicioHabitacioDesti();
            if (posicio != null) {
                x = posicio[0];
                y = posicio[1];
            }
        } else if (!testMur(ctx, auxX, auxY)) {
            x = auxX;
            y = auxY;
        }
    }
    
    public void tractarColisio(Colisio colisio) {
    }

    public void render(Graphics2D g) {
        int nImg = lastImage;

        if (bPosChanged) {
        	nFramesToChange--;
        	if (nFramesToChange == 0) {
        		nFramesToChange = FRAMES_CHANGE;
        	}
        }
        
        if (direccio == Constants.DIRECCIO_EST) {
        	nImg = 0;
        } else if(direccio == Constants.DIRECCIO_OEST) {
        	nImg = 1;
        }

        Image image = imatges[nImg];
        g.drawImage(image, x, y, observer);
        lastImage = nImg;
    }


    
    private void init() {
        imatges = new Image [2];
        BufferedImage iTmp = Util.carregarImatge("astronaut.png", 0, 0, AMPLADA, ALCADA); 
        imatges[0] = Util.flipImatgeHor(iTmp);
        imatges[1] = iTmp;
        cantitat = 0;
    }
    
    private void calcularNivellVida(Context ctx) {
        long t = ctx.getTempsFrame();
        float dany = DANY_PER_SEGON * t / 1000.f;
        setVida(Math.max(0, getVida() - dany));
    }
    
    public void addPainita(Painita cristall) {
        cantitat = cantitat +cristall.getCantitat();
        System.out.println(cantitat);
        int color = cristall.getColor();
        contador[color]++;

        activarArma();

    }

    private void activarArma() {

        int activar = 0;

        for (int i = 0; i < contador.length; i++) {
            if (contador[i] > 0) {
                activar++;
            }
        }

        if (activar == 3 && !armaActivada) {
            armaActivada = true;
            System.out.println("ARMA ACTIVADA!");
        }

    }

    public boolean haTrobatElsCristalls() {

        return cantitat >= MIN_GUANYAR_JOC; //6 cristalls x 3 colors amb un minim de 5 x cristall = 90 minim
    }



}
