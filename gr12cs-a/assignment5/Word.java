public class Word {
    private String word = "";
    private int insta = 0;
    
    public Word (String word, int insta) {
        this.word = word;
        this.insta = insta;
    }
    
    public int getInsta () {
        return insta;
    }
    
    public void setIntsa (int num) {
        insta = num;
    }
}