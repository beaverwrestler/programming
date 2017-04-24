/* Name: Artin S.   
 * Due Date: Today
 * Assignment: the one with the cd's
*/ 

import java.io.*;
import java.util.*;

public class Driver {
    private static ArrayList <CD> cds = new ArrayList();
    private static ArrayList <String> cdEnglish = new ArrayList();
    
    public static void main (String[] args) throws IOException {
    	BufferedReader stdIn = new BufferedReader (new InputStreamReader (System.in));
        importCDs("Millenium");
        importCDs("Wannabe");
        String contin;
        int cdNum = -1;
        
        do {        
        	int mainChoice, subChoice;
            contin = "";
        	mainChoice = displayMenu (0, stdIn);
            
        	if (mainChoice == 1)
        	    subChoice = displayMenu (1, stdIn);
        	else if (mainChoice == 2)
        	    subChoice = displayMenu (2, stdIn);
            else 
                break;
            
            //sub menu 1
            if (mainChoice == 1) {
                if (subChoice == 1)        //this is also fine
                    listCDs(); 
                else if (subChoice == 2) {     //this doesn't work
                    //cdName =  findCD(stdIn);    //shows number of songs, overall time, and the name of the CD
                    System.out.println();
                    //cdName = "";
                }
                else if (subChoice == 3) {        //this is fine, no changes for index
                    while (true) {
                        System.out.println("Please enter the name of the CD you'd like to load into memory (exit to cancel): ");
                        String name = stdIn.readLine();
                        if (name.equalsIgnoreCase("exit"))
                            break;
                        importCDs(name);    
                    }   
                }
                else if (subChoice == 4) {    }
                else if (subChoice == 5) {    }
                else if (subChoice == 6) {    }
                else if (subChoice == 7) {    }
                else if (subChoice == 8) { 
                    displayMenu(0, stdIn);        //this is broken
                }
            }
        
            //sub menu 2
            else if (mainChoice == 2) {
               cdNum = findCD(stdIn);
               if (subChoice ==1) {        //this is fine, it works with indexes
                   cds.get(cdNum).listSongs();   
                   cdNum = -1;
               }
               else if (subChoice == 2) {    }
               else if (subChoice == 3) {    }
               else if (subChoice == 4) {    }
               else if (subChoice == 5) {    }
               else if (subChoice == 6) {
                   displayMenu(0, stdIn);        //this is broken
               }            
            }
                      
            while (!contin.equals("y") && !contin.equals("yes") && !contin.equals("n") && !contin.equals("no")) {
                System.out.print ("\nWould you like to continue? (y/n): ");
                contin = stdIn.readLine().toLowerCase();
            }
        } while (contin.equals("y") || contin.equals("yes"));
        stdIn.close();
        System.out.println("Bye!");
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
            System.out.println ("2) Display information on a particular CD"); // Just the CD titles, numbered in order
	        System.out.println ("3) Add a CD"); // Adds a CD by reading from input file
    	    System.out.println ("4) Remove a CD");
    	    System.out.println ("5) Copy a CD");
    	    System.out.println ("6) Create a sub-CD");
    	    System.out.println ("7) List songs in common between two CDs");
    	    System.out.println ("8) Return back to main menu.");
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
	    System.out.print ("\nPlease enter your choice:  ");
        
	    try {
            int choice = Integer.parseInt (stdIn.readLine ());
            if (menuNum == 0 && choice>3)
                throw new NumberFormatException ();
            else if (menuNum ==1 && choice > 8)
                throw new NumberFormatException ();
            else if (choice >6 && menuNum!=1)
                throw new NumberFormatException ();
            else if (choice<1)
                throw new NumberFormatException();
            return choice;
        }
        catch (NumberFormatException e) {
            System.out.println("\nInvalid Input, try again...");
            return displayMenu (menuNum, stdIn);
        }
        catch (Exception e) {
            System.out.println("Something went bad, weally bad...");
            return -1;
        }
    }
    
    private static void importCDs (String fileName) {
        if (!fileName.contains (".txt"))
            fileName += ".txt";
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
            inp.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("That file was not found, was there a typo?");
        }
        catch (Exception e) {
            System.out.println("Something went bad, weally bad...");
        }
    }
    
    public static int findCD (BufferedReader stdIn) throws IOException {
        int cdName = -1;
        listCDs();
        System.out.println();
        while (cdName<0) {
            System.out.println("Please enter the number of the CD you'd like to access (0 to exit): ");
            try {
                cdName = Integer.parseInt(stdIn.readLine());
                if (cdName > cds.size())
                    throw new IOException ();
                cdName -= 1;
            }
            catch (IOException e) {
                cdName = -1;
            }
        }
        return cdName;
    }
    
    private static void listCDs () {
        if (cds.size() == 0)
            System.out.println("There are currently no CDs loaded...");
        else {
            System.out.println("\nCDs currently in memory: ");
            for (int i = 0; i < cds.size(); i ++) 
                System.out.println((i+1) + ") " + cds.get(i));
        } 
    }
}