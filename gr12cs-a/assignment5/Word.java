public class Word {
    private int instances = 0;
    private String name = "";
    public Word (int instances, String name) {
        this.instances = instances;
        this.name = name;
    }
    public int getInsta () {
        return instances;
    }
    public String getWord () {
        return name;
    }
    public Word addBy (int num) {
        instances += num;
        return this;
    }
}