import java.util.*;
import java.io.*;

public class Comp3_rev0 {
    
    int [] [] table = new int [6] [6];


    public static void main (String [] args)  throws FileNotFoundException {
        Scanner input = new Scanner (new File("input.txt"));
        table (3, input);
        table (2, input);
        
        
    }
    
    public static void table (int num, Scanner input) throws FileNotFoundException {

        for (int j = 0; j < num; j ++) {
            StringTokenizer wordz = new StringTokenizer (input.nextLine(), ", ");
            
            for (int i = 0; i < wordz.countTokens(); i++){
                String temp = wordz.nextToken();
                if (temp.length()> 3) {
                    for (int k = 0; k< String.length(); k ++) {
                        
                    }
                }
                else {
                    table
                }
            } 
        }
    }
    
}