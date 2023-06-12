package renjith.test;

import base.AppDriver;
import base.AppFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.net.MalformedURLException;


public class AndroidTest {

    @Test
    public void successfulLoginTest() throws MalformedURLException, InterruptedException {
        //AppiumServer.Start();
        //AppFactory.android_LaunchApp();

        AppDriver.getDriver().findElement(By.xpath("//android.view.ViewGroup[@content-desc='open menu']/android.widget.ImageView")).click();
        Thread.sleep(2000);
        AppDriver.getDriver().findElement(By.xpath("//android.view.ViewGroup[@content-desc='menu item log in']/android.widget.TextView")).click();
        Thread.sleep(2000);
        AppDriver.getDriver().findElement(By.xpath("//android.widget.EditText[@content-desc='Username input field']")).sendKeys("bob@example.com");
        AppDriver.getDriver().findElement(By.xpath("//android.widget.EditText[@content-desc='Password input field']")).sendKeys("10203040");
        AppDriver.getDriver().findElement(By.xpath("//android.view.ViewGroup[@content-desc='Login button']")).click();
        Thread.sleep(2000);
        WebElement ele = AppDriver.getDriver().findElement(By.xpath("//android.view.ViewGroup[@content-desc='container header']/android.widget.TextView"));
        String actualText = ele.getAttribute("text");
        String expectedText = "Products";
        Assert.assertEquals(actualText, expectedText);
    }

}
