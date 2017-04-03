//by Artin Sarkezians
import java.util.Scanner;
public class PartB_5 
{
   public static void main (String args[]) {
       Scanner scan = new Scanner (System.in);
       System.out.println("enter a word: ");
       String word = scan.nextLine();   
       System.out.println("enter the char you wish to search for: ");
       String letter = scan.nextLine();
       scan.close();
       System.out.println("there is/are " + search(word, letter) + " letter " + letter);
   }
    
   private static int search (String word, String letter) {     
       if (word.indexOf(letter) == -1 || word.isEmpty())
           return 0;
       return 1 + search ((word.substring(word.indexOf(letter)+1, word.length())), letter);       
   }
}