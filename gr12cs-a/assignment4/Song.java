public class Song {
    private String title, artist, genre;    //variables
    private double rating;
    Time duration;

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
}
