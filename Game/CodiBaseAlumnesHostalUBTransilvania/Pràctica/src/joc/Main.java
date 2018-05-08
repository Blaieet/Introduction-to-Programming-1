package joc;

import joc.actors.Fletxa;
import joc.actors.Heroi;
import joc.actors.Menjar;
import joc.actors.Torxa;
import joc.actors.Vampir;

/**
 * Demo.
 * 
 * @author danieldelrio@ub.edu
 */
public class Main {

    private Joc joc;
    private Hostal hostalUB;
    private Heroi heroi;
    
    private static final int MAX_MENJAR_PER_HABITACIO = 2;
    
    private Main() {
        hostalUB = new Hostal(1, 3);
        hostalUB.addHabitacio(0, 0, crearHabitacio0Planta0());
        hostalUB.addHabitacio(0, 1, crearHabitacio1Planta0());
        hostalUB.addHabitacio(0, 2, crearHabitacio2Planta0());                            
        
        heroi = new Heroi();
        Habitacio h = hostalUB.getHabitacio(0, 0);
        int[] p = h.getPosicioCela(10, 10);
        heroi.setPosicioInicial(p[0], p[1]);
        
        joc = new Joc(hostalUB, heroi);
                  
        distribuirMenjar();
                
        GUIJoc transilvania = new GUIJoc(joc);               
    }
        
    private Habitacio crearHabitacio0Planta0() {
        Habitacio h = Util.carregarHabitacio("h0_2.txt");
        
        Porta porta = h.getPorta(11, 24);
        porta.setNumPlantaDesti(0);
        porta.setNumHabitacioDesti(1);
        porta.setPosicioHabitacioDesti(h.getPosicioCela(11, 1));

        porta = h.getPorta(0, 10);
        porta.setNumPlantaDesti(0);
        porta.setNumHabitacioDesti(2);
        porta.setPosicioHabitacioDesti(h.getPosicioCela(14, 10));        
        
        Vampir vampir = new Vampir();        
        int[] p = h.getPosicioCela(2, 22);
        vampir.setPosicioInicial(p[0], p[1] - 10);
        vampir.setDireccio(Constants.DIRECCIO_OEST);
        h.addActor(vampir);
        
        vampir = new Vampir();        
        p = h.getPosicioCela(7, 16);
        vampir.setPosicioInicial(p[0], p[1] - 10);
        vampir.setDireccio(Constants.DIRECCIO_EST);
        h.addActor(vampir);
        
        Torxa torxa = new Torxa();
        p = h.getPosicioCela(9,20);
        torxa.setPosicioInicial(p[0], p[1]);
        h.addActor(torxa);
        
        torxa = new Torxa();
        p = h.getPosicioCela(11,17);
        torxa.setPosicioInicial(p[0], p[1]);
        h.addActor(torxa);
        
        torxa = new Torxa();
        p = h.getPosicioCela(1,9);
        torxa.setPosicioInicial(p[0], p[1]);
        h.addActor(torxa);
                          
        torxa = new Torxa();
        p = h.getPosicioCela(1,11);
        torxa.setPosicioInicial(p[0], p[1]);
        h.addActor(torxa);
        
        return h;
    }
    
    private Habitacio crearHabitacio1Planta0() {
        Habitacio h = Util.carregarHabitacio("h0_1.txt");

        Porta porta = h.getPorta(11, 0);
        porta.setNumPlantaDesti(0);
        porta.setNumHabitacioDesti(0);
        porta.setPosicioHabitacioDesti(h.getPosicioCela(11, 23));
        
        Fletxa fletxa = new Fletxa();        
        int[] p = h.getPosicioCela(4, 4);
        fletxa.setPosicioInicial(p[0], p[1]);        
        fletxa.setDireccio(Constants.DIRECCIO_EST);
        h.addActor(fletxa);
        
        fletxa = new Fletxa();
        p = h.getPosicioCela(2, 17);
        fletxa.setPosicioInicial(p[0], p[1]);        
        fletxa.setDireccio(Constants.DIRECCIO_OEST);
        h.addActor(fletxa);
 
        return h;
    }

    private Habitacio crearHabitacio2Planta0() {
        Habitacio h = Util.carregarHabitacio("h0_2.txt");
        Porta porta = h.getPorta(16, 10);
        porta.setNumPlantaDesti(0);
        porta.setNumHabitacioDesti(0);
        porta.setPosicioHabitacioDesti(h.getPosicioCela(1, 10));

        return h;
    }
    
    private void distribuirMenjar() {
        String[] menjars = {  "Aigua Beneida", "Coca Cola" };
        String[] fitxers = {  "objectes.png", "objectes.png" };
        
        // { calories,x,y,width,height } de cada imatge
        int[][] dades = {
            { 25, 265, 40, 33, 37 },
            { 20, 107, 0, 25, 40 }
        };
        
        for (int i = 0; i < hostalUB.getNumPlantes(); i++) {
            
            for (int j = 0; j < hostalUB.getNumHabitacions(i); j++) {
                Habitacio h = hostalUB.getHabitacio(i, j);
                int numMenjars = (int)(Math.random() * MAX_MENJAR_PER_HABITACIO) + 1;
                
                for (int k = 0; k < numMenjars; k++) {
                    int[] cela = obtenirCelaLliure(h);
                    int imenjar = (int)(Math.random() * menjars.length);
                    Menjar m = new Menjar(menjars[imenjar], fitxers[imenjar], 
                            dades[imenjar][0], dades[imenjar][1], dades[imenjar][2], 
                            dades[imenjar][3], dades[imenjar][4]);

                    int[] posicio = h.getPosicioCela(cela[0], cela[1]);
                    m.setPosicioInicial(posicio[0], posicio[1]);
                    h.addActor(m);
                }
            }
        }
    }
        
    /**
     * 
     * @param h
     * @return
     */
    private int[] obtenirCelaLliure(Habitacio h) {
        int fila = 0;
        int col = 0;
        int cela[] = null;
        boolean trobada = false;
        boolean terra = false;
        
        while (!trobada) {            
            terra = false;
            while (!terra) {
                fila = (int)(Math.random() * Constants.NUM_CELES_VERTICALS);
                col = (int)(Math.random() * Constants.NUM_CELES_HORIZONTALS);
                terra = h.getValor(fila, col) == Constants.SIMBOL_TERRA;
            }
            
            // comprovar que cap actor està dins la cela
            Actor[] actors = h.getActorsAsArray();
            int i = 0;
            boolean lliure = true;
            while (i < actors.length && lliure) {
                cela = h.getCela(actors[i].getPosicioInicial()[0], 
                        actors[i].getPosicioInicial()[1]);
                lliure = fila != cela[0] || col != cela[1];            
                i++;
            }         
            trobada = lliure;
        }
        return new int[] { fila, col };
    }
    

    /**
     * Principal.
     * 
     * @param args els args
     */
    public static void main(String[] args) {
        new Main();
    }
    
}
