package taf.template.fe.flow;

import io.qameta.allure.Step;
import taf.template.fe.pom.MainPage;

import static com.codeborne.selenide.Selenide.page;

public class MainPageFlow {

    @Step("Check is Sign Up e-mail field visible on Main Page")
    public boolean isSignUpEmailFieldVisible() {
        return page(MainPage.class).getUserEmailInput().isDisplayed();
    }

    @Step("Check is Sign Up button visible on Main Page")
    public boolean isSignUpButtonVisible() {
        return page(MainPage.class).getSignUpButton().isDisplayed();
    }

}
