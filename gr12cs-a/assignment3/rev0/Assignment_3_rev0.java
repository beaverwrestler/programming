import java.io.*;
import java.util.*;

public class Assignment_3_rev0 {
  
    private static double rating = 0;
    private static String categ = "";
    private static String name = "";
    private static int rememberThis = 0;
    private static ArrayList <Movie> film = new ArrayList();
 
            //ask user what they want to search by
            //ask what they want to seach
            //display info
            //keep looping unitl usr inputs exit OR press any key after to return to main menu
    
    public static void main (String [] args) throws FileNotFoundException {
        System.out.println("Processing...");
        makeObjects();   
        
        Scanner readInp = new Scanner (System.in);
        
        char crit = ' ';
        
        while (crit != 'r' || crit != 'g' || crit != 'n') {
            System.out.println("What would you like to search by? (r = rating, g = genre, n = name)");
            crit = readInp.next().charAt(0);
        }
    }
    
    private static void makeObjects () throws FileNotFoundException {
        Scanner fileInput = new Scanner (new File ("input.txt"));
       
        while (fileInput.hasNextLine()) {
            String currLine = fileInput.nextLine();
            StringTokenizer counter = new StringTokenizer (currLine, "% ");
            if (counter.countTokens()<3)
                System.out.println("There seems to be a problem with this line of input, going to next line");
            else {
                try {
                    rating = Double.parseDouble (counter.nextToken());   
                }
                catch (NumberFormatException e) {
                    System.out.println("There seems to be a problem with this line of input, going to next line");
                    break;
                }
            
                while (counter.hasMoreTokens()) {
                    String test = counter.nextToken();
                    if (!counter.hasMoreTokens())
                        categ = test;
                    else 
                        name += test + " "; 
                }
                
                name = name.trim();
                categ = categ.trim();
                
                System.out.println(name + " " + rating + " " + categ);
                film.add(new Movie (name, categ, rating)); 
                
                name = "";
                categ = "";
                rating = 0.0;
            }
        }    
        fileInput.close();
    }
}