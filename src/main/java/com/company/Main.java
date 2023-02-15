package com.company;

import org.asynchttpclient.util.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class Main {

    public static void main(String[] args) {

        //Test 1
        WebDriver driver=new ChromeDriver();
        driver.get("https://www.saucedemo.com/");
        driver.findElement(By.name("user-name")).sendKeys("standard_user");
        driver.findElement(By.name("password")).sendKeys("secret_sauce");
        driver.findElement(By.name("login-button")).click();

        String expected_label = "Sauce Labs Fleece Jacket";
        String actual_label = driver.findElement(By.cssSelector("#item_5_img_link > img")).getAttribute("alt");
        Assert.assertEquals(expected_label,actual_label);
        System.out.println(actual_label);

        //Test 2
        WebDriver driver2=new ChromeDriver();
        driver2.get("https://www.saucedemo.com/");
        driver2.findElement(By.name("user-name")).sendKeys("standard_user");
        driver2.findElement(By.name("password")).sendKeys("wrong-password");
        driver2.findElement(By.name("login-button")).click();

        String expected_error = "Epic sadface: Username and password do not match any user in this service";
        String error_found = driver2.findElement(By.className("error-message-container")).getText();
        System.out.println(error_found);
        Assert.assertEquals(expected_error,error_found);

        //Test 3
        WebDriver driver3=new ChromeDriver();
        driver3.get("https://www.saucedemo.com/");
        driver3.findElement(By.name("user-name")).sendKeys("locked_out_user");
        driver3.findElement(By.name("password")).sendKeys("secret_sauce");
        driver3.findElement(By.name("login-button")).click();

        String expected_error2 = "Epic sadface: Sorry, this user has been locked out.";
        String error_found2 = driver3.findElement(By.className("error-message-container")).getText();
        System.out.println(error_found);
        Assert.assertEquals(expected_error,error_found);

        //Test 4
        WebDriver driver4=new ChromeDriver();
        driver4.get("https://www.saucedemo.com/");
        driver4.findElement(By.name("user-name")).sendKeys("standard_user");
        driver4.findElement(By.name("password")).sendKeys("secret_sauce");
        driver4.findElement(By.name("login-button")).click();

        String expected_price = driver4.findElement(By.xpath("//*[@id=\"inventory_container\"]/div/div[1]/div[2]/div[2]/div")).getText();
        System.out.println(expected_price);
        Assert.assertEquals(expected_price,"$29.99");
        driver4.findElement(By.cssSelector("#add-to-cart-sauce-labs-backpack")).click();
        driver4.findElement(By.cssSelector("#shopping_cart_container > a")).click();
        String page_title = driver4.findElement(By.className("title")).getText();
        System.out.println(page_title);
        Assert.assertEquals(page_title,"YOUR CART");
        String chart_item = driver4.findElement(By.className("inventory_item_name")).getText();
        System.out.println(chart_item);
        Assert.assertEquals(chart_item,"Sauce Labs Backpack");

        //Quit drivers
        driver.quit();
        driver2.quit();
        driver3.quit();
        driver4.quit();
    }
}
