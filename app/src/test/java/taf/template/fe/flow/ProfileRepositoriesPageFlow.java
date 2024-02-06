package taf.template.fe.flow;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.Step;
import taf.template.fe.pom.ProfileRepositoriesPage;

import java.util.List;

public class ProfileRepositoriesPageFlow {

    @Step("Get list of repositories on Profile Page")
    public List<String> getListOfRepositories() {
        return Selenide.page(ProfileRepositoriesPage.class).getListOfRepositories()
                .texts().stream().map(String::trim).toList();
    }

}
