import java.io.*;
import java.util.*;

public class dynamic_data_problem1_rev0 {
    
    public static void main (String [] args) throws IOException {
        BufferedReader stdIn = new BufferedReader (new InputStreamReader (System.in));
        int num = getValidNumber (stdIn, "Number of Cards: ", -1, -1);
        List <Integer> cards = new ArrayList <Integer> ();
                List <Integer> new1 = new ArrayList <Integer> ();

        Iterator it = cards.iterator ();
        
        for (int i = 1; i <= num; i++)
            cards.add(i);
        
        while (true) {
            if (it.hasNext())
                break;  
            new1.add(cards.get(0));     
            cards.remove(0);
            cards.add(cards.get(0));
            cards.remove(0);
                      
        }
        
        print (new1);
        
    }
    
    public static void print (List <Integer> it) {
        for (int i = 0; i< it.size(); i++) {
            System.out.print (it.get (i) + " ");
        }
    }
    
    private static int getValidNumber (BufferedReader stdIn, String question, int min, int max) throws IOException {
        while (true) {
            System.out.print(question);
            String tempRating= stdIn.readLine();
            try {    //checks to see if the num is actually a num
                int tempNum = Integer.parseInt(tempRating);
                if (min!=-1 && max !=-1) {  //if both max and min parameters are -1, then it won't check for range
                    if (tempNum<min)
                        throw new NumberFormatException();      //range check
                    else if (tempNum>max)
                        throw new NumberFormatException();
                }
                return Integer.parseInt(tempRating);
            }
            catch (NumberFormatException e) {  } //no bueno
        }
    }
}