import java.util.Comparator;

/*
Name: Artin S.
Submission: Assignment #3 (April 7-ish, 2017)
Description: Objects are made from this and data is stored in instances of this class, implements comparator
 */

public class Movie {
    private String title;       //vars for data storage
    private String category;
    private double rating;

    //constructor
    public Movie (String title, String category, double rating) {
        if (rating>100)     //if the data is too big/small
            this.rating = 100;
        else if (rating <0)
            this.rating = 0;
        else
            this.rating = rating;       //setting data
        this.title = title;
        this.category = category;
    }
    
    public String toString () {     //for printing
        return "Movie title: " + title + "\nGenre:  " + category + "\nRating: " + rating + "%";
    }

    public String getTitle () {     //returns title
        return title;
    }

    public String getCategory () {  //returns category
        return  category;
    }

    public double getRating () {    //returns rating
        return rating;
    }
}

class Compareor implements Comparator<Movie> {      //comparator to organise by title
    public int compare (Movie m, Movie m1){
        return m.getTitle().compareToIgnoreCase(m1.getTitle());
    }
}

class Gcomp implements Comparator <Movie> {     //comparator to organise by genre
    public int compare (Movie m, Movie m1) {
        return m.getCategory().compareToIgnoreCase(m1.getCategory());
    }
}

class Rcomp implements Comparator <Movie> {     //comparator to organise by rating
    public int compare (Movie m, Movie m1) {
        return (int)( m1.getRating() - (m.getRating()));
    }
}