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
        mins = (int) sec/60;
        secs = (sec%60);
    } 
    
    //getters
    public int getMins () {
        return mins;
    }
    public int getSecs () {
        return secs;
    }
    public String getTimeColon () {
        return colonFormat;
    }

    //setter
    public void updateTime (int counter) {
        secs = (counter%60);
        mins = (int) counter/60;
        colonFormat = mins + ":" + secs;
    }
}