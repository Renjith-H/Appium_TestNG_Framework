package base;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.remote.RemoteWebElement;

import java.time.Duration;
import java.util.Arrays;
import java.util.Collections;

public class Utils {

    public static void scrollNClick(By listItems, String Text){
        boolean flag = false;

        while(true){
            for(WebElement el: AppDriver.getDriver().findElements(listItems)){
                if(el.getAttribute("text").equals(Text)){
                    el.click();
                    flag=true;
                    break;
                }
            }
            if(flag)
                break;
            else
                //scrollDown();
                scroll(AppDriver.getDriver(), ScrollDirection.DOWN);
        }
    }

    public static void scrollNClick(WebElement el){
        int retry = 0;
        while(retry <= 5){
            try{
                el.click();
                break;
            }catch (org.openqa.selenium.NoSuchElementException e){
                //scrollDown();
                scroll(AppDriver.getDriver(), ScrollDirection.DOWN);
                retry++;
            }
        }
    }
    public enum ScrollDirection {
        UP, DOWN, LEFT, RIGHT
    }
    public static void scroll(AppiumDriver driver,ScrollDirection dir){
        int startX = 0;
        int startY = 0;
        int endX = 0;
        int endY = 0;
        Dimension size = driver.manage().window().getSize();
        if(dir.equals(ScrollDirection.UP)){
            startX = size.getWidth()/2;
            startY = size.getHeight()/2;
            endX = startX;
            endY = (int) (size.getHeight()*0.25);
        }
        if(dir.equals(ScrollDirection.DOWN)){
            startX = size.getWidth()/2;
            startY = size.getHeight()/2;
            endX = startX;
            endY = (int) (size.getHeight()/0.25);
        }
        if(dir.equals(ScrollDirection.LEFT)){
            startX = size.getWidth()/2;
            startY = size.getHeight()/2;
            endX = (int) (size.getWidth()/0.25);
            endY = startY;
        }
        if(dir.equals(ScrollDirection.RIGHT)){
            startX = size.getWidth()/2;
            startY = size.getHeight()/2;
            endX = (int) (size.getWidth()*0.25);
            endY = startY;
        }

        PointerInput finger1 = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
        Sequence sequence = new Sequence(finger1, 1)
                .addAction(finger1.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY))
                .addAction(finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                .addAction(new Pause(finger1, Duration.ofMillis(200)))
                .addAction(finger1.createPointerMove(Duration.ofMillis(200), PointerInput.Origin.viewport(), endX, endY))
                .addAction(finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        driver.perform(Collections.singletonList(sequence));
    }

    public boolean isElementDisplayed(WebElement ele) {
        try {
            return !ele.isDisplayed();
        } catch (NoSuchElementException e) {
            return true;
        }
    }

    public static Point getCenterOfElement(WebElement ele){
        Point location = ele.getLocation();
        Dimension size = ele.getSize();
        int x = location.getX() + size.getWidth() / 2;
        int y = location.getY() + size.getHeight() / 2;
        Point center = new Point (x,y);
        return center;
    }
    public static void zoomElement(WebElement ele, AppiumDriver driver){
        Point center = getCenterOfElement(ele);

        PointerInput finger1=new PointerInput(PointerInput.Kind.TOUCH,"finger1");
        Sequence sequence=new Sequence(finger1,1)
                .addAction(finger1.createPointerMove(Duration.ZERO,PointerInput.Origin.viewport(), center))
                .addAction(finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                .addAction(new Pause(finger1,Duration.ofMillis(200)))
                .addAction(finger1.createPointerMove(Duration.ofMillis(200),PointerInput.Origin.viewport(), center.getX()+100, center.getY()-100))
                .addAction(finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        PointerInput finger2=new PointerInput(PointerInput.Kind.TOUCH,"finger2");
        Sequence sequence1=new Sequence(finger2,1)
                .addAction(finger2.createPointerMove(Duration.ZERO,PointerInput.Origin.viewport(), center))
                .addAction(finger2.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                .addAction(new Pause(finger2,Duration.ofMillis(200)))
                .addAction(finger2.createPointerMove(Duration.ofMillis(200),PointerInput.Origin.viewport(), center.getX()+100, center.getY()-100))
                .addAction(finger2.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        driver.perform(Arrays.asList(sequence,sequence1));
    }
    public void longPress(WebElement ele, AndroidDriver driver){

        Point center = getCenterOfElement(ele);
        PointerInput finger1=new PointerInput(PointerInput.Kind.TOUCH,"finger1");
        Sequence sequence=new Sequence(finger1,1)
                .addAction(finger1.createPointerMove(Duration.ZERO,PointerInput.Origin.viewport(), center))
                .addAction(finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                .addAction(new Pause(finger1,Duration.ofSeconds(2)))
                .addAction(finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        driver.perform(Collections.singletonList(sequence));
    }

    public void longPressUsingActions(WebElement ele, AndroidDriver driver){
        new Actions(driver).clickAndHold(ele).perform();
    }
    public static void longPressUsingJSExecutor(WebElement ele, AppiumDriver driver){
        ((JavascriptExecutor) driver).executeScript("mobile: longClickGesture", ImmutableMap.of(
                "elementId", ((RemoteWebElement) ele).getId(),
                "duration",2000));
    }

    public void tap(WebElement ele, AndroidDriver driver){

        Point center = getCenterOfElement(ele);
        PointerInput finger1=new PointerInput(PointerInput.Kind.TOUCH,"finger1");
        Sequence sequence=new Sequence(finger1,1)
                .addAction(finger1.createPointerMove(Duration.ZERO,PointerInput.Origin.viewport(), center))
                .addAction(finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                .addAction(new Pause(finger1,Duration.ofMillis(100)))
                .addAction(finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        driver.perform(Collections.singletonList(sequence));
    }

    public static void doubleTap(WebElement ele, AppiumDriver driver){

        Point center = getCenterOfElement(ele);
        PointerInput finger1=new PointerInput(PointerInput.Kind.TOUCH,"finger1");
        Sequence sequence=new Sequence(finger1,1)
                .addAction(finger1.createPointerMove(Duration.ZERO,PointerInput.Origin.viewport(), center))
                .addAction(finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                .addAction(new Pause(finger1,Duration.ofMillis(100)))
                .addAction(finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()))
                .addAction(new Pause(finger1,Duration.ofMillis(100)))
                .addAction(finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                .addAction(new Pause(finger1,Duration.ofMillis(100)))
                .addAction(finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        driver.perform(Collections.singletonList(sequence));
    }

    public static void dragAndDrop(WebElement source,WebElement destination){

        Point centerOfSource = getCenterOfElement(source);
        Point centerOfDestination = getCenterOfElement(destination);

        PointerInput finger1=new PointerInput(PointerInput.Kind.TOUCH,"finger1");
        Sequence sequence=new Sequence(finger1,1)
                .addAction(finger1.createPointerMove(Duration.ZERO,PointerInput.Origin.viewport(), centerOfSource))
                .addAction(finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                .addAction(new Pause(finger1,Duration.ofMillis(500)))
                .addAction(finger1.createPointerMove(Duration.ZERO,PointerInput.Origin.viewport(), centerOfDestination))
                .addAction(finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));


        AppDriver.getDriver().perform(Collections.singletonList(sequence));
    }
}
