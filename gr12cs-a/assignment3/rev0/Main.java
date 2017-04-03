import java.io.*;
import java.util.*;

public class Main {
    
    private static double rating = 0;
    private static String categ = "";
    private static String name = "";
    private static int rememberThis = 0;
    
    public static void main (String [] args) throws FileNotFoundException {
        System.out.println("Processing...");
        makeObjects();  
        
        System.out.println(name);
        System.out.println(categ);
        System.out.println(rating);
    }
    
    public static void makeObjects () throws FileNotFoundException {
        Scanner fileInput = new Scanner (new File ("input.txt"));
        
        for (int j = 0; fileInput.hasNextLine(); j++) {
            String currLine = fileInput.nextLine();
            currLine.trim();
            
            StringTokenizer counter = new StringTokenizer (currLine);
            //skip line if no percent sign
            if (counter.countTokens() >2)
            {
                //finds the index of the first % sign of space starting from the left
                for (int i = 0; i<currLine.length(); i ++) {
                    if (currLine.charAt(i) == '%') {

                        rememberThis = i;
                        rating = Double.parseDouble (currLine.substring(0, i));
                        currLine = currLine.substring(i);
                        break;
                    }
               
                }
            
                //finds the index going backwards of the first space
                for (int i = currLine.length()-1; i>-1; i --)
                    if (currLine.charAt(i) == ' ' ) {
                        categ = currLine.substring(i).trim();
                        name = currLine.substring (rememberThis, i).trim();
                        break;
                    }
           }
            
           //code for making objects
           
           Movie mv1 = new Movie (name, categ, rating); 
            
            //ask user what they want to search by
            //ask what they want to seach
            //display info
            //keep looping unitl usr inputs exit OR press any key after to return to main menu
            
        } 
    }
    
    public static void printFirstObject ()
    {
        
        
    }
}