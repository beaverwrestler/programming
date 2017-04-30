/* Name: Artin S.   
 * Due Date: Monday, May 1, 2017
 * Assignment: the one with the cd's
*/ 

import java.io.*;
import java.util.*;

public class Driver {
    private static ArrayList <CD> cds = new ArrayList();

    public static void main (String[] args) throws IOException {
    	BufferedReader stdIn = new BufferedReader (new InputStreamReader (System.in));
        importCDs("Millenium");
        importCDs("Wannabe");
        int cdNum;
        int mainChoice = 0, subChoice = 0;

        while (true) {
            while(true) {
                if (mainChoice == 0)
                    mainChoice = displayMenu (0, stdIn, true);
                if (mainChoice == 1)
                    subChoice = displayMenu (1, stdIn, true);
                else if (mainChoice == 2)
                    subChoice = displayMenu (2, stdIn, true);
                else
                    break;

                //sub menu 1
                if (mainChoice == 1) {
                    if (subChoice == 1)        //working, tested
                        listCDs(true);
                    else if (subChoice == 2) {    //working, tested
                        int temp = findCD(stdIn, true, "access", true);
                        while (temp!=-1) {
                            System.out.print("\nCD Name: " +cds.get(temp).getTitle() + "\nNumber of Songs: " +
                                    cds.get(temp).getNumSongs() + "\nTotal Duration: " + cds.get(temp).getTime(temp) + "\n");
                            temp = findCD(stdIn, false, "access", true);
                        }
                    }
                    else if (subChoice == 3) {        //working, tested
                        while (true) {
                            System.out.print("\nPlease enter the name of the CD you'd like to load into memory ('exit' to cancel): ");
                            String name = stdIn.readLine();
                            if (name.equalsIgnoreCase("exit"))
                                break;
                            importCDs(name);
                        }
                    }
                    else if (subChoice == 4) {      //working, tested
                        int temp = findCD(stdIn, true, "remove", true);
                        while (true) {
                            if (temp==-1 )
                                break;
                            else
                                cds.remove(temp);
                            temp = findCD(stdIn, true, "remove", true);
                        }
                    }
                    else if (subChoice == 5) {      //working, tested
                        while (true) {
                            int temp = findCD(stdIn, true, "copy", true);
                            if (temp==-1)
                                break;
                            else {
                                String tempTitle = cds.get(temp).getTitle() + " - Copy";
                                int tempNumSongs = cds.get(temp).getNumSongs();
                                CD tempCD = new CD (tempTitle, tempNumSongs);
                                ArrayList <Song> tempSongs = new ArrayList<>(cds.get(temp).getArray());
                                tempCD.setArray(tempSongs);
                                tempCD.calcTime();
                                cds.add(tempCD);
                            }
                        }
                    }
                    else if (subChoice == 6) {      //working, tested
                        while (true) {
                            int temp = findCD(stdIn, true, "create a sub of", true);
                            if (temp==-1)
                                break;
                            else {
                                cds.get(temp).listSongs();
                                int one = getValidNumber(stdIn, "Starting song?: ", 1, cds.get(temp).getNumSongs())-1;
                                int two = 0;
                                while (two < one) {
                                    two = getValidNumber(stdIn, "Ending song?: ", 1, cds.get(temp).getNumSongs())-1;
                                    if (two< one)
                                        System.out.println("The ending CD must have a higher/equal number...");
                                }

                                String tempTitle = cds.get(temp).getTitle() + " - Sub CD";
                                CD tempCD = new CD (tempTitle, (two-one+1));
                                ArrayList <Song> tempSongs = new ArrayList<>(cds.get(temp).getSubArray(one, two));
                                tempCD.setArray(tempSongs);
                                tempCD.calcTime();
                                cds.add(tempCD);
                            }
                        }
                    }
                    /*LOOK-E HERE!!!

                    ARE CDs COMSIDERED TO BE THE SAME IF THEY HAVE ONLY THE SAME NAME OF DOES EVERYTHING HAVE TO BE THE SAME?

                     */
                    else if (subChoice == 7) {      //working, tested
                        int cdOne = -1, cdTwo = -1;
                        if (cds.size() == 1)
                            System.out.println("\nNot enough CDs to comapre, exiting...");
                        else if (cds.size() == 2) {
                            System.out.println("\nThere are only 2 CDs loaded, auto-comparing...");
                            cdOne = 0;
                            cdTwo = 1;
                        }
                        else {
                            cdOne = findCD(stdIn, true, "compare", true);
                            if (cdOne >-1)
                                cdTwo = findCD(stdIn, false, "compare", false);
                        }
                        if (cds.size() > 1 && (cdOne != -1 && cdTwo != -1)) {
                            ArrayList <Song> cdOneTempSongs = new ArrayList<>(cds.get(cdOne).getArray());
                            ArrayList <Song> cdTwoTempSongs = new ArrayList<>(cds.get(cdTwo).getArray());

                            Collections.sort (cdOneTempSongs, new compareSongTitle());
                            Collections.sort (cdTwoTempSongs, new compareSongTitle());
                            int counter = 1;
                            System.out.println("\nSong(s) in common: ");

                            for (int i = 0; i < cdOneTempSongs.size(); i ++) {
                                int temp = Collections.binarySearch(cdTwoTempSongs, new Song (cdOneTempSongs.get(i).getSongTitle(),
                                        "", "", 0.0, new Time (0)), new compareSongTitle());
                                if (temp > -1) {
                                    System.out.println(counter + ") " + cdOneTempSongs.get(i).getSongTitle());
                                    cdOneTempSongs.remove(i);
                                    counter++;
                                }
                            }
                        }
                    }
                    else if (subChoice == 8) {
                        mainChoice = 0;
                        break;
                    }
                    else if (subChoice == 9) {
                        mainChoice = 3;
                        break;
                    }
                }

                //sub menu 2
                else{
                    if (subChoice == 6) {       //working, tested
                        mainChoice = 0;
                        break;
                    }
                    else if (subChoice == 7) {
                        mainChoice = 3;
                        break;
                    }
                    cdNum = findCD(stdIn, true, "access", true);
                    if (cdNum != -1) {
                        if (subChoice == 1)         //working, tested
                            while (cdNum != -1) {
                                cds.get(cdNum).listSongs();
                                cdNum = findCD(stdIn, false, "access", false);
                            }
                        else if (subChoice == 2) {   //working, done
                            int temp1 = findSong(stdIn, true, cdNum);
                            while (temp1!=-1) {
                                System.out.println(cds.get(cdNum).getSongInfo(temp1));
                                temp1 = findSong(stdIn, false, cdNum);
                            }
                        }
                        else if (subChoice == 3) {
                            System.out.print("\nName?: ");
                            String tempName = stdIn.readLine();
                            System.out.print("Artist?: ");
                            String tempArtist = stdIn.readLine();
                            System.out.print("Genre?: ");
                            String tempGenre= stdIn.readLine();
                            boolean continu = false;
                            String time = "error";
                            double rating = 0.0;

                            while (!continu) {
                                System.out.print("Rating?: ");
                                String tempRating= stdIn.readLine();
                                try {    //checks to see if the rating is actually a #
                                    Double.parseDouble(tempRating);
                                    continu = true;
                                }
                                catch (NumberFormatException e) { continu = false; } //no bueno
                                if (continu) {
                                    rating = Double.parseDouble(tempRating);
                                    break;
                                }
                            }

                            continu = false;
                            while (!continu) {
                                System.out.print("Time? (mm:ss): ");
                                String tempTime= stdIn.readLine();

                                if (tempTime.contains(":")) {
                                    int tempFour = tempTime.indexOf(":");
                                    String one = tempTime.substring(0, tempFour);
                                    String two = tempTime.substring(tempFour+1);
                                    try {    //checks to see if the stuff before and after the colon are actually #'s
                                        Integer.parseInt(one);
                                        int secs = Integer.parseInt(two);
                                        if (secs>59)
                                            throw new NumberFormatException();
                                        continu = true;
                                    }
                                    catch (NumberFormatException e) { continu = false; } //no bueno
                                    if (continu) {
                                        time = tempTime;
                                        break;
                                    }
                                }
                            }
                            cds.get(cdNum).addSong(tempName, tempArtist, tempGenre, rating, time, true);
                        }
                        else if (subChoice == 4) {
                            int choice = getValidNumber(stdIn, "\nHow would you like to remove the " +
                                    "song by? 1) Number 2) Title 3) First Song 4) Last Song : ", 1, 4);
                            if (choice == 1) {
                                while (true) {
                                    cds.get(cdNum).listSongs();
                                    int choiceSong = getValidNumber(stdIn, "Pick the song you'd like to remove (0 to exit): "
                                            , 0, cds.get(cdNum).getNumSongs())-1;
                                    if (choiceSong == -1)
                                        break;
                                    cds.get(cdNum).removeSong(choiceSong);
                                }

                            }
                            else if (choice == 2) {
                                Collections.sort (cds.get(cdNum).getArray(), new compareSongTitle());
                                while (true) {
                                    cds.get(cdNum).listSongs();
                                    System.out.print("Enter the name of the song you'd like to remove ('exit' to cancel): ");
                                    String name = stdIn.readLine();
                                    if (name.equalsIgnoreCase("exit"))
                                        break;
                                    int numSong = 0;
                                    while (numSong != -1) {
                                        numSong = Collections.binarySearch(cds.get(cdNum).getArray(), new
                                                Song(name, "", "", 0.0, new Time(0)), new compareSongTitle());
                                        if (numSong < 0)
                                            break;
                                        else
                                            cds.get(cdNum).removeSong(numSong);
                                    }
                                }
                            }
                            else if (choice == 3)
                                cds.get(cdNum).removeSong(0);
                            else if (choice == 4)
                                cds.get(cdNum).removeSong(cds.get(cdNum).getNumSongs()-1);
                        }
                        else if (subChoice == 5) {      //this probably works
                            int choice = getValidNumber(stdIn, "\nHow would you like to sort the " +
                                    "songs? 1) Title 2) Artist 3) Time (low -> high) 4) Time (high -> low) : ", 1, 4);
                            if (choice == 1)
                                Collections.sort (cds.get(cdNum).getArray(), new compareSongTitle());
                            else if (choice == 2)
                                Collections.sort (cds.get(cdNum).getArray(), new compareSongArtist());
                            else if (choice == 3)
                                Collections.sort (cds.get(cdNum).getArray(), new compareSongTimeLH());
                            else if (choice == 4)
                                Collections.sort (cds.get(cdNum).getArray(), new compareSongTimeHL());
                            cds.get(cdNum).listSongs();
                        }
                    }
                }
            }
            if (mainChoice == 3)
                break;
        }
        stdIn.close();
        System.out.println("\nBye!");
    }
    
