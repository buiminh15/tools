import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DownLoadLearningPath {
    public final static String LEARNING_PATH = "https://www.lynda.com/learning-paths/Developer/become-a-c-developer";
    public final static String LOCATION = "D:\\download lynda course";
    public static void main(String[] args) throws IOException {

        File htmlFile = new File("D:\\download lynda course\\index.html");
        Document doc = Jsoup.parse(htmlFile, "UTF-8");
        System.out.println(doc.title());
//        Document doc = Jsoup.parse(getHtmlContent(LEARNING_PATH));
//        System.out.println(doc);
        // Elements extends ArrayList<Element>.
        //Elements aElements = doc.getElementsByTag("a");
        Elements aElements = doc.select("div.col-xs-10 > a");

        boolean isLearningPath = true;

        if (isLearningPath) {
            for (Element aElement : aElements) {
                String href = aElement.attr("href");
                if (href.contains(".html")) {
                    System.out.println("https://www.lynda.com" + href);

                }
            }
        } else {
            for (Element aElement : aElements) {
                String href = aElement.attr("href");
                if (href.contains("https://www.lynda.com")) {
                    String[] arrStr = href.split("[?]+");
                    System.out.println(arrStr[0]);
                }
            }
        }

        String[] parts = doc.title().split("-");
        String part1 = parts[0].trim();
        Utils.createFolders(LOCATION,part1);
        System.out.println("========================================");
        Utils.showLocation(LOCATION);
        Utils.formBatch(part1);

    }

    public static String getHtmlContent (String url) throws IOException {
        URL u = new URL(url);
        InputStream in = u.openStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        StringBuilder result = new StringBuilder();
        String line;
        while((line = reader.readLine()) != null) {
            result.append(line);
        }
        return result.toString();
    }


}
