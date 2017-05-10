import java.io.*;
import java.util.*;

public class dynamic_data_problem2_rev0 {
    
    public static void main (String [] args) throws IOException {
        BufferedReader stdIn = new BufferedReader (new InputStreamReader (System.in));
        int num = getValidNumber (stdIn, "Number of Cards: ", -1, -1);
        List <Integer> cards = new ArrayList <Integer> ();
        
        cards.add(num);        
        for (int i = num; i >1; i--) {
            num--;
            cards.add(0, cards.get(cards.size()-1));
            cards.remove(cards.size()-1);
            cards.add(0, num);
        }        
        System.out.println(cards);
        stdIn.close();
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