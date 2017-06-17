import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Citation {
    private String dateAcc;
    private String datePosted;
    private String author;
    private String url;
    private String publisher;
    private String title;
    private String webTitle;

    Citation (String datePosted, String author, String url, String publisher, String title, String webTitle, String dateAcc) {
        this.datePosted = datePosted;
        this.author = author;
        this.url = url;
        this.publisher = publisher;
        this.title = title;
        this.webTitle = webTitle;

        if (dateAcc != null && !dateAcc.equals(""))
            this.dateAcc = dateAcc;
        else {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
            LocalDate localDate = LocalDate.now();
            this.dateAcc = dtf.format(localDate);
        }
    }

    String getFullMLA () {
        String properName = getProperName();
        return properName + ". \"" + title + ".\" " + webTitle + ". " + publisher + ", " +
                datePosted + ". Web. " + dateAcc;
    }

    String getFullAPA () {
        String properName = getProperName();
        return properName + ". (" + dateAcc + "). " + title + ". Retrieved from " + url;
    }

    private String getProperName () {
        String last = "", first;
        String authorTrim = author.trim();

        if (authorTrim.length()>0) {

            if (!authorTrim.contains(" "))
                return authorTrim.substring(0, 1).toUpperCase() + authorTrim.substring(1);

            for (int i = authorTrim.length() - 1; i > -1; i--)
                if (authorTrim.charAt(i) == ' ') {
                    last = authorTrim.substring(i);
                    last = last.trim();
                    break;
                }

            first = authorTrim.substring(0, 1).toUpperCase();
            last = last.substring(0, 1).toUpperCase() + last.substring(1);
            return last + ", " + first;
        }
        return "";
    }

    String getDateAcc () {
        return dateAcc;
    }
    String getDatePosted () {
        return datePosted;
    }
    String getAuthor () {
        return author;
    }
    String getUrl () {
        return url;
    }
    String getPublisher () {
        return publisher;
    }
    String getTitle () {
        return title;
    }
    String getWebTitle () {
        return webTitle;
    }

    void setDateAcc (String a) {
        dateAcc = a;
    }
    void setAuthor (String a) {
        author = a;
    }
    void setDatePosted (String a) {
        datePosted = a;
    }
    void setUrl (String a) {
        url = a;
    }
    void setPublisher (String a) {
        publisher = a;
    }
    void setTitle (String a) {
        title = a;
    }
    void setWebTitle (String a) {
        webTitle = a;
    }


}