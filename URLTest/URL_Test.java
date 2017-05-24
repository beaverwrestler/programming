import java.util.*;
import java.io.*;
import java.net.*;

public class URL_Test {
    public static void main (String [] args) {
        String str = "https://www.google.ca/maps";
        try {
            URL myURL = new URL(str);
            URLConnection myURLConnection = myURL.openConnection();
            myURLConnection.connect();
            String finalStr = savePage (str);
            System.out.println(finalStr);
        } 
        catch (MalformedURLException e) { 
            // new URL() failed
        } 
        catch (IOException e) {   
            // openConnection() failed
        }      
    }
    
    public static String savePage(final String URL) throws IOException {
        String line = "", all = "";
        URL myUrl = null;
        BufferedReader in = null;
        try {
            myUrl = new URL(URL);
            in = new BufferedReader(new InputStreamReader(myUrl.openStream()));
            while ((line = in.readLine()) != null) {
                all += line;
            }
        } finally {
            if (in != null) {
                in.close();
            }
        }
        return all;
    }
}