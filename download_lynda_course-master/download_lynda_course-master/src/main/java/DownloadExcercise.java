
import java.awt.AWTException;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class DownloadExcercise {
    private static String DELETE = "$('.exercise-tab .content').css('max-height', 'none');";
    private static String JQUERY_FILE = "D:\\download lynda course\\MyJQuery.txt";

    public static void main(String[] args) throws InterruptedException, IOException, AWTException {

        String linkIndex = "D:\\download lynda course\\test\\index1.html";

        String path = "D:\\download lynda course\\chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", path);


        ChromeOptions options = new ChromeOptions();
        options.addArguments("user-data-dir=C:\\Users\\MinhBB\\AppData\\Local\\Google\\Chrome\\AutomationProfile");
        options.addArguments("profile-directory=Profile 2");


        WebDriver driver = new ChromeDriver(options);
        driver.get("https://www.lynda.com/Photoshop-tutorials/Dekes-Techniques/76067-2.html");
        // Inject JQuery trong Selenium WebDriver
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript(readFile(JQUERY_FILE));
        js.executeScript("$('.exercise-tab .content').css('max-height', 'none');");
//        js.executeScript("document.querySelector('.exercise-tab .content').style.maxHeight = 'none'");

        Thread.sleep(3*1000);
        WebDriverWait wait = new WebDriverWait(driver,15);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#exercise-tab")));
        driver.findElement(By.cssSelector("#exercise-tab")).click();

        WebDriverWait wait1 = new WebDriverWait(driver,15);
        wait1.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("html.no-touch.member.loaded")));

//        List<WebElement> list1 = driver.findElements(By.cssSelector("a > .exercise-name"));
        List<WebElement> list1 = (List<WebElement>) js.executeScript("return document.querySelectorAll('.course-file')");

        for (int i = 0; i < list1.size(); i++) {
            String link = (String) js.executeScript("return document.querySelectorAll('.course-file')[" + i + "].href");
            System.out.println(link);
        }




//        System.out.println(list1.size());


    }

    public static String readFile(String file) throws IOException {
        Charset cs = Charset.forName("UTF-8");
        FileInputStream stream = new FileInputStream(file);
        try {
            Reader reader = new BufferedReader(new InputStreamReader(stream, cs));
            StringBuilder builder = new StringBuilder();
            char[] buffer = new char[8192];
            int read;
            while ((read = reader.read(buffer, 0, buffer.length)) > 0) {
                builder.append(buffer, 0, read);
            }
            return builder.toString();
        } finally {
            stream.close();
        }
    }

    public static void downloadExcerciseFile() {

    }

}
