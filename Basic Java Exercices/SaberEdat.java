import java.util.Scanner;
public class SaberEdat
{
	public static void main( String[ ] args )
	{
		System.out.print("Digue'm la teva edat loco: ");
		Scanner scan = new Scanner(System.in);
		int edat= scan.nextInt();
		int edatfinal = 2015 - edat;
		System.out.println("Vas neixer al " + edatfinal);
	}
}
