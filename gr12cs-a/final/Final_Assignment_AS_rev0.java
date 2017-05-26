import java.io.*;
import java.util.*;
import java.net.*;

public class Final_Assignment_AS_rev0 {
    public static void main (String [] args) {
        System.out.println(getHTML ("https://www.google.ca/maps"));
    }
    
    public static String getHTML (String url) {
        StringBuilder html = new StringBuilder();
        try {
            URL web = new URL(url);
            URLConnection connection = web.openConnection();
            connection.connect();

            BufferedReader in = new BufferedReader(new InputStreamReader(web.openStream()));
            String line = "";
            while ((line = in.readLine()) != null) {
                //put code to remove all java script and such here
                html.append (line);
            }
        } 
        catch (MalformedURLException e) { 
            System.out.println ("URL format invalid, please try again");
        } 
        catch (IOException e) {   
            System.out.println ("Could not estalish a conenction, do you have internet connectivity?");
        } 
        return html.toString();            
    }
}