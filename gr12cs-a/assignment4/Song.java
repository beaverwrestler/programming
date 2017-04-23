public class Song {
    private String title, name, genre;    //variables
    private double rating;
    Time duration;

    public Song (String title, String name, String genre, double rating, Time duration) {        //constructor
        this.title = title;
        this.name = name;
        this.genre = genre;
        this.rating = rating;
        this.duration = duration;
    }
    
    public String toString () {
        return title;
    }
    
    public String getDurationCOlon () {
        return duration.getTimeColon();
    }
}