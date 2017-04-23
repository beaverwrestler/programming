//by Artin S.
import java.util.Scanner;
public class PartB_4 
{
   public static void main (String args[]) {
       Scanner scan = new Scanner (System.in);
       System.out.println("enter a word that you would like to see written backwards and having hyphens between each letter: ");
       String word = scan.nextLine();   
       scan.close();
       System.out.println(hyphen(word));
   }
    
   private static String hyphen (String word) {
       if (word.length() == 1)
           return word;
       return word.substring(word.length()-1, word.length()) + "-" + hyphen (word.substring(0, word.length()-1));
   }
}