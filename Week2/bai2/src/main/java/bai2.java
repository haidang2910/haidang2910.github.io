import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class bai2 {

    public static String getData() throws IOException {
        Document doc = Jsoup.connect("http://dantri.com.vn").get();
        String title = doc.title();
        Element pngs = doc.select("img[src$=.jpeg]").first();
        String data = title + "\n" + doc.body().text() + "\n" + doc.getElementsByTag("h2").first().text() + "\n" + pngs.attr("lazy-src");
        return data;
    }

    public static void writeFile(String out) {
        try {
            LocalDateTime time = LocalDateTime.now();
            String timeTXT = time.toString();
            FileWriter fw = new FileWriter(timeTXT);
            fw.write(out);
            fw.close();
        } catch (IOException var2) {
            var2.printStackTrace();
        }

    }

    public static void main(String[] args) throws IOException {
        writeFile(getData());
    }

    // build loi chua dua ra ngoai chay duoc
}
