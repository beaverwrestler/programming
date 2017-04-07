import java.io.*;
import java.util.*;

public class Assignment_3_rev0 {
    private static double rating = 0;
    private static String categ = "";
    private static String name = "";
    private static int rememberThis = 0;
    private static ArrayList <Movie> film = new ArrayList();
    
    public static void main (String [] args) throws FileNotFoundException, IOException {
        System.out.println("Processing...");
        makeObjects();   
                        
        BufferedReader readInp = new BufferedReader (new InputStreamReader (System.in));
        System.out.println("Enter the name or genre you'd like to search by: ");
        String usrInp = readInp.readLine();            
        usrInp.toUpperCase();
        
        while (!usrInp.equals("exit")) {
            //right now, this just assumes the criteria will be genre
            System.out.println("Searching...");
            
            
            
            System.out.println("Enter the name or genre you'd like to search by: ");
            usrInp = readInp.readLine();
            usrInp.toUpperCase();
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
                film.add(new Movie (name, categ, rating)); 
                
                name = "";
                categ = "";
                rating = 0.0;
            }
        }    
        fileInput.close();
    }
}