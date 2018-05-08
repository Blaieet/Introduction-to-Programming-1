import java.util.*;
public class AlumnesAmbClases{
  public static final int NUM_ALUMNES = 4;
  public static void main(String [] args){
  Scanner sc= new Scanner(System.in);
  
  TaulaAlumnes t = new TaulaAlumnes(NUM_ALUMNES);
  Alumne a;
  
  for (int i = 0; i < NUM_ALUMNES; i++){
	a = new Alumne();
	a.obtenirAlumne(sc);
	t.afegirAlumne(a);
}
    
  System.out.println("La mitjana es "+t.mitjanaNotes());
  }
}