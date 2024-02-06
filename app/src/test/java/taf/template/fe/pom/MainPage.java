package taf.template.fe.pom;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;
import org.openqa.selenium.support.FindBy;
import taf.template.core.annotations.DefaultUrl;

import static com.codeborne.selenide.Condition.visible;

@Getter
@DefaultUrl(url = "https://github.com/")
public class MainPage extends PageObject {

    @FindBy(css = "div.application-main form[action='/signup'] input#user_email")
    private SelenideElement userEmailInput;
    @FindBy(css = "div.application-main form[action='/signup'] button")
    private SelenideElement signUpButton;

    @Override
    public void pageIsLoaded() {
        userEmailInput.shouldBe(visible);
    }

}
