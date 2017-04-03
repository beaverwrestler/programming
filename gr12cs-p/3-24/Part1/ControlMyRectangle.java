public class ControlMyRectangle { 
    
    public static void main (String[] args) {   
     int oneLeft = 4;
     int oneRight = 3;
     int oneW = 4;
     int oneH = 6;
     int twoLeft = 4;
     int twoRight = 3;
     int twoW = 4;
     int twoH = 6;
        
     MyRectangle r1 = new MyRectangle (oneLeft, oneRight, oneW, oneH);
     MyRectangle r2 = new MyRectangle (twoLeft, twoRight, twoW, twoH);
     System.out.println("get width r1: " + r1.getWidth());
     System.out.println("r1 area: " + r1.area());
     System.out.println("r2 area: " + r2.area());
    }   
}