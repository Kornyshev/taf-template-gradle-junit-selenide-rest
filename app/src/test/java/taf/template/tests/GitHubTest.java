package taf.template.tests;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import taf.template.be.controllers.UserController;
import taf.template.be.models.Repository;
import taf.template.be.models.User;
import taf.template.core.annotations.Flow;
import taf.template.core.utils.DataUtils;
import taf.template.fe.flow.LogInPageFlow;
import taf.template.fe.flow.ProfileRepositoriesPageFlow;
import taf.template.tests.annotations.NewRepository;
import taf.template.tests.extensions.RepositoryPreconditionExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;
import static taf.template.fe.pom.Pages.*;

@Slf4j
@ExtendWith(RepositoryPreconditionExtension.class)
class GitHubTest extends BaseTest {

    @Flow
    UserController userController;
    @Flow
    LogInPageFlow logInPageFlow;
    @Flow
    ProfileRepositoriesPageFlow profileRepositoriesPageFlow;

    @Test
    void testApiUserController() {
        log.info("Testing API - User Controller");
        User user = userController.executeGetUserData();
        userController.executeGetUserRepos();
        log.info("User: {}", user);
    }

    @Test
    void testSignUpElementsOnMainPage() {
        browserFlow.open(MAIN_PAGE);
        assertThat(mainPageFlow.isSignUpButtonVisible()).isTrue();
        assertThat(mainPageFlow.isSignUpEmailFieldVisible()).isTrue();
    }

    @Test
    void testHeaderTextOnSignInPage() {
        browserFlow.open(LOGIN_PAGE);
        assertThat(logInPageFlow.getSignInPageHeaderText())
                .isEqualTo("Sign in to GitHub");
    }

    @NewRepository(name = "NewTestRepo", isPrivate = "false")
    @Test
    void testRepositoryIsPresent(Repository repository) {
        browserFlow.open(LOGIN_PAGE);
        logInPageFlow.loginAsUser(DataUtils.getUserFromEnvironmentVariables());
        browserFlow.open(PROFILE_REPOSITORIES_PAGE, userController.executeGetUserData().getLogin());
        log.info("Repo in test: {}", repository);
        assertThat(profileRepositoriesPageFlow.getListOfRepositories()).contains(repository.getName());
    }

    @NewRepository(name = "NewTestRepo_ForFailingTest", isPrivate = "false")
    @Test
    void testRepositoryIsPresentFailingTest(Repository repository) {
        browserFlow.open(LOGIN_PAGE);
        logInPageFlow.loginAsUser(DataUtils.getUserFromEnvironmentVariables());
        browserFlow.open(PROFILE_REPOSITORIES_PAGE, userController.executeGetUserData().getLogin());
        log.info("Repo in test: {}", repository);
        assertThat(profileRepositoriesPageFlow.getListOfRepositories()).contains("ERROR");
    }

    @Test
    void testWhichAlwaysFails() {
        fail("Failure for test demo purpose");
    }

}
