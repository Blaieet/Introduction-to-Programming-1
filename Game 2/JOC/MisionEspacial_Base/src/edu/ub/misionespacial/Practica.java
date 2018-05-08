
package edu.ub.misionespacial;

import edu.ub.misionespacial.actors.Heroi;
import edu.ub.misionespacial.actors.Oxigen;
import edu.ub.misionespacial.actors.Soldat;
import edu.ub.misionespacial.actors.ArmaHeroi;
import edu.ub.misionespacial.actors.Painita;

/**
 * Demo.
 * 
 */
public class Practica {

    private Joc joc;
    private Fortalesa fortalesa;
    private Heroi heroi;
    private ArmaHeroi armaHeroi;

    private static final int MAX_OXIGEN_PER_HABITACIO = 2;
    private static final int MAX_SOLDATS_PER_HABITACIO = 2;
    private static final int MAX_PAINITA = 6;
    private static final int NUM_COLORS = 2;

    
    private Practica() {

    
        fortalesa = new Fortalesa(3, 3);
        fortalesa.addHabitacio(0, 0, crearHabitacio0Planta0());
        fortalesa.addHabitacio(0, 1, crearHabitacio1Planta0());
        fortalesa.addHabitacio(0, 2, crearHabitacio2Planta0());
        fortalesa.addHabitacio(1, 0, crearHabitacio0Planta1());
        fortalesa.addHabitacio(1, 1, crearHabitacio1Planta1());
        fortalesa.addHabitacio(1, 2, crearHabitacio2Planta1());
        fortalesa.addHabitacio(2, 0, crearHabitacio0Planta2());   
        fortalesa.addHabitacio(2, 1, crearHabitacio1Planta2());
        fortalesa.addHabitacio(2, 2, crearHabitacio2Planta2());                            
        
        heroi = new Heroi();
        Habitacio h = fortalesa.getHabitacio(0, 0);
        int[] p = h.getPosicioCela(10, 10);
        heroi.setPosicioInicial(p[0], p[1]);
        
        armaHeroi = new ArmaHeroi();

        distribuirOxigen();
        distribuirSoldats();
        distribuirPainita();
               
        //initialitzacio del joc
        joc = new Joc(fortalesa, heroi, armaHeroi);
        Jack jda = new Jack(joc);      
    }
    
        
    private Habitacio crearHabitacio0Planta0() {

        
        Habitacio h = Util.carregarHabitacio("h0_0.txt");
        
        Porta porta = h.getPorta(11, 24);
        porta.setNumPlantaDesti(0);
        porta.setNumHabitacioDesti(1);
        porta.setPosicioHabitacioDesti(h.getPosicioCela(11, 1));

        porta = h.getPorta(0, 10);
        porta.setNumPlantaDesti(0);
        porta.setNumHabitacioDesti(2);
        porta.setPosicioHabitacioDesti(h.getPosicioCela(14, 10));

        porta = h.getPorta(16, 10);
        porta.setNumPlantaDesti(1);
        porta.setNumHabitacioDesti(0);
        porta.setPosicioHabitacioDesti(h.getPosicioCela(1,10));       
        
        return h;
    }
    
