import java.util.Scanner;
public class Bicicleta {
    private String  model;
    private double  pes;
    private boolean teSuspensio;
    private double  preu;

    public void setModel(String m) {
	model = m;
    }
    public String getModel() {
	return (model);
    }

    public void setPes(double p) {
	pes = p;
    }
    public double getPes() {
	return (pes);
    }

    public void setTeSuspensio(boolean ts) {
	teSuspensio = ts;
    }
    public boolean getTeSuspensio() {
	return (teSuspensio);
    }

    public void setPreu(double p) {
	preu = p;
    }
    public double getPreu() {
	return (preu);
    }
    public void obteBicicleta(Scanner sc) {
	System.out.println("Model?");;
	model = sc.next();
	System.out.println("Pes?");;
	pes = sc.nextDouble();
	System.out.println("Te suspensió (s/*)?");
	teSuspensio = sc.next().equals("s");
	System.out.println("Preu?");;
	preu = sc.nextDouble();
    }
    public String toString() {
	return ("Bicicleta: Model: "+model+"\n\t Pes: "+pes
		+"\n\t Suspensio:"+teSuspensio+"\n\t Preu: " +preu);
    }
}

