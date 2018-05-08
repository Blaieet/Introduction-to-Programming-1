package joc;




import java.awt.event.KeyEvent;

/**
 * Constants del programa utilitzades freqüentment.
 * 
 * @author danieldelrio@ub.edu
 */
public interface Constants {

    /**
     * Número de frames per segon (50).
     */
    public static int FRAMES_PER_SEGON = 50;
    
    public static int AMPLADA_FINESTRA = 800;
    public static int ALCADA_FINESTRA = 576;
    
    public static char SIMBOL_PORTA = 'p';
    public static char SIMBOL_MUR = 'x';
    public static char SIMBOL_TERRA = '0';
 
    public static short DIRECCIO_NORD = 1;
    public static short DIRECCIO_SUD = 2;
    public static short DIRECCIO_EST = 3;
    public static short DIRECCIO_OEST = 4;
    
    public static int KEY_UP = KeyEvent.VK_UP;
    public static int KEY_DOWN = KeyEvent.VK_DOWN;
    public static int KEY_LEFT = KeyEvent.VK_LEFT;
    public static int KEY_RIGHT = KeyEvent.VK_RIGHT;
    
    public static int NUM_CELES_HORIZONTALS = 25;
    public static int NUM_CELES_VERTICALS = 17;
    public static int AMPLADA_CELA = 32;
    public static int ALCADA_CELA = 32;
    
    public static int ESTAT_INACTIU = 0;
    public static int ESTAT_ACTIU = 1;
}
