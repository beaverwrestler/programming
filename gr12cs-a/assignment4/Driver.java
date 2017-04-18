/* Name: Artin Sarkezians   
 * Due Date: Today
 * Assignment: the one with the cd's
*/ 

import java.io.*;
import java.util.*;

public class Driver {
    private static ArrayList <CD> cds = new ArrayList();

    public static void main (String[] args) throws IOException {
    	BufferedReader stdIn = new BufferedReader (new InputStreamReader (System.in));
        importCDs("Millenium.txt");
        String contin = "";
        
        do {        
        	int mainChoice, subChoice;
        	mainChoice = displayMenu (0, stdIn);
            
        	if (mainChoice == 1)
        	    subChoice = displayMenu (1, stdIn);
        	else if (mainChoice == 2)
        	    subChoice = displayMenu (2, stdIn);
            else 
                break;
            
            if (mainChoice == 1) {
                if (subChoice == 1) {
                    System.out.println("\nCDs currently in memory: ");
                    for (int i = 0; i < cds.size(); i ++) 
                        System.out.println(cds.get(i));
                    System.out.println();
                }
                if (subChoice == 2) {
                    System.out.println("Please enter the name of the file/CD you'd like to load into memory: ");
                    String name = stdIn.readLine().toLowerCase();
                    importCDs(name);
                }
            }
        
            else if (mainChoice == 2) {
               
            }          
            while (!contin.equals("y") && !contin.equals("yes") && !contin.equals("n") && !contin.equals("no")) {
                System.out.println ("Would you like to continue? (y/n)");
                contin = stdIn.readLine().toLowerCase();
            }
        } while (contin.equals("y") || contin.equals("yes"));
        
        stdIn.close();
        System.out.println("Thank you and have a terrrible day");
    }
    
    public static void twoOne () {
        System.out.println("Please enter the name of the CD you'd like to access: ");
        String cdName = stdIn.readLine().toLowerCase(); 
        if (subChoice ==1) {
            int found = Collections.binarySearch(cds, new CD (cdName, 0), new compareCD());
            if (found < 0)
                System.out.println("That file was not found, was there a typo?");
            else 
                cds.get(found).listSongs();   
            }
    }
    
    public static int displayMenu (int menuNum, BufferedReader stdIn) throws IOException {
    	if (menuNum == 0) {
	        System.out.println ("----------  MAIN MENU  -----------");
	        System.out.println ("1) Accessing your list of CDs");
	        System.out.println ("2) Accessing within a particular CD");
	        System.out.println ("3) Exit");
	        System.out.println ("----------------------------------");
	    }
	    else if (menuNum == 1) {    
	        System.out.println ("\n---------  SUB-MENU #1  ----------");
	        System.out.println ("1) Display all of your CDs"); // Just the CD titles, numbered in order
	        System.out.println ("2) Add a CD"); // Adds a CD by reading from input file
    	    System.out.println ("3) Remove a CD");
    	    System.out.println ("4) Copy a CD");
    	    System.out.println ("5) Create a sub-CD");
    	    System.out.println ("6) List songs in common between two CDs");
    	    System.out.println ("7) Return back to main menu.");
    	    System.out.println ("----------------------------------");
    	}
    	else {
    	    System.out.println ("\n---------  SUB-MENU #2  ----------");
    	    System.out.println ("1) Display all songs (in the last sorted order) ");
    	    System.out.println ("2) Display info on a particular song ");
    	    System.out.println ("3) Add song");
    	    System.out.println ("4) Remove Song (4 options)");
    	    System.out.println ("5) Sort songs (3 options)");
    	    System.out.println ("6) Return back to main menu");
    	    System.out.println ("----------------------------------");
    	}
	    System.out.print ("\n\nPlease enter your choice:  ");
        
	    try {
            int choice = Integer.parseInt (stdIn.readLine ());
            if (menuNum == 0 && choice>3)
                throw new NumberFormatException ();
            else if (menuNum ==1 && choice > 7)
                throw new NumberFormatException ();
            else if (choice >6)
                throw new NumberFormatException ();
            return choice;
        }
        catch (NumberFormatException e) {
            System.out.println("\nInvalid Input, try again...");
            return displayMenu (menuNum, stdIn);
        }
        catch (Exception e) {
            System.out.println("hehe, I don't know how this happened");
            return -1;
        }
    }
    
    public static void importCDs (String fileName) {
        try {
            BufferedReader inp = new BufferedReader (new FileReader (fileName));
            String cdName = inp.readLine();
            int numSongs = Integer.parseInt(inp.readLine());
            CD cd = new CD (cdName, numSongs);
            cds.add(cd);    //adds cd to arrayList
            
            for (int i = 0; i < numSongs; i++) {            
                String title = inp.readLine();
                String name = inp.readLine();
                String genre = inp.readLine();
                int rating = Integer.parseInt(inp.readLine());
                String length = inp.readLine();
                cd.addSong(title, name, genre, rating, length);
            }
            
            Collections.sort(cds, new compareCD());
        }
        catch (FileNotFoundException e) {
            System.out.println("That file was not found, was there a typo?");
        }
        catch (Exception e) {
            System.out.println("Something bad went wrong, weally bad...");
        }
    }
}