import java.io.*;
import java.util.*;
import java.net.*;

public class Final_Assignment_AS_rev0 {
    private static boolean valid = true;
    public static ArrayList <Citation> citations = new ArrayList <> ();
    
    public static void main (String [] args) throws IOException {
        String url = "https://www.thestar.com/news/world/2017/06/07/several-dead-in-attacks-on-iran-parliament-and-shrine.html";
        System.out.println("Started...");
        StringBuilder raw = new StringBuilder (getHTML (url));
        if (valid) {
            raw = removeIrr (raw);

            Saver autoSave = new Saver ();
            autoSave.start();
        }
        
        System.out.println("done");
    }
    
    private static StringBuilder removeIrr (StringBuilder website) throws IOException {
        String html = website.toString();
        //https://www.w3schools.com/html/html_scripts.asp
        //DO NOT REMOVE
        //    - <title> flags
        //REMOVE
        //    - JS/scripts
        //    - Comments
        //    - <style> tags
        //    - <link> tags
        //    - <meta> tags
        //    - <button> tags
        //    - <a> tags
        //    - everything besides <head></head> and <body></body>
        
        html = html.replaceAll("(<!--(.*?)-->)", " ");
        html = html.replaceAll("(<style>(.*?)</style>)", " ");
        html = html.replaceAll("(<script)(.*?)(</script>)", " ");
        //html = html.replaceAll("(<meta)(.*?)(\/>)", " ");

        BufferedWriter steve = new BufferedWriter (new FileWriter ("bob.txt"));
        steve.write(html);
        
        return null;
    }
    
    private static StringBuilder getHTML (String url) {
        StringBuilder html = new StringBuilder();
        try {
            URL web = new URL(url);
            URLConnection connection = web.openConnection();
            connection.connect();

            BufferedReader in = new BufferedReader(new InputStreamReader(web.openStream()));
            String line;
            while ((line = in.readLine()) != null) {
                html.append (line);
            }
            connection.close();
            in.close();
        } 
        catch (MalformedURLException e) { 
            System.out.println ("URL format invalid, please try again");
        } 
        catch (IOException e) {   
            System.out.println ("Could not establish a connection, do you have internet connectivity?");
        }  
        
        if (html.substring(0, 15).equals("<!DOCTYPE html>"))
            return html;            
        else {
            System.out.println("HTML document not valid, exiting");
            valid = false;
        }
        return null;
    }
}