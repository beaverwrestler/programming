import java.util.Comparator;

public class Song {
    private String title, artist, genre;    //variables
    private double rating;
    private Time duration;

    public Song (String title, String artist, String genre, double rating, Time duration) {        //constructor
        this.title = title;
        this.artist = artist;
        this.genre = genre;
        this.rating = rating;
        this.duration = duration;
    }

    //getter methods
    public String getSongTitle () {
        return title;
    }
    public String getArtist () {
        return artist;
    }
    public String getGenre () {
        return genre;
    }
    public double getRating () {
        return rating;
    }
    public String getDurationColon () {
        return duration.getTimeColon();
    }
    public int getDurationSeconds () {
        String colonFormat = duration.getTimeColon();
        int colon = colonFormat.indexOf(":");
        int mins = Integer.parseInt(colonFormat.substring(0, colon));
        int secs = Integer.parseInt(colonFormat.substring(colon+1));
        return secs + (mins*60);
    }
}

class compareSongTitle implements Comparator <Song> {      //comparator to organise by title
    public int compare (Song s, Song s1){
        return s.getSongTitle().compareToIgnoreCase(s1.getSongTitle());
    }
}

class compareSongArtist implements Comparator <Song> {      //comparator to organise by artist
    public int compare (Song s, Song s1){
        return s.getArtist().compareToIgnoreCase(s1.getArtist());
    }
}

class compareSongTimeLH implements Comparator <Song> {      //comparator to organise by time
    public int compare (Song s, Song s1){
        return s.getDurationSeconds() - (s1.getDurationSeconds());
    }
}

class compareSongTimeHL implements Comparator <Song> {      //comparator to organise by time
    public int compare (Song s, Song s1){
        return s1.getDurationSeconds() - (s.getDurationSeconds());
    }
}