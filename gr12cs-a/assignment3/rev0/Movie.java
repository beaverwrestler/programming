public class Movie {
    
    private String title;
    private String category;
    private double rating;
    
    public Movie (String title, String category, double rating) {
        this.title = title;
        this.category = category;
        this.rating = rating;    
    }
    
    public String toString ()
    {
        return title + " " + category + " " + rating;
    }
}