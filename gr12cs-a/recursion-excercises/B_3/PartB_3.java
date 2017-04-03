//by Artin Sarkezians
import java.util.Scanner;
public class PartB_3 {
    public static void main (String args[]) {
       Scanner scan = new Scanner (System.in);
       System.out.println("what char do you want: ");
       String word = scan.nextLine();
       System.out.println("how many times should I repeat: ");
       int number = scan.nextInt();
       scan.close();
       System.out.println(reprep(word, number));        
    }
    
    private static String reprep (String word, int rep) {
        if (rep == 1)
            return word;
        if (rep <=0)
            return "";
        return word + reprep((word), (rep-1));
    }
}