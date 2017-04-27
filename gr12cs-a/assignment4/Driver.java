/* Name: Artin S.   
 * Due Date: Today
 * Assignment: the one with the cd's
*/ 

//YOU WERE WORKING ON MENU 1 SUB 2 TRYING TO GET THE TIMES RIGHT, AS OF NOW, I DON'T THINK MOD WORKS

import java.io.*;
import java.util.*;

public class Driver {
    private static ArrayList <CD> cds = new ArrayList();
    private static ArrayList <String> cdEnglish = new ArrayList();
    
    public static void main (String[] args) throws IOException {
    	BufferedReader stdIn = new BufferedReader (new InputStreamReader (System.in));
        importCDs("Millenium");
        importCDs("Wannabe");
        String contin = "y";
        int cdNum = -1;
        boolean show = false;
        
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
                if (subChoice == 1)        //working, done
                    listCDs();
                else if (subChoice == 2) {    //working, not done
                    int temp = findCD(stdIn, true);
                    while (temp!=-1) {
                        System.out.println("\nCD Name: " +cds.get(temp).getTitle() + "\nNumber of Songs: " +
                                cds.get(temp).getNumSongs() + "\nTotal Duration: " + cds.get(temp).getTime(temp));
                        temp = findCD(stdIn, false);
                    }
                }
                else if (subChoice == 3) {        //working
                    while (true) {
                        System.out.print("\nPlease enter the name of the CD you'd like to load into memory ('exit' to cancel): ");
                        String name = stdIn.readLine();
                        if (name.equalsIgnoreCase("exit"))
                            break;
                        importCDs(name);    
                    }   
                }
                else if (subChoice == 4) {      //working
                    while (true) {
                        int temp = findCD(stdIn, true);
                        if (temp==-1)
                            break;
                        else
                            cds.remove(temp);
                    }
                }
                else if (subChoice == 5) {
                    while (true) {
                        int temp = findCD(stdIn, true);
                        if (temp==-1)
                            break;
                        else {
                            //cds.add(copyCD(temp));
                        }
                    }
                }
                else if (subChoice == 6) {    }
                else if (subChoice == 7) {    }
                else if (subChoice == 8) { 
                    mainChoice = 0;
                    subChoice = 0;
                    show = true;
                    displayMenu(0, stdIn);        //this is broken af
                }
            }
        
            //sub menu 2
            else {
               cdNum = findCD(stdIn, true);
               if (cdNum !=-1) {
                   if (subChoice ==1) {        //this is fine, it works with indexes
                       cds.get(cdNum).listSongs();
                       cdNum = -1;
                   }
                   else if (subChoice == 2) {    }
                   else if (subChoice == 3) {    }
                   else if (subChoice == 4) {    }
                   else if (subChoice == 5) {    }
                   else if (subChoice == 6) {
                       displayMenu(0, stdIn);        //this is broken af
                   }
               }
            }
                      
            while (!contin.equals("y") && !contin.equals("yes") && !contin.equals("n") && !contin.equals("no") && !show) {
                System.out.print ("\nWould you like to continue? (y/n): ");
                contin = stdIn.readLine().toLowerCase();
            }
        } while (contin.equals("y") || contin.equals("yes"));
        stdIn.close();
        System.out.println("Bye!");
    }
    
    private static int displayMenu (int menuNum, BufferedReader stdIn) throws IOException {
    	if (menuNum == 0) {
	        System.out.println ("\n----------  MAIN MENU  -----------");
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
            System.out.println("Invalid Input, try again...");
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
            System.out.print("That file was not found, was there a typo?");
        }
        catch (Exception e) {
            System.out.println("Something went bad, weally bad...");
        }
    }

    private static void copyCD (int num) {
        String tempTitle = cds.get(num).getTitle() + " - Copy";
        int tempNumSongs = cds.get(num).getNumSongs();
        CD temp = new CD (tempTitle, tempNumSongs);
        //Song tempSong = new Song(cds.get(num).getTitle());

        for (int i = 0; i < tempNumSongs; i++) {
            //blah
        }

        //make return type CD and add code to copy songs and title and shit
    }
    
    private static int findCD (BufferedReader stdIn, boolean a) throws IOException {
        int cdName = -1;
        if (a)
            listCDs();
        System.out.println();
        while (cdName<0) {
            System.out.print("Please enter the number of the CD you'd like to access (0 to exit): ");
            try {
                cdName = Integer.parseInt(stdIn.readLine());
                if (cdName > cds.size())
                    throw new IOException ();
                else if (cdName == 0)
                    return -1;
                cdName -= 1;
            }
            catch (Exception e) {
                cdName = -1;
            }
        }
        return cdName;
    }
    
    private static void listCDs () {
        if (cds.size() !=0) {
            System.out.println("\nCDs currently in memory: ");
            for (int i = 0; i < cds.size(); i++)
                System.out.println((i + 1) + ") " + cds.get(i).getTitle());
        }
    }
}