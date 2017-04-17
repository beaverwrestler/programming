public class Song {
    private String title, name, genre;    //variables
    private int rating;
    Time totalTime;

    public Song (String title, String name, String genre, int rating, Object time) {        //constructor
        this.title = title;
        this.name = name;
        this.genre = genre;
        this.rating = rating;
        totalTime = time;
    }
}