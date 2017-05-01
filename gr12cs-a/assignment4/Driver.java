/* Name: Artin S.   
 * Due Date: Monday, May 1, 2017
 * Description: "The one with the CDs". i made some changes to the original menu design, you can now exit from any of the main
 *              menu's and most of the sub options will loop to let you use different CDs. Added some other features like
  *             auto-search for 1-7, multiple time entry methods for 2-3, and probably more that I forgot. See internal comments.
*/ 

import java.io.*;
import java.util.*;

public class Driver {
    private static ArrayList <CD> cds = new ArrayList();

    //Parameters: String [] args
    //Return: void
    //Function: main method, this is where all the IO and metaphorical magic happens
    public static void main (String[] args) throws IOException {
    	BufferedReader stdIn = new BufferedReader (new InputStreamReader (System.in));
        int cdNum;
        int mainChoice = 0, subChoice;

        while (true) {
            while(true) {
                if (mainChoice == 0)
                    mainChoice = displayMenu (0, stdIn, true);  //display menu 1/get mainChoice
                if (mainChoice == 1)
                    subChoice = displayMenu (1, stdIn, true);       //get subChoice for menu 1
                else if (mainChoice == 2)
                    subChoice = displayMenu (2, stdIn, true);       //get subChoice for menu 2
                else
                    break;  //exit program

                //sub menu 1
                if (mainChoice == 1) {
                    if (subChoice == 1)        //1-1
                        listCDs(true);      //prints a list of all CDs
                    else if (subChoice == 2) {    //1-2
                        int temp = findCD(stdIn, true, "access", true);     //user inp for CD
                        while (temp!=-1) {
                            System.out.print("\nCD Name: " +cds.get(temp).getTitle() + "\nNumber of Songs: " +
                                    cds.get(temp).getNumSongs() + "\nTotal Duration: " + cds.get(temp).getTime() + "\n");     //shows info on CD
                            temp = findCD(stdIn, false, "access", true);
                        }
                    }
                    else if (subChoice == 3) {        //1-3
                        while (true) {
                            System.out.print("\nPlease enter the name of the CD you'd like to load into memory ('exit' to cancel): ");      //usr inp for file name
                            String name = stdIn.readLine();
                            if (name.equalsIgnoreCase("exit"))  //exit case
                                break;
                            importCDs(name);    //calls importCD method to get file and make objects
                        }
                    }
                    else if (subChoice == 4) {      //1-4
                        int temp = findCD(stdIn, true, "remove", true);     //asks user for CD
                        while (true) {
                            if (temp==-1 )  //exit case
                                break;
                            else
                                cds.remove(temp);       //removes index song
                            temp = findCD(stdIn, true, "remove", true);      //asks again
                        }
                    }
                    else if (subChoice == 5) {      //1-5
                        while (true) {
                            int temp = findCD(stdIn, true, "copy", true);       //asks user for CD
                            if (temp==-1)
                                break;
                            else {
                                String tempTitle = cds.get(temp).getTitle() + " - Copy";    //crates new title and gets constructor parameters
                                int tempNumSongs = cds.get(temp).getNumSongs();
                                CD tempCD = new CD (tempTitle, tempNumSongs);
                                ArrayList <Song> tempSongs = new ArrayList<>(cds.get(temp).getArray());     //copies songs arrayList
                                tempCD.setArray(tempSongs);
                                tempCD.calcTime();
                                cds.add(tempCD);        //adds new CD
                            }
                        }
                    }
                    else if (subChoice == 6) {      //1-6
                        while (true) {
                            int temp = findCD(stdIn, true, "create a sub of", true);        //which Cd to create a sub of
                            if (temp==-1)
                                break;
                            else {
                                cds.get(temp).listSongs();      //lists songs of said Cd
                                int one = getValidNumber(stdIn, "Starting song?: ", 1, cds.get(temp).getNumSongs())-1;      //gets starting and ending index
                                int two = 0;
                                while (two < one) {
                                    two = getValidNumber(stdIn, "Ending song?: ", 1, cds.get(temp).getNumSongs())-1;
                                    if (two< one)
                                        System.out.println("The ending CD must have a higher/equal number...");     //error check
                                }

                                String tempTitle = cds.get(temp).getTitle() + " - Sub CD";
                                CD tempCD = new CD (tempTitle, (two-one+1));
                                ArrayList <Song> tempSongs = new ArrayList<>(cds.get(temp).getSubArray(one, two));      //creates sub songs array list and copies it
                                tempCD.setArray(tempSongs);     //sets array to new object
                                tempCD.calcTime();
                                cds.add(tempCD);    //adds object
                            }
                        }
                    }
                    else if (subChoice == 7) {     //1-7
                        int cdOne = -1, cdTwo = -1;
                        if (cds.size() == 1)
                            System.out.println("\nNot enough CDs to comapre, exiting...");  //exits if one cd
                        else if (cds.size() == 2) {
                            System.out.println("\nThere are only 2 CDs loaded, auto-comparing...");     //auto-compares if there are 2 cds
                            cdOne = 0;
                            cdTwo = 1;
                        }
                        else {
                            cdOne = findCD(stdIn, true, "compare", true);       //if there are more than 1/2, it asks the user which to compare
                            if (cdOne >-1)
                                cdTwo = findCD(stdIn, false, "compare", false);
                        }
                        if (cds.size() > 1 && (cdOne != -1 && cdTwo != -1)) {   //compares
                            ArrayList <Song> cdOneTempSongs = new ArrayList<>(cds.get(cdOne).getArray());       //creates arrayList copies
                            ArrayList <Song> cdTwoTempSongs = new ArrayList<>(cds.get(cdTwo).getArray());

                            Collections.sort (cdOneTempSongs, new compareSongTitle());  //sorts
                            Collections.sort (cdTwoTempSongs, new compareSongTitle());
                            int counter = 1;
                            System.out.println("\nSong(s) in common: ");

                            for (int i = 0; i < cdOneTempSongs.size(); i ++) {  //binary searches for each individual song, removes after search is done
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
                    else if (subChoice == 8) {      //exit case
                        mainChoice = 0;
                        break;
                    }
                    else if (subChoice == 9) {      //exit case
                        mainChoice = 3;
                        break;
                    }
                }

                //sub menu 2
                else{
                    if (subChoice == 6) {       //exit case
                        mainChoice = 0;
                        break;
                    }
                    else if (subChoice == 7) {  //exit case
                        mainChoice = 3;
                        break;
                    }
                    cdNum = findCD(stdIn, true, "access", true);        //user to choose which CD they want to manipulate
                    if (cdNum != -1) {
                        if (subChoice == 1)         //2-1
                            while (cdNum != -1) {
                                cds.get(cdNum).listSongs();     //prints all songs from CD
                                cdNum = findCD(stdIn, false, "access", false);
                            }
                        else if (subChoice == 2) {   //2-2
                            int temp1 = findSong(stdIn, true, cdNum);       //finds song
                            while (temp1!=-1) {
                                System.out.println(cds.get(cdNum).getSongInfo(temp1));  //gets info, prints
                                temp1 = findSong(stdIn, false, cdNum);
                            }
                        }
                        else if (subChoice == 3) {      //2-3
                            System.out.print("\nName?: ");      //user input for song info
                            String tempName = stdIn.readLine();
                            System.out.print("Artist?: ");
                            String tempArtist = stdIn.readLine();
                            System.out.print("Genre?: ");
                            String tempGenre= stdIn.readLine();
                            boolean continu = false;
                            String time = "error";
                            double rating = 0.0;

                            while (!continu) {      //looping until valid rating input (double)
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

                            int choiceTime = getValidNumber(stdIn, "\nHow would you like to specify the " +
                                    "time? 1) Seconds 2) Minute/Seconds : ", 1, 2);     //asking how the user wants to specify the time
                            int timeSeconds = 0;

                            if (choiceTime == 2) {      //if (mm:ss) format
                                continu = false;
                                while (!continu) {
                                    System.out.print("Time? (mm:ss): ");
                                    String tempTime= stdIn.readLine();

                                    if (tempTime.contains(":")) {       //checks for colon
                                        int tempFour = tempTime.indexOf(":");
                                        String one = tempTime.substring(0, tempFour);       //splits accordingly
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
                            }
                            else {
                                timeSeconds = getValidNumber(stdIn, "Time? (s): ", -1,-1);      //gets valid number for (s) format
                            }

                            if (choiceTime == 2)        //adds song according to whether the time input as colonFormat or seconds
                                cds.get(cdNum).addSong(tempName, tempArtist, tempGenre, rating, time, true);
                            else if (choiceTime == 1)
                                cds.get(cdNum).addSong(tempName, tempArtist, tempGenre, rating, timeSeconds, true);
                        }
                        else if (subChoice == 4) {      //2-4
                            int choice = getValidNumber(stdIn, "\nHow would you like to remove the " +
                                    "song by? 1) Number 2) Title 3) First Song 4) Last Song : ", 1, 4);     //valid choice
                            if (choice == 1) {
                                while (true) {  //by number, loops until no more songs are to be removed
                                    cds.get(cdNum).listSongs();
                                    int choiceSong = getValidNumber(stdIn, "Pick the song you'd like to remove (0 to exit): "
                                            , 0, cds.get(cdNum).getNumSongs())-1;
                                    if (choiceSong == -1)
                                        break;
                                    cds.get(cdNum).removeSong(choiceSong);
                                }

                            }
                            else if (choice == 2) {     //by title, gets input, sorts, binary search, loops until no more songs are to be removed
                                Collections.sort (cds.get(cdNum).getArray(), new compareSongTitle());
                                while (true) {
                                    cds.get(cdNum).listSongs();
                                    System.out.print("Enter the name of the song you'd like to remove ('exit' to cancel): ");
                                    String name = stdIn.readLine();
                                    if (name.equalsIgnoreCase("exit"))  //exit case
                                        break;
                                    int numSong = 0;
                                    while (numSong != -1) {
                                        numSong = Collections.binarySearch(cds.get(cdNum).getArray(), new
                                                Song(name, "", "", 0.0, new Time(0)), new compareSongTitle());      //binary search for name
                                        if (numSong < 0)
                                            break;      //not found
                                        else
                                            cds.get(cdNum).removeSong(numSong);
                                    }
                                }
                            }
                            else if (choice == 3)
                                cds.get(cdNum).removeSong(0);       //remove first song
                            else if (choice == 4)
                                cds.get(cdNum).removeSong(cds.get(cdNum).getNumSongs()-1); //remove last song
                        }
                        else if (subChoice == 5) {      //2-5
                            int choice = getValidNumber(stdIn, "\nHow would you like to sort the " +
                                    "songs? 1) Title 2) Artist 3) Time (low -> high) 4) Time (high -> low) : ", 1, 4);  //asks how to sort
                            if (choice == 1)
                                Collections.sort (cds.get(cdNum).getArray(), new compareSongTitle());   //option 1, 2...
                            else if (choice == 2)
                                Collections.sort (cds.get(cdNum).getArray(), new compareSongArtist());
                            else if (choice == 3)
                                Collections.sort (cds.get(cdNum).getArray(), new compareSongTimeLH());
                            else if (choice == 4)
                                Collections.sort (cds.get(cdNum).getArray(), new compareSongTimeHL());
                            cds.get(cdNum).listSongs(); //list songs after sorting
                        }
                    }
                }
            }
            if (mainChoice == 3)    //breaks out of second while (true) to exit program
                break;
        }
        stdIn.close();      //closing regards, closing input
        System.out.println("\nBye!");
    }

    //Parameters: menu number, bufferedReader, option to show menu
    //Return: int (menu/submenu choice)
    //Function: displays the menu and returns valid input for navigation
    private static int displayMenu (int menuNum, BufferedReader stdIn, boolean showMenu) throws IOException {
        if (showMenu) {     //text for menus
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
            int choice = Integer.parseInt (stdIn.readLine ());  //checks if actually a number
            if (menuNum == 0 && choice>3)
                throw new NumberFormatException ();     //checks for range
            else if (menuNum ==1 && choice > 9)
                throw new NumberFormatException ();
            else if (choice >7 && menuNum!=1)
                throw new NumberFormatException ();
            else if (choice<1)
                throw new NumberFormatException();
            return choice;
        }
        catch (NumberFormatException e) {
            return displayMenu (menuNum, stdIn, false);     //asks again if error
        }
    }

    //Parameters: file name
    //Return: void
    //Function: imports text files and creates CDs in its name
    private static void importCDs (String fileName) {
        if (!fileName.contains (".txt"))    //if user forgets to add ".txt"
            fileName += ".txt";
        try {
            BufferedReader inp = new BufferedReader (new FileReader (fileName));
            String cdName = inp.readLine();
            int numSongs = Integer.parseInt(inp.readLine());
            CD cd = new CD (cdName, numSongs);      //make object
            cds.add(cd);    //adds cd to arrayList
            
            for (int i = 0; i < numSongs; i++) {    //get data for each cd object
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
        catch (FileNotFoundException e) {   //catches if file not found
            System.out.print("\tFile not found, was there a typo?");
        }
        catch (Exception e) {       //in case something bad happens
            System.out.println("Something went bad, weally bad...");
        }
    }

    //Parameters: BuffereReader, string of text to print, range of values
    //Return: int
    //Function: gets a valid number input from the user, either within a certain range or not
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

    //Parameters: BuffereReader, boolean to print list of songs or not, index of CD
    //Return: int
    //Function: displays a list of songs in a certain cd and gets a valid input from user
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

    //Parameters: BuffereReader, boolean to print list of cds or not, string for verb in question, boolean for hitting enter (must... have... perfect... spacing)
    //Return: int
    //Function: returns the index of a cd from a list of printed cds
    private static int findCD (BufferedReader stdIn, boolean a, String action, boolean enter) throws IOException {
        int cdName = -1;
        if (a)
            listCDs(false);     //prints the list of cds if necessary
        if (enter)
            System.out.println();   //enter if necessary
        while (cdName<0 && cds.size() > 0) {
            System.out.print("Please enter the number of the CD you'd like to " + action  + " (0 to exit): ");
            try {
                cdName = Integer.parseInt(stdIn.readLine());    //tries to parseInt, crashes and loops if inp is not an int
                if (cdName > cds.size())        //makes sure it is in range
                    throw new IOException ();
                else if (cdName == 0)       //exit condition
                    return -1;
                cdName -= 1;
            }
            catch (Exception e) {
                cdName = -1;
            }
        }
        return cdName;  //returns the index once done
    }

    //Parameters: boolean for whether or not to hit enter (spacing...)
    //Return: void
    //Function: prints a list of songs in a certain cd
    private static void listCDs (boolean enter) {
        if (cds.size() !=0) {
            System.out.println("\nCDs currently in memory: ");
            for (int i = 0; i < cds.size(); i++)
                System.out.println((i + 1) + ") " + cds.get(i).getTitle());     //gets and prints cds
        }
        else
            System.out.print("\nThere are no CDs left...");     //if there are no cds
        if (enter)
            System.out.println();
    }
}