public class randonString {
  public static void main (String [] args) {
    String ans = "";
    String alpha = "abcdefghijklmnopqrstuvwxyz";
    
    for (int i = 0; i < 1000; i++) {
      int rand =  (int) (Math.random()*26);
      ans += alpha.substring (rand, rand+1);
    }
    System.out.println(ans);
  }
}