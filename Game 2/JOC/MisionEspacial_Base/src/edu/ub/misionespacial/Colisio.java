
package edu.ub.misionespacial;

/**
 * Les dades d'una collisio.
 * 
 * @author danieldelrio@ub.edu
 */
public class Colisio {
    
    private  Actor actor;

    /**
     * Nova collisio.
     * 
     * @param actor l'actor amb qui es collisiona.
     */
    public Colisio(Actor actor) {
        this.actor = actor;
    }
    
    /**
     * L'actor amb qui es collisiona.
     * 
     * @return l'actor
     */
    public Actor getActor() {
        return actor;
    }

        
}
