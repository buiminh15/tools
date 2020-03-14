import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class DownloadExcersiceUseless {
    public final static String LEARNING_PATH = "https://www.lynda.com/Google-Analytics-tutorials/SEO-Foundations/737788-2.html";
    public final static String LOCATION = "D:\\download lynda course\\";
    public static void main(String[] args) throws IOException {
        List<String> listFolderPath = null;

//        try {
//            listFolderPath = getList(LEARNING_PATH);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        for (String str: listFolderPath
//             ) {
//            createFolders(str);
//        }

//        try {
//            createParentFolders(LEARNING_PATH);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        getList(LEARNING_PATH);

    }


    // create sub folders
    private static void createFolders(String FOLDER_PATH) {
        Path path = Paths.get(LOCATION,FOLDER_PATH);
        try {
            Files.createDirectories(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // create parent folders
    private static void createParentFolders(String LEARNING_PATH) throws IOException {
        Document doc = Jsoup.connect(LEARNING_PATH).get();
        String[] title = doc.title().split("-");
        String folderTitle = title[0];
        Path path = Paths.get(LOCATION,folderTitle);
        try {
            Files.createDirectories(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // get list html
    private static List<String> getList(String LEARNING_PATH) throws IOException {
        List<String> listWithDuplicates = new ArrayList<String>();
        List<String> listFolderPath = new ArrayList<>();

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
            Document doc1 = Jsoup.connect(str).get();
            String title = doc1.title();
            str = title.replace(':',' ');
            listFolderPath.add(str);

        }

        return listFolderPath;
    }

}
