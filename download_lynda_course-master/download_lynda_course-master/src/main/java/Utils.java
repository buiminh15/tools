import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

public class Utils {
    static void createFolders(String LOCATION, String FOLDER_PATH) {
        Path path = Paths.get(LOCATION, FOLDER_PATH);
        try {
            Files.createDirectories(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void createCommand(String batchNumber, String part1) {
        String str1 = "youtube-dl.exe -i -c --no-warnings --console-title -a batch%s.txt --cookies cookies.txt ";
        String str2 = "--all-subs -o \"E:/Lynda/%s/";
        str1 = String.format(str1,batchNumber);
        str2 = String.format(str2,part1);
        String pathLink = str1 + str2 + "%(playlist_title)s/%(chapter_number)s -" +
                "%(chapter)s/%(playlist_index)s-%(title)s.%(ext)s\" --external-downloader aria2c.exe " +
                "--max-sleep-interval 20 --min-sleep-interval 5";
        System.out.println(pathLink);
    }

    static void formBatch(String part1){
        createCommand("",part1);
        createCommand("1",part1);
        createCommand("2",part1);
    }

    static void showLocation (String LOCATION) {
        System.out.println(LOCATION);
    }

}
