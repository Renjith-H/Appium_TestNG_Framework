package pageObjects;

import base.AppFactory;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

public class ProductsPage extends AppFactory {
    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc='open menu']/android.widget.ImageView")
    private WebElement btnOpenMenu;

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc='menu item log in']/android.widget.TextView")
    private WebElement optionLogin;

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc='container header']/android.widget.TextView")
    private WebElement txtProducts;

    public ProductsPage clickOpenMenu(){
        click(btnOpenMenu);
        return this;
    }
    public String getProductText(){
        return getAttribute(txtProducts,"text");
    }

    public LoginPage clickLoginOption(){
        click(optionLogin);
        return new LoginPage();
    }
}
