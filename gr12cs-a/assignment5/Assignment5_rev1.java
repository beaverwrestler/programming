public class Assignment5_rev1 {
    public static void main (String [] args) throws FileNotFoundException, IOException {
        long startTime = System.currentTimeMillis();
        BufferedReader text = new BufferedReader (new FileReader ("input.txt"));
        StringBuilder book = new StringBuilder ();
        String line = text.readLine();

        while (line != null){   //imports the whole goddamn book
            book.append(line + " ");
            line = text.readLine();
        }

        text.close();
        String finalStr = book.toString().toLowerCase();
        StringTokenizer words = new StringTokenizer (finalStr, "~!@#$%^&*()_+{}|:\"<>?`=[];, ./-\\");
        Map <String, Integer> bookMap = new HashMap <> ();

        while (words.hasMoreTokens()) {
            
        }
    }
}