import java.util.*;

public class CD {
    private ArrayList <Song> songs = new ArrayList();
    private String title;
    private int numSongs;

    public CD (String title, int numSongs) {    //cosntructor
        this.title = title;
        this.numSongs = numSongs;
    }
    
    public int cdLength () {    
        return -1;
    }
    
    public void commonSongs () {    }
    
    public void listSongs () {    }
    
    public void removeSong (String title, int num) {    }
}