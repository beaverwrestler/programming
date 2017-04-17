public class Time {
    private int mins;
    private int secs;
    
    public Time (String colonFormat) {    
        //constructor #1
    }
    public Time (int sec) {    //constructor #2
        mins = (int) sec/60;
        secs = sec%60;
    } 
    
    //getters
    public int getMins () {
        return mins;
    }
    public int getSecs () {
        return secs;
    }
}