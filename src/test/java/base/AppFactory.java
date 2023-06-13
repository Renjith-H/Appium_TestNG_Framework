package base;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.remote.AutomationName;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class AppFactory {

    private static AppiumDriver driver;
    private static Properties props;
    private static FileInputStream fis;

    public AppFactory(){
        PageFactory.initElements(new AppiumFieldDecorator(driver),this);
    }
    @Parameters({"platformName","deviceName","avdName"})
    @BeforeTest
    public void android_LaunchApp(String platformName,String deviceName, String avdName) throws MalformedURLException {
        try{
            props = new Properties();
            String propFileName = System.getProperty("user.dir")+"/src/main/resources/config.properties";
            fis=new FileInputStream(propFileName);
            props.load(fis);
            UiAutomator2Options options = new UiAutomator2Options();
            options.setApp(System.getProperty("user.dir")+props.getProperty("appUrl"));
            //Android-MyDemoAppRN.1.3.0.build-244.apk
            //ApiDemos-debug.apk
            options.setDeviceName(deviceName);
            options.setPlatformName(platformName);
            options.setAutomationName(AutomationName.ANDROID_UIAUTOMATOR2);
//        options.setAppPackage("io.appium.android.apis");
//        options.setAppActivity("io.appium.android.apis.ApiDemos");
            options.noReset();
            options.setAvd(avdName);
            options.setAvdLaunchTimeout(Duration.ofSeconds(20));

            URL url = new URL(props.getProperty("appiumUrl"));
            driver = new AndroidDriver(url,options);
            AppDriver.setDriver(driver);
            System.out.println("Android driver is set");
        }
        catch(Exception e){
            e.printStackTrace();
            try {
                throw e;
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }

    }

    public void iOS_LaunchApp() throws MalformedURLException {
        XCUITestOptions options = new XCUITestOptions();
        //options.setApp(System.getProperty("user.dir")+"/apps/iOS-Simulator-MyRNDemoApp.1.3.0-162.zip");
        options.setDeviceName("iPhone 11 Pro");
        options.setPlatformName("iOS");
        options.setAutomationName("XCuiTest");
        //com.saucelabs.mydemoapp.rn
        options.setBundleId("com.saucelabs.mydemoapp.rn");
        driver = new IOSDriver(new URL("http://127.0.0.1:4723"),options);
        AppDriver.setDriver(driver);
        System.out.println("iOs driver is set");
    }

    public void waitforVisibilty(WebElement e){
        WebDriverWait wait = new WebDriverWait(AppDriver.getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(e));
    }
    public void click(WebElement e){
        waitforVisibilty(e);
        e.click();
    }
    public void sendKeys(WebElement e, String txt){
        waitforVisibilty(e);
        e.sendKeys(txt);
    }
    public String getAttribute(WebElement e, String attribute){
        waitforVisibilty(e);
        return e.getAttribute(attribute);
    }
}
