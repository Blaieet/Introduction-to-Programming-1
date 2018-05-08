package joc;




import java.awt.Graphics2D;

/**
 * El hostal conté les habitacions distribuides per plantes on es succeeïx l'acció,
 * també manté el número de planta i habitació actuals on és troba el
 * jugador.
 * 
 * @author danieldelrio@ub.edu
 */
public class Hostal implements Renderable {

    private int numPlanta;
    private int numHabitacio;
    
    private Habitacio[][] habitacions;
    
    /**
     * Número de plantes del hostal.
     */
    public static final int NUM_PLANTES = 3;
    
    /**
     * Número d'habitacions per cada planta.
     */
    public static final int NUM_HABITACIONS_PER_PLANTA = 4;
    
    /**
     * Constructor.
     * @param numPlantes el número de plantas
     * @param numHabitacionsPerPlanta el número d'habitacions per planta
     */
    public Hostal(int numPlantes, int numHabitacionsPerPlanta) {
        habitacions = new Habitacio[numPlantes][numHabitacionsPerPlanta];
    }

    /**
     * Incialitzacions.
     */
    public void inicialitzar() {
        numPlanta = 0;
        numHabitacio = 0;
    }
    
    /***
     * Afegeix una habitació.
     * 
     * @param planta número de planta
     * @param numHabitacio número d'habitació
     * @param habitacio habitació
     */
    public void addHabitacio(int planta, int numHabitacio, Habitacio habitacio) {
        habitacions[planta][numHabitacio] = habitacio;
    }
        
    /**
     * Obté el número de plantes.
     * 
     * @return el número de plantes que té el hostal
     */
    public int getNumPlantes() {
        return habitacions.length;
    }
    
    /**
     * Obté el número d'habitacions.
     * 
     * @param planta la planta
     * @return el número d'habitacions que té la planta
     */
    public int getNumHabitacions(int planta) {
        return habitacions[planta].length;
    }
    
    /**
     * Estableix la planta actual.
     * 
     * @param planta el número de planta
     */
    public void setPlanta(int planta) {
        this.numPlanta = planta;
    }
    
    public int getPlanta() {
        return numPlanta;
    }

    /**
     * Estableix la habitació actual.
     * 
     * @param habitacio el número d'habitació
     */
    public void setNumHabitacio(int habitacio) {
        this.numHabitacio = habitacio;
    }
    
    public int getNumHabitacio() {
        return numHabitacio;
    }
    
    /**
     * Dibuixa l'habitació actual.
     * 
     * @param g els graphics
     */
    public void render(Graphics2D g) {        
        getHabitacio().render(g);
    }    

    /**
     * Obté l'habitació actual.
     * 
     * @return l'habitació
     */
    public Habitacio getHabitacio() {
        return habitacions[numPlanta][numHabitacio];
    }
    
    /**
     * Obté una habitació determinada.
     * 
     * @param numPlanta el número de planta
     * @param numHabitacio el número d'habitació dins de la planta
     * @return la habitació
     */
    public Habitacio getHabitacio(int numPlanta, int numHabitacio) {
        return habitacions[numPlanta][numHabitacio];
    }
    
    /**
     * Obté totes les habitacions d'una planta.
     * 
     * @param numPlanta el número de planta
     * @return el array d'habitacions
     */
    public Habitacio[] getHabitacions(int numPlanta) {
        return habitacions[numPlanta];
    }
        
}
