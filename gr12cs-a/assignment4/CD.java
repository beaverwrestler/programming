/* Name: Artin S.
 * Due Date: Monday, May 1, 2017
 * Description: Instances are made of this class to store info related to each CD, there are multiple getter/setter methods,
 *              methods to update time and manipulate arraylists
*/

import java.util.*;

public class CD {
    private ArrayList <Song> songs = new ArrayList();   //arrayList of songs
    private String title;
    private int numSongs;
    private Time duration;


    public CD (String title, int numSongs) {    //constructor
        this.title = title;
        this.numSongs = numSongs;
        duration = new Time (0);
    }

    //makes a new object and adds it to the arrayList (with mm:ss as the parameter for the time object)
    public void addSong (String title, String name, String genre, double rating, String length, boolean add) {
        songs.add(new Song(title, name, genre, rating, new Time (length)));
        if (add)
            numSongs++;
        calcTime();
    }

    //makes a new object and adds it to the arrayList (with seconds as the parameter for the time object)
    public void addSong (String title, String name, String genre, double rating, int length, boolean add) {
        songs.add(new Song(title, name, genre, rating, new Time (length)));
        if (add)
            numSongs++;
        calcTime();
    }

    public void listSongs () {        //prints a list of all songs in the arrayList
        System.out.println("\nSongs:");
        for (int i = 0; i < songs.size(); i ++) 
            System.out.println((i+1) + ") " + songs.get(i).getSongTitle());
        System.out.println();
    }
    
    public void removeSong (int num) {  //removes an indexed song from the arrayList, calculates time & reduces numSongs
        songs.remove(num);
        numSongs--;
        calcTime();
    }

    public void calcTime () {      //updates the overall time in the cd
        int counter = 0;
        for (int i = 0; i < songs.size(); i ++)
            counter += songs.get(i).getDurationSeconds();
        duration.updateTime(counter);
    }

    //gettter
    public String getTitle () {
        return title;
    }
    public String getTime () {
        return duration.getTimeColon();
    }
    public int getNumSongs () {
        return numSongs;
    }
    public String getSongInfo (int temp) {
        return "\nSong Title: " + songs.get(temp).getSongTitle() + "\nArtist: " + songs.get(temp).getArtist()
                + "\nGenre: " + songs.get(temp).getGenre() + "\nRating: " + songs.get(temp).getRating() +
                "\nDuration: " + songs.get(temp).getDurationColon() + "\n";
    }
    public ArrayList <Song> getArray () {
        return songs;
    }
    public ArrayList <Song> getSubArray (int start, int end) {      //makes a copy of the array but only within a certain range of indexes
        ArrayList <Song> tempSongs = new ArrayList <> (songs); //creates a copy
        for (int i = 0; i < start; i ++)
            tempSongs.remove(0);
        for (int i = (end-start+1); i < tempSongs.size();)      //i don't why this works
            tempSongs.remove((end-start+1));
        return tempSongs;
    }

    //setters
    public void setArray (ArrayList <Song> songs) {
        this.songs = songs;
    }
}