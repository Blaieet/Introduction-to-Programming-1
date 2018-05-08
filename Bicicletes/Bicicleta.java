import java.util.Scanner;
public class Bicicleta {
    public String  model;
    public double  pes;
    public boolean teSuspensio;
    public double  preu;

    public void obteBicicleta(Scanner sc) {
        System.out.println("Model?");
        model = sc.next();
        System.out.println("Pes?");
        pes = sc.nextDouble();
        System.out.println("Te suspensio (s/*)?");
        teSuspensio = sc.next().equals("s");
        System.out.println("Preu?");
        preu = sc.nextDouble();
    }
    public String toString() {
        return ("Bicicleta: Model: "+model+"\n\t Pes: "+pes
                +"\n\t Suspensio:"+teSuspensio+"\n\t Preu: " +preu);
    }
}

