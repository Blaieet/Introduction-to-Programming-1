
package edu.ub.misionespacial;


/**
 * Mante les dades del joc. la fortalesa i l'heroi.
 * 
 * @author danieldelrio@ub.edu
 */
public class Joc {

    private Fortalesa fortalesa;
    private Actor heroi;
    private Actor armaHeroi;

    
    /**
     * Constructor.
     */    
    public Joc() {        
    }
    
    public Joc(Fortalesa fortalesa, Actor heroi, Actor armaHeroi) {
        this.fortalesa = fortalesa;
        this.heroi = heroi;
        this.armaHeroi = armaHeroi;
    }
    
    public void iniciar() {
        fortalesa.setPlanta(0);
        fortalesa.setNumHabitacio(0);
                
        // inicialitzar actors
        heroi.inicialitzar();
        armaHeroi.inicialitzar();

        for (int i = 0; i < fortalesa.getNumPlantes(); i++) {
            Habitacio[] arr = fortalesa.getHabitacions(i);
            if (arr != null) {
                for (Habitacio h : arr) {
                    if (h != null) {
                        for (Actor actor : h.getActors()) {
                            actor.inicialitzar();
                        }
                    }
                }
            }
        }        
    }
            
    /**
     * Estableix la fortalesa de los Morc.
     * 
     * @param fortalesa la fortalesa
     */
    public void setFortalesa(Fortalesa fortalesa) {
        this.fortalesa = fortalesa;
    }
    
    public Fortalesa getFortalesa() {
        return fortalesa;
    }
    
    /**
     * Estableix l'heroi.
     * 
     * @param heroi
     */
    public void setHeroi(Actor heroi) {
        this.heroi = heroi;
    }
    
    public Actor getHeroi() {
        return heroi;
    }
    
    /**
     * Estableix l'arma de l'heroi.
     * 
     * @param heroi
     */
    public void setArmaHeroi(Actor armaHeroi) {
        this.armaHeroi = armaHeroi;
    }
    
    public Actor getArmaHeroi() {
        return armaHeroi;
    }
                    
    
}
