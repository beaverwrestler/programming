import java.util.*;

public class CD {
    private ArrayList <Song> songs = new ArrayList();
    private String title;
    private int numSongs;

    public CD (String title, int numSongs) {    //cosntructor
        this.title = title;
        this.numSongs = numSongs;
    }
    
    public void addSong (String title, String name, String genre, int rating, String length) {
        Time duration = new Time (length);
        songs.add(new Song(title, name, genre, rating, duration));
    }
    
    public int cdLength () {    
        return -1;
    }
    
    public void commonSongs () {    }
    
    public void listSongs () {    }
    
    public void removeSong (String title, int num) {    }
}