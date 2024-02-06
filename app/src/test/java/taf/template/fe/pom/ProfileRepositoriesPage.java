package taf.template.fe.pom;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.Getter;
import org.openqa.selenium.support.FindBy;
import taf.template.core.annotations.DefaultUrl;

import static com.codeborne.selenide.Condition.visible;

@Getter
@DefaultUrl(url = "https://github.com/{user}?tab=repositories")
public class ProfileRepositoriesPage extends PageObject {

    @FindBy(css = "div.js-profile-editable-replace a")
    private SelenideElement avatarImage;
    @FindBy(css = "ul[data-filterable-for='your-repos-filter'] li a")
    private ElementsCollection listOfRepositories;

    @Override
    public void pageIsLoaded() {
        avatarImage.shouldBe(visible);
    }

}
