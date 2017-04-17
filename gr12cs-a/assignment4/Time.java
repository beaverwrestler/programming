public class Time {
    private int mins;
    private int secs;
    
    public Time (String colonFormat) {    //construtor 1
        int colon = colonFormat.indexOf(":");
        int mins = Integer.parseInt(colonFormat.substring(0, colon));
        int secs = Integer.parseInt(colonFormat.substring(colon+1));
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