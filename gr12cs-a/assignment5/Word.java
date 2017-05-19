/* Name: Artin S.   
 * Due Date: Friday, May 19, 2017
 * Description: Stores values for each word
 */

 public class Word {
    private int instances = 0;
    private String name = "";
    public Word (int instances, String name) {    //constructors
        this.instances = instances;
        this.name = name;
    }
    public int getInsta () {    //getters
        return instances;
    }
    public String getWord () {
        return name;
    }
    public Word addBy (int num) {    //adds a value if the word already exist
        instances += num;
        return this;
    }
}