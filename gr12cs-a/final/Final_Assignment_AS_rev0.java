import java.io.*;
import java.util.*;
import java.net.*;

public class Final_Assignment_AS_rev0 {
    public static void main (String [] args) {
        String url = "https://www.google.ca/maps";
        StringBuilder raw = new StringBuilder (getHTML (url));
        raw = removeIrr (raw);
        
        Saver autoSave = new Saver ();
        autoSave.start();
    }
    
    private static StringBuilder removeIrr (StringBuilder html) {
        //https://www.w3schools.com/html/html_scripts.asp
        //DO NOT REMOVE
        //    - <title> flags
        //    - 
        //REMOVE
        //    - JS/scripts
    }
    
    public static StringBuilder getHTML (String url) {
        StringBuilder html = new StringBuilder();
        try {
            URL web = new URL(url);
            URLConnection connection = web.openConnection();
            connection.connect();

            BufferedReader in = new BufferedReader(new InputStreamReader(web.openStream()));
            String line = "";
            while ((line = in.readLine()) != null) {
                html.append (line);
            }
        } 
        catch (MalformedURLException e) { 
            System.out.println ("URL format invalid, please try again");
        } 
        catch (IOException e) {   
            System.out.println ("Could not estalish a conenction, do you have internet connectivity?");
        }  
        
        if (html.substring(0, 15).toString().equals("<!DOCTYPE html>"))
            return html;            
        else 
            return "Website linked is not a valid HTML document, cannot search";
    }
}