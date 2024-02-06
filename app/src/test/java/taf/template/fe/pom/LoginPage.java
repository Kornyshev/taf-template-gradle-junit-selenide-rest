package taf.template.fe.pom;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;
import org.openqa.selenium.support.FindBy;
import taf.template.core.annotations.DefaultUrl;

import static com.codeborne.selenide.Condition.visible;

@Getter
@DefaultUrl(url = "https://github.com/login")
public class LoginPage extends PageObject {

    @FindBy(css = "div.application-main div.auth-form-header h1")
    private SelenideElement signInPageHeader;
    @FindBy(css = "form input#login_field")
    private SelenideElement loginField;
    @FindBy(css = "form input#password")
    private SelenideElement passwordField;
    @FindBy(css = "form input.btn.btn-primary")
    private SelenideElement signInButton;

    @Override
    public void pageIsLoaded() {
        signInPageHeader.shouldBe(visible);
    }

}
