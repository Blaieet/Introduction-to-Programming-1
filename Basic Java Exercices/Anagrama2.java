    import java.util.Scanner;

    public class Anagrama2 {
    static Scanner sc = new Scanner(System. in );
    static String word1 = sc.nextLine();
    static String word2 = sc.nextLine();

    public static void main(String[] args) {
        System.out.println("The Result is " + check(word1, word2));
    }

    private static boolean check(String w1, String w2) {

        if (w1.trim().length() != w2.trim().length())
            return false;

        for (int i = 0; i < w1.length(); i++) {
            if (w2.indexOf(w1.charAt(i)) < 0)
                return false;
        }
        return true;
    }
}