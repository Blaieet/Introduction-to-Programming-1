import java.util.*;
public class Multiples3 {
	public static void main (String [] args) {
	Scanner sc = new Scanner(System.in);
	System.out.println("Dona'm el primer numero ");
	int num1,num2, count;
	count = 0;
	num1= sc.nextInt();
	System.out.println("Dona'm el segon numero ");
	num2 = sc.nextInt();
	for (int i = num1; i <= num2; i++) {
		if (i %3 == 0) {
			count=count+1;
		}
	}
	System.out.println("Hi ha "+count+" múltiples de 3");
	}
}