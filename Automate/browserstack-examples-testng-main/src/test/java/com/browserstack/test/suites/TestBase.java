package com.browserstack.test.suites;

import com.browserstack.test.utils.DriverUtil;
import com.browserstack.test.utils.ScreenshotListener;
import io.percy.selenium.Percy;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

import java.net.URL;
import java.time.Duration;

@Listeners({ ScreenshotListener.class })
public class TestBase {

    public WebDriver driver;
    protected WebDriverWait wait;
    public static Percy percy;

    public boolean isOnPremExecution() {
        return StringUtils.equalsIgnoreCase(System.getProperty("on-prem"), "true");
    }

    @BeforeMethod(alwaysRun = true)
    public void setUp() throws Exception {
        if (isOnPremExecution()) {
            DriverUtil.setDriverPathVariable();
            driver = new ChromeDriver();
        } else {
            /* SDK WAY: We use an empty DesiredCapabilities object. 
               The BrowserStack SDK will automatically intercept this call,
               inject the correct Hub URL, and add the credentials/capabilities 
               defined in your browserstack.yml file.
            */
            DesiredCapabilities capabilities = new DesiredCapabilities();
            driver = new RemoteWebDriver(new URL("https://hub.browserstack.com/wd/hub"), capabilities);
        }

        // Initialize Percy with the driver session
        percy = new Percy(driver);

        // Dynamic URL handling
        String baseUrl = System.getProperty("baseUrl");
        if (baseUrl == null) {
            baseUrl = "https://bstackdemo.com";
        }
        
        driver.get(baseUrl);
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}