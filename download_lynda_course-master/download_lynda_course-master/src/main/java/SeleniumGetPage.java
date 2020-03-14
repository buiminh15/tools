import com.google.gson.internal.$Gson$Preconditions;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SeleniumGetPage {
    public static void main(String[] args) throws InterruptedException, IOException, AWTException {
        List<String> list = new ArrayList<>();
        String linkIndex = "D:\\download lynda course\\test\\index1.html";
        list = DownloadWebPage.readHtml(linkIndex);
        String path = "D:\\download lynda course\\chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", path);

        ChromeOptions options = new ChromeOptions();
        options.addArguments("user-data-dir=C:\\Users\\MinhBB\\AppData\\Local\\Google\\Chrome\\AutomationProfile");
        options.addArguments("profile-directory=Profile 2");

        WebDriver driver = new ChromeDriver(options);
        driver.get(list.get(10));

        Thread.sleep(3*1000);


    }

    public static void waitForLoad(WebDriver driver) {
        ExpectedCondition<Boolean> pageLoadCondition = new
                ExpectedCondition<Boolean>() {
                    public Boolean apply(WebDriver driver) {
                        return ((JavascriptExecutor)driver).executeScript("return document.readyState").equals("complete");
                    }
                };
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(pageLoadCondition);
    }
}

