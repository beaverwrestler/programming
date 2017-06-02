public class Saver extends Thread {
    public void run () {
        while (true) {
            System.out.println("once");
            letsTakeFive();
        }
    }
    
    public void letsTakeFive () {
        try {
            Thread.sleep(5000);
        }
        catch (InterruptedException e) {}
    }
}
