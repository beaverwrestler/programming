public class MyRectangle {
    
    private int left;
    private int bottom;
    private int width;
    private int height;
    private int numRectangles;
    
    public MyRectangle (int left, int bottom, int width, int height){
        if (this.left<0)
            this.left = 0;
        if (this.bottom<0)
            this.bottom = 0;
        if (this.width<0)
            this.width = 0;
        if (this.height<0)
            this.height = 0;    
        numRectangles++;
    }    
    
    public int getWidth () {
        return width;
    }
    public int getHeight () {
        return height;
    }
    public void setWidth (int width) {
        this.width = width;
    }
    public void setHeight (int height) {
        this.height = height;
    }
    public int getNumRectangles () {
        return numRectangles;
    }
    
    public int area () {
        return (width*height);
    }
    
    private static int compareTo (MyRectangle r1, MyRectangle r2){
        if (r1.area() > r2.area())
            return 1;
        else if (r2.area()>r1.area())
            return -1;
        else
            return 0;
    }
    
    public String toString () {
        return "base: (" + left + ", " + bottom + ") w: " + width + " h: " +height;
    }
}