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
        songs.add(new Song(title, name, genre, rating, length));
    }
    
    public int cdLength () {    
        return -1;
    }
    
    public void commonSongs () {    }
    
    public void listSongs () {        //this is done
        for (int i = 0; i < songs.size(); i ++) 
            System.out.println(songs.get(i));
    }
    
    public void removeSong (String title, int num) {
    
    }
    
    public String toString () {
        return title;
    }
    
    public String getTitle () {
        return title;
    }
    
    public int getNumSongs () {
        return numSongs;
    }
    
    public int calcTime () {
        int tot = 0;
        String time = ""
        for (int i = 0; i <songs.size(); i++) {
            
        }
    }
}

class compareCD implements Comparator<CD> {      //comparator to organise by title
    public int compare (CD m, CD m1){
        return m.getTitle().compareToIgnoreCase(m1.getTitle());
    }
}