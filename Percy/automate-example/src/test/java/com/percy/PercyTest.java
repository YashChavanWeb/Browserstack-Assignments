package com.percy;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import io.percy.selenium.Percy;

import java.net.URL;
import java.time.Duration;

class PercyTest {

    public Percy percy ;
    public WebDriverWait webDriverWait;
    public WebDriver driver;

    public String USERNAME = System.getenv("BROWSERSTACK_USERNAME");
    public String AUTOMATE_KEY =  System.getenv("BROWSERSTACK_ACCESS_KEY");
    public String URL = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";

    @BeforeMethod(alwaysRun = true)
    @SuppressWarnings("unchecked")
    public void setUp() throws Exception {
        ChromeOptions options = new ChromeOptions();
        options.setCapability("browserName", "Chrome");
        options.setCapability("projectName","My Project");
        options.setCapability("buildName","test percy_screenshot");
        options.setCapability("sessionName","Percy visual_diff_test");
        options.setCapability("local","false");
        options.setCapability("seleniumVersion","3.141");
        options.setCapability("browserVersion", "latest");
        options.setCapability("os", "Windows");
        options.setCapability("os_version", "11");
        driver = new RemoteWebDriver(new URL(URL), options);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() throws Exception {
        driver.quit();
    }

    @Test
    public void addProductToCart() throws Exception {

        try {
            webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(10));
            percy = new Percy(driver);

            driver.manage().window().setSize(new Dimension(1280, 1024));
            driver.get("https://www.bstackdemo.com");

            webDriverWait.until(ExpectedConditions.titleContains("StackDemo"));

            // CHANGE: Clicked Samsung (div[2]) instead of Apple (div[1]) to change visual state
            webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"__next\"]/div/div/main/div[1]/div[2]/label/span")));
            driver.findElement(By.xpath("//*[@id=\"__next\"]/div/div/main/div[1]/div[2]/label/span")).click();

            // Percy Screenshot 1 - Now shows Samsung devices
            percy.screenshot("screenshot_1");

            // CHANGE: Updated ID to 10 (Samsung S20) to reflect the new selection
            String productOnScreenText = driver.findElement(By.xpath("//*[@id=\"10\"]/p")).getText();

            // CHANGE: Updated ID to 10 for the "Add to cart" button
            webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"10\"]/div[4]")));
            driver.findElement(By.xpath("//*[@id=\"10\"]/div[4]")).click();

            webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.className("float-cart__content")));

            String productOnCartText = driver.findElement(By.xpath("//*[@id=\"__next\"]/div/div/div[2]/div[2]/div[2]/div/div[3]/p[1]")).getText();

            // Percy Screenshot 2 - Will show the Samsung product in the cart
            percy.screenshot("screenshot_2");

            Assert.assertEquals(productOnScreenText, productOnCartText);

        } catch (Exception e) {
            System.out.println("Error occured while executing script :" + e);
        }
    }
}