    private static int displayMenu (int menuNum, BufferedReader stdIn, boolean showMenu) throws IOException {
        if (showMenu) {
            if (menuNum == 0) {
                System.out.println("\n----------  MAIN MENU  -----------");
                System.out.println("1) Accessing your list of CDs");
                System.out.println("2) Accessing within a particular CD");
                System.out.println("3) Exit program");
                System.out.println("----------------------------------");
            } else if (menuNum == 1) {
                System.out.println("\n---------  SUB-MENU #1  ----------");
                System.out.println("1) Display all of your CDs"); // Just the CD titles, numbered in order
                System.out.println("2) Display information on a particular CD"); // Just the CD titles, numbered in order
                System.out.println("3) Add a CD"); // Adds a CD by reading from input file
                System.out.println("4) Remove a CD");
                System.out.println("5) Copy a CD");
                System.out.println("6) Create a Sub-CD");
                System.out.println("7) List songs in common between two CDs");
                System.out.println("8) Return back to main menu");
                System.out.println("9) Exit program");
                System.out.println("----------------------------------");
            } else {
                System.out.println("\n---------  SUB-MENU #2  ----------");
                System.out.println("1) Display all songs (in the last sorted order) ");
                System.out.println("2) Display info on a particular song ");
                System.out.println("3) Add song");
                System.out.println("4) Remove song (4 options)");
                System.out.println("5) Sort songs (4 options)");
                System.out.println("6) Return back to main menu");
                System.out.println("7) Exit program");
                System.out.println("----------------------------------");
            }
            System.out.println();
        }

        System.out.print("Please enter your choice:  ");

	    try {
            int choice = Integer.parseInt (stdIn.readLine ());
            if (menuNum == 0 && choice>3)
                throw new NumberFormatException ();
            else if (menuNum ==1 && choice > 9)
                throw new NumberFormatException ();
            else if (choice >7 && menuNum!=1)
                throw new NumberFormatException ();
            else if (choice<1)
                throw new NumberFormatException();
            return choice;
        }
        catch (NumberFormatException e) {
            return displayMenu (menuNum, stdIn, false);
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
                cd.addSong(title, name, genre, rating, length, false);
            }
            inp.close();
            System.out.print("\tFile '" + fileName +"' successfully imported");
        }
        catch (FileNotFoundException e) {
            System.out.print("\tFile not found, was there a typo?");
        }
        catch (Exception e) {
            System.out.println("Something went bad, weally bad...");
        }
    }

    private static int getValidNumber (BufferedReader stdIn, String question, int min, int max) throws IOException {
        boolean continu = false;
        int num = -1;

        while (!continu) {
            System.out.print(question);
            String tempRating= stdIn.readLine();
            try {    //checks to see if the num is actually a num
                int tempNum = Integer.parseInt(tempRating);
                if (tempNum<min)
                    throw new NumberFormatException();
                else if (tempNum>max)
                    throw new NumberFormatException();
                continu = true;
            }
            catch (NumberFormatException e) { continu = false; } //no bueno
            if (continu) {
                num = Integer.parseInt(tempRating);
                break;
            }
        }
        return num;
    }

    private static int findSong (BufferedReader stdIn, boolean a, int cdNum) throws IOException {
        int songName = -1;
        if (a)
            cds.get(cdNum).listSongs();
        while (songName<0) {
            System.out.print("Please enter the number of the song you'd like to access (0 to exit): ");
            try {
                songName = Integer.parseInt(stdIn.readLine());
                if (songName > cds.get(cdNum).getNumSongs())
                    throw new IOException ();
                else if (songName == 0)
                    return -1;
                songName -= 1;
            }
            catch (Exception e) {
                songName = -1;
            }
        }
        return songName;
    }

    private static int findCD (BufferedReader stdIn, boolean a, String action, boolean enter) throws IOException {
        int cdName = -1;
        if (a)
            listCDs(false);
        if (enter)
            System.out.println();
        while (cdName<0 && cds.size() > 0) {
            System.out.print("Please enter the number of the CD you'd like to " + action  + " (0 to exit): ");
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
    
    private static void listCDs (boolean enter) {
        if (cds.size() !=0) {
            System.out.println("\nCDs currently in memory: ");
            for (int i = 0; i < cds.size(); i++)
                System.out.println((i + 1) + ") " + cds.get(i).getTitle());
        }
        else
            System.out.print("\nThere are no CDs left...");
        if (enter)
            System.out.println();
    }
}