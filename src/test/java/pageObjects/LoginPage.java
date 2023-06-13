package pageObjects;

import base.AppFactory;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

public class LoginPage extends AppFactory {
    @AndroidFindBy(xpath = "//android.widget.EditText[@content-desc='Username input field']")
    private WebElement inputUsername;
    @AndroidFindBy(xpath = "//android.widget.EditText[@content-desc='Password input field']")
    private WebElement inputPassword;
    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc='Login button']")
    private WebElement btnLogin;
    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc='menu item log in']/android.widget.TextView")
    private WebElement optionLogin;

    public LoginPage enterUsername(String username){
        sendKeys(inputUsername, username);
        return this;
    }
    public LoginPage enterPassword(String password){
        sendKeys(inputPassword, password);
        return this;
    }

    public ProductsPage clickLoginBtn(){
        click(btnLogin);
        return new ProductsPage();
        }
}
