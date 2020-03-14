import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;

public class download_course {
    //    public final static String LEARNING_PATH = "https://www.lynda.com/learning-paths/Web/become-a-front-end-web-developer";
//    public final static String SEARCH = "";


    public static void main(String[] args) throws IOException {

        String PART_1 = "youtube-dl.exe -i -c --no-warnings --console-title -a batch%s.txt --cookies cookies.txt --all-subs -o ";
        String PART_3 = "  --external-downloader aria2c.exe --max-sleep-interval 20 --min-sleep-interval 5 ";




        File htmlFile = new File("E:\\Lynda\\index.html");
        Document doc = Jsoup.parse(htmlFile, "UTF-8");
//        Document doc = Jsoup.parse(getHtmlContent(LEARNING_PATH));
        System.out.println(doc.title());
        String PART_2 =  "\"E:/Lynda/"+ doc.title() +"/%(playlist_title)s/%(chapter_number)s - %(chapter)s/%(playlist_index)s-%(title)s.%(ext)s\"";
        // Elements extends ArrayList<Element>.
        //Elements aElements = doc.getElementsByTag("a");
        Elements aElements = doc.select("div.col-xs-10 > a");

        boolean isLearningPath = true;

        if (isLearningPath) {
            for (Element aElement : aElements) {
                String href = aElement.attr("href");
                if (href.contains(".html")) {
                    System.out.println( href);

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

        System.out.println("======================================");
        String output = String.format(PART_1, "");
        String output1 = String.format(PART_1, "1");
        String output2 = String.format(PART_1, "2");


        System.out.println((output+PART_2+PART_3));
        System.out.println(output1+PART_2+PART_3);
        System.out.println(output2+PART_2+PART_3);

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
