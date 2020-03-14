import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class DownloadWebPage {

    static List<String> readHtml(String linkToIndexFile) throws IOException {
        List<String> list = new ArrayList<>();
        File htmlFile = new File("D:\\download lynda course\\test\\index1.html");
        Document doc = Jsoup.parse(htmlFile, "UTF-8");
        Elements aElements = doc.select("a.course-file");
        for (Element aElement : aElements) {
            String href = aElement.attr("href");
            if (href.contains("/ajax/course/")) {
                String fullPath = "https://www.lynda.com" + href;
                list.add(fullPath);
            }
        }
        return list;
    }
}
