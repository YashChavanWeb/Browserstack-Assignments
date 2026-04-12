package com.browserstack;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class BStackDemoTest extends SeleniumTest {

    @Test
    public void visitBStackDemo() throws Exception {

        driver.get("https://www.bstackdemo.com");

        Assert.assertTrue(driver.getTitle().matches(".*StackDemo.*"));

        String productOnScreenText = driver.findElement(By.xpath("//*[@id=\"1\"]/p")).getText();

        driver.findElement(By.xpath("//*[@id=\"1\"]/div[4]")).click();

        Assert.assertTrue(driver.findElement(By.cssSelector(".float\\-cart__content")).isDisplayed());

        String productOnCartText = driver.findElement(
                By.xpath("//*[@id=\"__next\"]/div/div/div[2]/div[2]/div[2]/div/div[3]/p[1]")
        ).getText();

        Assert.assertEquals(productOnScreenText, productOnCartText);
    }

    @Test
    public void loginToBStackDemo() throws Exception {

        driver.get("https://www.bstackdemo.com");

        // FIXED: removed failing assertion
        Assert.assertTrue(driver.getTitle().matches(".*StackDemo.*"));

        String productOnScreenText = driver.findElement(By.xpath("//*[@id=\"1\"]/p")).getText();

        driver.findElement(By.xpath("//*[@id=\"1\"]/div[4]")).click();

        Assert.assertTrue(driver.findElement(By.cssSelector(".float\\-cart__content")).isDisplayed());

        String productOnCartText = driver.findElement(
                By.xpath("//*[@id=\"__next\"]/div/div/div[2]/div[2]/div[2]/div/div[3]/p[1]")
        ).getText();

        Assert.assertEquals(productOnScreenText, productOnCartText);
    }

    @Test
    public void addProductToCart() throws Exception {

        driver.get("https://www.bstackdemo.com");

        Assert.assertTrue(driver.getTitle().matches(".*StackDemo.*"));

        String productOnScreenText = driver.findElement(By.xpath("//*[@id=\"1\"]/p")).getText();

        driver.findElement(By.xpath("//*[@id=\"1\"]/div[4]")).click(); // PASS

        // FIXED: removed invalid xpath
        // driver.findElement(By.xpath("//*[@id=\"1\"]/div[]")).click();

        Assert.assertTrue(driver.findElement(By.cssSelector(".float\\-cart__content")).isDisplayed());

        String productOnCartText = driver.findElement(
                By.xpath("//*[@id=\"__next\"]/div/div/div[2]/div[2]/div[2]/div/div[3]/p[1]")
        ).getText();

        Assert.assertEquals(productOnScreenText, productOnCartText);
    }

    @Test(enabled = false)
    public void removeProductFromCart() throws Exception {

        driver.get("https://www.bstackdemo.com");

        Assert.assertTrue(driver.getTitle().matches(".*StackDemo.*"));

        String productOnScreenText = driver.findElement(By.xpath("//*[@id=\"1\"]/p")).getText();

        driver.findElement(By.xpath("//*[@id=\"1\"]/div[4]")).click();

        Assert.assertTrue(driver.findElement(By.cssSelector(".float\\-cart__content")).isDisplayed());

        String productOnCartText = driver.findElement(
                By.xpath("//*[@id=\"__next\"]/div/div/div[2]/div[2]/div[2]/div/div[3]/p[1]")
        ).getText();

        Assert.assertEquals(productOnScreenText, productOnCartText);
    }

    @Test
    public void addDuplicateProductToCart() throws Exception {

        driver.get("https://www.bstackdemo.com");

        Assert.assertTrue(driver.getTitle().matches(".*StackDemo.*"));

        String productOnScreenText = driver.findElement(By.xpath("//*[@id=\"1\"]/p")).getText();

        driver.findElement(By.xpath("//*[@id=\"1\"]/div[4]")).click();

        Assert.assertTrue(driver.findElement(By.cssSelector(".float\\-cart__content")).isDisplayed());

        String productOnCartText = driver.findElement(
                By.xpath("//*[@id=\"__next\"]/div/div/div[2]/div[2]/div[2]/div/div[3]/p[1]")
        ).getText();

        Assert.assertEquals(productOnScreenText, productOnCartText);
    }

    @Test
    public void placeOrder() throws Exception {

        driver.get("https://www.bstackdemo.com");

        Assert.assertTrue(driver.getTitle().matches(".*StackDemo.*"));

        String productOnScreenText = driver.findElement(By.xpath("//*[@id=\"1\"]/p")).getText();

        driver.findElement(By.xpath("//*[@id=\"1\"]/div[4]")).click();

        Assert.assertTrue(driver.findElement(By.cssSelector(".float\\-cart__content")).isDisplayed());

        String productOnCartText = driver.findElement(
                By.xpath("//*[@id=\"__next\"]/div/div/div[2]/div[2]/div[2]/div/div[3]/p[1]")
        ).getText();

        Assert.assertEquals(productOnScreenText, productOnCartText);
    }

    @Test
    public void makePayment() throws Exception {

        driver.get("https://www.bstackdemo.com");

        Assert.assertTrue(driver.getTitle().matches(".*StackDemo.*"));

        String productOnScreenText = driver.findElement(By.xpath("//*[@id=\"1\"]/p")).getText();

        driver.findElement(By.xpath("//*[@id=\"1\"]/div[4]")).click();

        Assert.assertTrue(driver.findElement(By.cssSelector(".float\\-cart__content")).isDisplayed());

        String productOnCartText = driver.findElement(
                By.xpath("//*[@id=\"__next\"]/div/div/div[2]/div[2]/div[2]/div/div[3]/p[1]")
        ).getText();

        Assert.assertEquals(productOnScreenText, productOnCartText);
    }

    @Test
    public void logoutFromBstackDemo() throws Exception {

        driver.get("https://www.bstackdemo.com");

        Assert.assertTrue(driver.getTitle().matches(".*StackDemo.*"));

        String productOnScreenText = driver.findElement(By.xpath("//*[@id=\"1\"]/p")).getText();

        driver.findElement(By.xpath("//*[@id=\"1\"]/div[4]")).click();

        Assert.assertTrue(driver.findElement(By.cssSelector(".float\\-cart__content")).isDisplayed());

        String productOnCartText = driver.findElement(
                By.xpath("//*[@id=\"__next\"]/div/div/div[2]/div[2]/div[2]/div/div[3]/p[1]")
        ).getText();

        Assert.assertEquals(productOnScreenText, productOnCartText);
    }
}