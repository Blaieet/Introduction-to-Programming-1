import java . util . Scanner ;
public class Capgirar {
    public static void main ( String [] args ) {
    String s , p;
    int i;
    Scanner sc ;
    sc = new Scanner ( System . in );
    s = sc . next ();
    p = new String ();
    i = 0;
    while (i <s. length ()) {
        p = p + s. charAt (s. length () -1 -i );
        i = i +1;
    }
    System . out . println (p );
    }
}
