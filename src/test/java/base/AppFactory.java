package base;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import io.appium.java_client.remote.AutomationName;
import org.testng.annotations.BeforeTest;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class AppFactory {

    public AppiumDriver driver;
    @BeforeTest
    public void android_LaunchApp() throws MalformedURLException {
        UiAutomator2Options options = new UiAutomator2Options();
        options.setApp(System.getProperty("user.dir")+"/apps/Android-MyDemoAppRN.1.3.0.build-244.apk");
        //Android-MyDemoAppRN.1.3.0.build-244.apk
        //ApiDemos-debug.apk
        options.setDeviceName("Pixel 5 API 32");
        options.setPlatformName("Android");
        options.setAutomationName(AutomationName.ANDROID_UIAUTOMATOR2);
//        options.setAppPackage("io.appium.android.apis");
//        options.setAppActivity("io.appium.android.apis.ApiDemos");
//        options.noReset();
        options.setAvd("Pixel_2_API_32");
        options.setAvdLaunchTimeout(Duration.ofSeconds(20));
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723"),options);
        AppDriver.setDriver(driver);
        System.out.println("Android driver is set");
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
}
