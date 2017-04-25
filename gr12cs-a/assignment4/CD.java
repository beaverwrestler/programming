import java.util.*;

public class CD {
    private ArrayList <Song> songs = new ArrayList();
    private String title;
    private int numSongs;
    Time duration;


    public CD (String title, int numSongs) {    //cosntructor
        this.title = title;
        this.numSongs = numSongs;
    }
    
    public void addSong (String title, String name, String genre, int rating, String length) {
        duration = new Time (length);
        songs.add(new Song(title, name, genre, rating, duration));
        calcTime();
    }
    
    public void commonSongs () {    }
    
    public void listSongs () {        //this is done
        System.out.println("\nSongs:");
        for (int i = 0; i < songs.size(); i ++) 
            System.out.println((i+1) + ") " + songs.get(i).getSongTitle());
    }
    
    public void removeSong (String title, int num) {
        calcTime();
    }

    private int calcTime () {
        int tot = 0;
        String time = "";
        for (int i = 0; i <songs.size(); i++) {

        }
        return -1;
    }

    //gettter
    public String getTitle () {
        return title;
    }
    public String getTime () {
        return "blah";
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