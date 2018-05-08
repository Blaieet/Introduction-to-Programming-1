package joc;






/**
 * Manté les dades del joc. el hostal i l'heroi.
 * 
 * @author danieldelrio@ub.edu
 */
public class Joc {

    private Hostal hostal;
    private Actor heroi;

    
    /**
     * Constructor.
     */    
    public Joc() {        
    }
    
    public Joc(Hostal hostal, Actor heroi) {
        this.hostal = hostal;
        this.heroi = heroi;
    }
    
    public void iniciar() {
        hostal.setPlanta(0);
        hostal.setNumHabitacio(0);
                
        // inicialitzar actors
        heroi.inicialitzar();
        for (int i = 0; i < hostal.getNumPlantes(); i++) {
            Habitacio[] arr = hostal.getHabitacions(i);
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
     * Estableix el hostal.
     * 
     * @param hostal el hostal
     */
    public void setHostal(Hostal hostal) {
        this.hostal = hostal;
    }
    
    public Hostal getHostal() {
        return hostal;
    }
    
    /**
     * Estableix l'heroi.
     * 
     * @param heroi el heroi
     */
    public void setHeroi(Actor heroi) {
        this.heroi = heroi;
    }
    
    public Actor getHeroi() {
        return heroi;
    }
                    
    
}
