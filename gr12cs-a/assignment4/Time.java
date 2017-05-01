/* Name: Artin S.
 * Due Date: Monday, May 1, 2017
 * Description: Instances are made of this class to store the time of a CD or Song, getters & setters exist, limited functionality beyond this
*/

public class Time {
    private int mins = 0;
    private int secs = 0;
    private String colonFormat;
    
    public Time (String colonFormat) {    //construtor 1
        this.colonFormat = colonFormat;
        int colon = colonFormat.indexOf(":");
        int mins = Integer.parseInt(colonFormat.substring(0, colon));
        int secs = Integer.parseInt(colonFormat.substring(colon+1));
    }
    public Time (int sec) {    //constructor #2
        mins = sec/60;
        secs = (sec%60);
        if (secs>10)
            colonFormat = mins+ ":" + secs;     //adds colon
        else
            colonFormat = mins+ ":0" + secs;        //makes sure that the number of seconds doesn't look funny (ie. 56:8)
    } 
    
    //getters
    public String getTimeColon () {
        return colonFormat;
    }

    //setter
    public void updateTime (int counter) {
        secs = (counter%60);
        mins = (int) counter/60;
        if (secs>10)
            colonFormat = mins+ ":" + secs;     //adds colon
        else
            colonFormat = mins+ ":0" + secs;        //makes sure that the number of seconds doesn't look funny (ie. 56:8)
    }
}