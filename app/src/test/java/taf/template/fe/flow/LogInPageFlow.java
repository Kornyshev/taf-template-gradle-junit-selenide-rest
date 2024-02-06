package taf.template.fe.flow;

import io.qameta.allure.Step;
import taf.template.fe.models.User;
import taf.template.fe.pom.LoginPage;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.page;

public class LogInPageFlow {

    @Step("Get Sign In header text on sign In Page")
    public String getSignInPageHeaderText() {
        return page(LoginPage.class).getSignInPageHeader().shouldBe(visible).text();
    }

    @Step("Login as user")
    public void loginAsUser(User user) {
        page(LoginPage.class).getLoginField().shouldBe(visible).sendKeys(user.getLogin());
        page(LoginPage.class).getPasswordField().shouldBe(visible).sendKeys(user.getPassword());
        page(LoginPage.class).getSignInButton().shouldBe(visible).click();
    }

}
