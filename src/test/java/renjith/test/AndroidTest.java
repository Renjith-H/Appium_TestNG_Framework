package renjith.test;

import base.AppDriver;
import base.AppFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageObjects.LoginPage;
import pageObjects.ProductsPage;

import java.lang.reflect.Method;
import java.net.MalformedURLException;


public class AndroidTest extends AppFactory{
LoginPage loginPage;
ProductsPage productsPage;
    @BeforeMethod
    public void beforeMethod(Method m){
        productsPage = new ProductsPage();
        System.out.println("\n" + "***** starting test ***** " + m.getName() +"\n");
    }
    @Test
    public void successfulLoginTest() throws MalformedURLException, InterruptedException {
        loginPage=new LoginPage();
        productsPage.clickOpenMenu().clickLoginOption();
        loginPage.enterUsername("bob@example.com")
                .enterPassword("10203040")
                .clickLoginBtn();
        String expectedText = "Products";
        String actualText = productsPage.getProductText();
        Assert.assertEquals(actualText, expectedText);
//        AppDriver.getDriver().findElement(By.xpath("//android.view.ViewGroup[@content-desc='open menu']/android.widget.ImageView")).click();
//        Thread.sleep(2000);
//        AppDriver.getDriver().findElement(By.xpath("//android.view.ViewGroup[@content-desc='menu item log in']/android.widget.TextView")).click();
//        Thread.sleep(2000);
//        AppDriver.getDriver().findElement(By.xpath("//android.widget.EditText[@content-desc='Username input field']")).sendKeys("bob@example.com");
//        AppDriver.getDriver().findElement(By.xpath("//android.widget.EditText[@content-desc='Password input field']")).sendKeys("10203040");
//        AppDriver.getDriver().findElement(By.xpath("//android.view.ViewGroup[@content-desc='Login button']")).click();
//        Thread.sleep(2000);
//        WebElement ele = AppDriver.getDriver().findElement(By.xpath("//android.view.ViewGroup[@content-desc='container header']/android.widget.TextView"));
//        String actualText = ele.getAttribute("text");
//        String expectedText = "Products";
//        Assert.assertEquals(actualText, expectedText);
    }

}
