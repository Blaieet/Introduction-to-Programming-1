package joc;

import joc.actors.Fletxa;
import joc.actors.Heroi;
import joc.actors.Menjar;
import joc.actors.Torxa;
import joc.actors.Vampir;
import joc.actors.Pastis; //importa la clase pastis
import joc.actors.Lletres; //importa la clase lletres
import joc.actors.Monstre; //importa el monstre
import joc.actors.Mavis; // importa la princesa


/**
 * Demo.
 * 
 * @author danieldelrio@ub.edu
 */
public class Practica {

    private Joc joc;
    private Hostal hostalUB;
    private Heroi heroi;
    private Monstre monstre;
    private Mavis mavis;

    
    private static final int MAX_MENJAR_PER_HABITACIO = 2;
    private static final int MAX_PASTIS_PER_PLANTA = 4;
    
    private Practica() {

        hostalUB = new Hostal(3, 3); //Hi haurà 3 habitacions per cada 3 plantes
        hostalUB.addHabitacio(0, 0, crearHabitacio0Planta0());
        hostalUB.addHabitacio(0, 1, crearHabitacio1Planta0());
        hostalUB.addHabitacio(0, 2, crearHabitacio2Planta0());
        hostalUB.addHabitacio(1, 0, crearHabitacio0Planta1());
        hostalUB.addHabitacio(1, 1, crearHabitacio1Planta1());
        hostalUB.addHabitacio(1, 2, crearHabitacio2Planta1());
        hostalUB.addHabitacio(2, 0, crearHabitacio0Planta2());   
        hostalUB.addHabitacio(2, 1, crearHabitacio1Planta2());
        hostalUB.addHabitacio(2, 2, crearHabitacio2Planta2());                       
        
        heroi = new Heroi();
        Habitacio h = hostalUB.getHabitacio(0, 0);
        int[] p = h.getPosicioCela(10, 10);
        heroi.setPosicioInicial(p[0], p[1]);
        
        joc = new Joc(hostalUB, heroi);
                  
        //funcions que distribueixen els seguents elements per totes les plantes:
        distribuirMenjar(); 
        distribuirPastis();
        distribuirMonstre();
        distribuirLletres();

                
        GUIJoc transilvania = new GUIJoc(joc);               
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


    //Creo la primera habitacio de la meva primera planta:

    private Habitacio crearHabitacio0Planta1() {
        Habitacio h = Util.carregarHabitacio("h1_0.txt"); //Carrego l'arxiu amb l'estructura de la planta

        Porta porta = h.getPorta(15, 24); //Localitzacio de la porta
        porta.setNumPlantaDesti(1); //A on porta aquesta porta
        porta.setNumHabitacioDesti(1); //A quina habitacio porta
        porta.setPosicioHabitacioDesti(h.getPosicioCela(1, 1)); //Coordenades de sortida de la porta

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

        //Mavis

        mavis = new Mavis();
        int[] p = h.getPosicioCela(8,12);
        mavis.setPosicioInicial(p[0], p[1]); 
        h.addActor(mavis);

        return h;
    }
    

    private void distribuirMenjar() {
        String[] menjars = {  "Aigua Beneida", "Coca Cola"};
        String[] fitxers = {  "objectes.png", "objectes.png"};
        
        // { calories,x,y,width,height } de cada imatge
        int[][] dades = {
            { 25, 265, 40, 33, 37 },
            { 20, 107, 0, 25, 40 },
            
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

    //funcio que distribueix els pastisos per les plantes

    private void distribuirPastis() {
    	String[] pastisos = {"Pastis"}; //Nom de la imatge
    	String[] fitxers = {"cake.png"}; //Archiu corresponent a la imatge

    	//invisibilitat, x,y width, height

    	int[][] dades = {
    		{ 20, 200, 20, 32, 28 } //x,y width, height de la imatge
    	};


    	
    	int numPastisos = MAX_PASTIS_PER_PLANTA; //Hi haura 4 pastisos per planta
    	int plantes = hostalUB.getNumPlantes();

    	for (int i = 0; i < plantes; i++) {
            numPastisos = MAX_PASTIS_PER_PLANTA;
    		while (numPastisos > 0) {

                //Mentre no hi hagi 4, afegeix un pastis en una posicio random
    			int j, hab;
    			hab = hostalUB.getNumHabitacions (i);
    			j= (int) (Math.random()* hab);
    			Habitacio h = hostalUB.getHabitacio(i,j);
    			int[] cela = obtenirCelaLliure(h);
                //Un pastis es dira m, i estableixo les seves dades
    			Pastis m = new Pastis(pastisos[0], fitxers[0], (float)(dades[0][0]), dades[0][1], dades[0][2], dades[0][3], dades[0][4]);

    			int[] posicio = h.getPosicioCela(cela[0], cela[1]);
    			m.setPosicioInicial(posicio[0], posicio[1]);
    			h.addActor(m); //Afegeix el pastis
    			numPastisos -=1;

    		}


    	}
    }

    //Funcio que distribueix un monstre per sala
    private void distribuirMonstre() {
        for (int i = 0; i < hostalUB.getNumPlantes(); i++) {
            for (int j = 0; j < hostalUB.getNumHabitacions(i); j++) {
                Habitacio h = hostalUB.getHabitacio(i, j);
                int[] cela = obtenirCelaLliure(h); //Obte cela lliure per afegirlo
                Monstre m = new Monstre();
                int[] p = h.getPosicioCela(cela[0], cela[1]);
                m.setPosicioInicial(p[0], p[1]);
                m.setDireccio((int)(Math.random()*4)+1);
                h.addActor(m); //afegeix el monstre
        
                }
            }
    }

    //Funcio que distribueix lletres de manera aleatoria, 2 imatges per cada lletra

    private void distribuirLletres() {

        String[] fitxers = {  "alfabet.png", "alfabet.png","alfabet.png", "alfabet.png" }; //Defineix la imatge per cada lletra
        
        //defineix les cordenades x, y, amplada y alcada de cada imatge:
        int[][] dades = {
            {65, 0, 30, 30 },
            { 32, 64, 30, 30 },
            {0, 0, 30, 30},
            {0, 127, 30, 30}
        };

        //Lletres a trobar:
        char [] c = {'c','l','a','u'}; 
        int i,j;
        for (int a = 0; a<4; a++){
            for (int b = 0; b < 2; b++) {

                //Fes el random de plantes

                i = (int)(Math.random()*hostalUB.getNumPlantes()); 
                j = (int)(Math.random()*hostalUB.getNumHabitacions(i));

                Habitacio h = hostalUB.getHabitacio(i, j);

                int[] cela = obtenirCelaLliure(h);

                //Defineix les dades de la imatge lletres

                Lletres m = new Lletres(fitxers[a],c[a], dades[a][0], dades[a][1], dades[a][2], dades[a][3]);

                int[] posicio = h.getPosicioCela(cela[0], cela[1]);
                m.setPosicioInicial(posicio[0], posicio[1]);
                h.addActor(m); //Afegeix les lletres
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
        new Practica();
    }
    
}
