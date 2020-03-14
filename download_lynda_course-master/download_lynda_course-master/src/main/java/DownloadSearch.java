import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class DownloadSearch {
    public final static String LEARNING_PATH = "https://www.lynda.com/Student-Tools-training-tutorials/1799-0.html";
    public final static String SEARCH = "";
    public final static String LOCATION = "E:\\Lynda";

    public static void main(String[] args) throws IOException {


        List<String> listWithDuplicates = new ArrayList<String>();

        Document doc = Jsoup.connect(LEARNING_PATH).get();
        System.out.println(doc.title());
        Elements aElements = doc.getElementsByTag("a");

        for (Element aElement : aElements) {
            String href = aElement.attr("href");
            if (href.contains("https://www.lynda.com")) {
                String[] arrStr = href.split("[?]+");
                listWithDuplicates.add(arrStr[0]);

            }
        }

        List<String> listWithoutDuplicates = new ArrayList<String>(new HashSet<String>(listWithDuplicates));

        for (String str: listWithoutDuplicates) {
            System.out.println(str);
        }
        String[] parts = doc.title().split("-");
        String part1 = parts[0].trim();
        Utils.createFolders(LOCATION,part1);

        System.out.println("==================================");
        //Utils.showLocation(LOCATION);
        Utils.formBatch(part1);
    }


}