    private Habitacio crearHabitacio1Planta0() {
        Habitacio h = Util.carregarHabitacio("h0_1.txt");

        Porta porta = h.getPorta(11, 0);
        porta.setNumPlantaDesti(0);
        porta.setNumHabitacioDesti(0);
        porta.setPosicioHabitacioDesti(h.getPosicioCela(11, 23));
       

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

    private Habitacio crearHabitacio0Planta1() {

        Habitacio h = Util.carregarHabitacio("h1_0.txt");

        Porta porta = h.getPorta(15,24);
        porta.setNumPlantaDesti(1);
        porta.setNumHabitacioDesti(1);
        porta.setPosicioHabitacioDesti(h.getPosicioCela(1,1));

        porta = h.getPorta(15, 0);
        porta.setNumPlantaDesti(1);
        porta.setNumHabitacioDesti(2);
        porta.setPosicioHabitacioDesti(h.getPosicioCela(1, 23));

        porta = h.getPorta(0, 10);
        porta.setNumPlantaDesti(0);
        porta.setNumHabitacioDesti(0);
        porta.setPosicioHabitacioDesti(h.getPosicioCela(15, 10));

        porta = h.getPorta(16, 10);
        porta.setNumPlantaDesti(2);
        porta.setNumHabitacioDesti(0);
        porta.setPosicioHabitacioDesti(h.getPosicioCela(1, 10));

        return h;

    }

    private Habitacio crearHabitacio1Planta1() {
        Habitacio h = Util.carregarHabitacio("h1_1.txt");
        Porta porta = h.getPorta(1, 0);
        porta.setNumPlantaDesti(1);
        porta.setNumHabitacioDesti(0);
        porta.setPosicioHabitacioDesti(h.getPosicioCela(15, 23));

        return h;
    }

    private Habitacio crearHabitacio2Planta1() {
        Habitacio h = Util.carregarHabitacio("h1_2.txt");
        Porta porta = h.getPorta(1, 24);
        porta.setNumPlantaDesti(1);
        porta.setNumHabitacioDesti(0);
        porta.setPosicioHabitacioDesti(h.getPosicioCela(15, 1));

        return h;
    }

    private Habitacio crearHabitacio0Planta2() {
        Habitacio h = Util.carregarHabitacio("h2_0.txt");

        Porta porta = h.getPorta(8, 0);
        porta.setNumPlantaDesti(2);
        porta.setNumHabitacioDesti(2);
        porta.setPosicioHabitacioDesti(h.getPosicioCela(8, 23));

        porta = h.getPorta(8, 24);
        porta.setNumPlantaDesti(2);
        porta.setNumHabitacioDesti(1);
        porta.setPosicioHabitacioDesti(h.getPosicioCela(8, 1));

        porta = h.getPorta(0, 10);
        porta.setNumPlantaDesti(1);
        porta.setNumHabitacioDesti(0);
        porta.setPosicioHabitacioDesti(h.getPosicioCela(15, 10));


        return h;
    }

    private Habitacio crearHabitacio1Planta2() {
        Habitacio h = Util.carregarHabitacio("h2_1.txt");
        Porta porta = h.getPorta(8, 0);
        porta.setNumPlantaDesti(2);
        porta.setNumHabitacioDesti(0);
        porta.setPosicioHabitacioDesti(h.getPosicioCela(8, 23));

        return h;
    }

    private Habitacio crearHabitacio2Planta2() {
        Habitacio h = Util.carregarHabitacio("h2_2.txt");
        Porta porta = h.getPorta(8, 24);
        porta.setNumPlantaDesti(2);
        porta.setNumHabitacioDesti(0);
        porta.setPosicioHabitacioDesti(h.getPosicioCela(8, 1));

        return h;
    }

    private void distribuirPainita(){

        int maxPain = MAX_PAINITA;
        int color;
        int cantitat;
        //Vull 1 painita per planta, pero hi ha maxim 6 en tota la fortalesa
        for (int x = 0; x < 3; x++) {

            for (int z = 0; z < MAX_PAINITA; z++) {

                    cantitat = 5 + (int)(Math.random() * ((10 - 5) + 1));

                    //Obtinc una habitacio random
                    int i = (int) (Math.random()* fortalesa.getNumPlantes());
                    int j = (int) (Math.random()* fortalesa.getNumHabitacions(i));
                    Habitacio h = fortalesa.getHabitacio(i,j);
                    int[] cela = obtenirCelaLliure(h);

                    Painita p = new Painita(x,cantitat);
                    int[] posicio = h.getPosicioCela(cela[0], cela[1]);
                    p.setPosicioInicial(posicio[0], posicio[1]);
                    h.addActor(p); //Afegeix el pastis
                    maxPain -=1;

                }
            }
        }
    
    private void distribuirOxigen() {
        
        int[] dades = {25,50,75}; //quantitat de oxygen

        
        for (int i = 0; i < fortalesa.getNumPlantes(); i++) {
            
            for (int j = 0; j < fortalesa.getNumHabitacions(i); j++) {
                Habitacio h = fortalesa.getHabitacio(i, j);
                int numOxigen = (int)(Math.random() * (MAX_OXIGEN_PER_HABITACIO) + 1);
                
                for (int k = 0; k < numOxigen; k++) {
                    int[] cela = obtenirCelaLliure(h);
   
                    int quantitat = dades[(int)(Math.random() * 3)];
                    Oxigen m = new Oxigen(dades[i]);
                    
                    int[] posicio = h.getPosicioCela(cela[0], cela[1]);
                    m.setPosicioInicial(posicio[0], posicio[1]);
                    h.addActor(m);                     
                }
            }
            
        }
    }
    
    private void distribuirSoldats() {
      
        for (int i = 0; i < fortalesa.getNumPlantes(); i++) {
            
            for (int j = 0; j < fortalesa.getNumHabitacions(i); j++) {
                Habitacio h = fortalesa.getHabitacio(i, j);
                int numSoldats = (int)(Math.random() * (MAX_SOLDATS_PER_HABITACIO)  + 1);
                
                for (int k = 0; k < numSoldats; k++) {
                    int[] cela = obtenirCelaLliure(h);
   
                    Soldat s = new Soldat();
                    
                    int[] posicio = h.getPosicioCela(cela[0], cela[1]);
                    s.setPosicioInicial(posicio[0], posicio[1]);
                    h.addActor(s);                     
                }
            }
            
        }
    }

    private int[] obtenirCelaLliure(Habitacio h) {
        int fila = 0;
        int col = 0;
        int cela[] = null;
        boolean trobada = false;
        boolean terra = false;
        
        while (!trobada) {            
            terra = false;
            while (!terra) {
                fila = (int)Math.max(0,(Math.random() * Constants.NUM_CELES_VERTICALS-2));
                col = (int)Math.max(0,(Math.random() * Constants.NUM_CELES_HORIZONTALS-2));
                terra = (h.getValor(fila, col) == Constants.SIMBOL_TERRA &&
                        h.getValor(fila+1, col) == Constants.SIMBOL_TERRA &&
                        h.getValor(fila, col+1) == Constants.SIMBOL_TERRA &&
                        h.getValor(fila+1, col+1) == Constants.SIMBOL_TERRA );
            }
            
            // comprovar que cap actor esta dins la cela
            Actor[] actors = h.getActorsAsArray();
            int i = 0;
            boolean lliure = true;
            while (i < actors.length && lliure) {
                cela = h.getCela(actors[i].getPosicioInicial()[0], 
                        actors[i].getPosicioInicial()[1]);
                lliure = fila != cela[0] || col != cela[1];            
                i++;
            }
            
            //comprovar que el heroi no esta dins la cela
            if (lliure) {
	            cela = h.getCela(heroi.getPosicioInicial()[0], 
	                    heroi.getPosicioInicial()[1]);
	            lliure = fila != cela[0] || col != cela[1];
            }

            trobada = lliure;
        }
        return new int[] { fila, col };
    }
    

    /**
     * Principal.
     * 
     * @param args
     */
    public static void main(String[] args) {
        new Practica();
    }
    
}
