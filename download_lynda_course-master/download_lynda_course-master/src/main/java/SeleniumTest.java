import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class SeleniumTest {
    public final static String PATH = "https://www.lynda.com/Photoshop-tutorials/Dekes-Techniques/76067-2.html";
    public static void main(String[] args) throws InterruptedException {

        System.setProperty("webdriver.chrome.driver", "D:\\2. Fun\\5. Selenium\\driver\\chromedriver.exe");

        String downloadFilepath = "D:\\download lynda course\\test";

        HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
        chromePrefs.put("profile.default_content_settings.popups", 0);
        chromePrefs.put("download.default_directory", downloadFilepath);
        ChromeOptions options = new ChromeOptions();
        HashMap<String, Object> chromeOptionsMap = new HashMap<String, Object> ();
        options.setExperimentalOption("prefs", chromePrefs);
        options.addArguments("--test-type");
        options.addArguments("--disable-extensions"); //to disable browser extension popup
        options.addArguments("user-data-dir=C:\\Users\\MinhBB\\AppData\\Local\\Google\\Chrome\\User Data");
        options.addArguments("profile-directory=Profile 2");
        options.addArguments("--headless");
        options.setCapability(ChromeOptions.CAPABILITY, chromeOptionsMap);
        options.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        options.setCapability(ChromeOptions.CAPABILITY, options);

        ChromeDriver driver = new ChromeDriver(options);
        driver.get(PATH);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
//        if (driver instanceof JavascriptExecutor) {
//            Thread.sleep(4000);
//            System.out.println( ((JavascriptExecutor) driver)
//                    .executeScript("return document.documentElement.outerHTML").toString());
//        }
    }
}

