package taf.template.fe.pom;

import lombok.Getter;

@Getter
public enum Pages {

    LOGIN_PAGE("LoginPage"),
    MAIN_PAGE("MainPage"),
    PROFILE_REPOSITORIES_PAGE("ProfileRepositoriesPage");

    private final String className;

    Pages(String className) {
        this.className = className;
    }

}
