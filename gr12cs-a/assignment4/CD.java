import java.util.*;

public class CD {
    private ArrayList <Song> songs = new ArrayList();
    private String title;
    private int numSongs;
    private Time duration;


    public CD (String title, int numSongs) {    //constructor
        this.title = title;
        this.numSongs = numSongs;
        duration = new Time (0);
    }
    
    public void addSong (String title, String name, String genre, int rating, String length) {
        songs.add(new Song(title, name, genre, rating, new Time (length)));
        calcTime();
    }
    
    public void commonSongs () {    }
    
    public void listSongs () {        //this is done, working
        System.out.println("\nSongs:");
        for (int i = 0; i < songs.size(); i ++) 
            System.out.println((i+1) + ") " + songs.get(i).getSongTitle());
    }
    
    public void removeSong (String title, int num) {
        calcTime();
    }

    private void calcTime () {      //hopefully this works
        int counter = 0;
        for (int i = 0; i < songs.size(); i ++)
            counter += songs.get(i).getDurationSeconds();
        duration.updateTime(counter);
    }

    //gettter
    public String getTitle () {
        return title;
    }
    public String getTime (int num) {
        return duration.getTimeColon();
    }
    public int getNumSongs () {
        return numSongs;
    }
}

class compareCD implements Comparator<CD> {      //comparator to organise by title
    public int compare (CD m, CD m1){
        return m.getTitle().compareToIgnoreCase(m1.getTitle());
    }
}