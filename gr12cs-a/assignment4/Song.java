public class Song {
    private String title, name, genre;    //variables
    private int rating;
    Time duration;

    public Song (String title, String name, String genre, int rating, Time duration) {        //constructor
        this.title = title;
        this.name = name;
        this.genre = genre;
        this.rating = rating;
        this.duration = duration;
    }
    
    public String toString () {
        return title;
    }
}