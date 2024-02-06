package taf.template.tests.extensions;

import com.codeborne.selenide.Screenshots;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Allure;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.extension.*;
import org.junit.platform.commons.support.AnnotationSupport;
import org.openqa.selenium.WebDriver;
import taf.template.be.controllers.RepositoriesController;
import taf.template.be.controllers.UserController;
import taf.template.be.models.Repository;
import taf.template.be.models.User;
import taf.template.tests.annotations.NewRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.util.Optional;

@Slf4j
public class RepositoryPreconditionExtension implements BeforeEachCallback, AfterEachCallback, ParameterResolver {

    public static final ExtensionContext.Namespace NAMESPACE
            = ExtensionContext.Namespace.create(RepositoryPreconditionExtension.class);

    @Override
    public void beforeEach(ExtensionContext extensionContext) {
        Optional<NewRepository> annotation = AnnotationSupport.findAnnotation(
                extensionContext.getRequiredTestMethod(), NewRepository.class);
        if (annotation.isPresent()) {
            NewRepository newRepository = annotation.get();
            Repository repository = Repository.builder()
                    .name(newRepository.name()).isPrivate(newRepository.isPrivate()).build();
            new RepositoriesController().executePostNewRepo(repository);
            extensionContext.getStore(NAMESPACE).put("repo", repository);
        }
    }

    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return extensionContext.getRequiredTestMethod().isAnnotationPresent(NewRepository.class);
    }

    @Override
    public Repository resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return extensionContext.getStore(NAMESPACE).get("repo", Repository.class);
    }

    @Override
    public void afterEach(ExtensionContext extensionContext) throws IOException {
        Optional<NewRepository> annotation = AnnotationSupport.findAnnotation(
                extensionContext.getRequiredTestMethod(), NewRepository.class);
        if (annotation.isPresent()) {
            NewRepository newRepository = annotation.get();
            Repository repository = Repository.builder()
                    .name(newRepository.name()).isPrivate(newRepository.isPrivate()).build();
            User user = new UserController().executeGetUserData();
            new RepositoriesController().executeDeleteRepoForUserByRepoName(user.getLogin(), repository.getName());
        }
        log.info("AfterEachCallback before creating screenshot");
        if (extensionContext.getExecutionException().isPresent() && WebDriverRunner.hasWebDriverStarted()) {
            log.info("Adding screenshot to Allure");
            Allure.addAttachment("Screenshot on Failure", "image/png",
                    Files.newInputStream(Screenshots.takeScreenShotAsFile().toPath()), "png");
        }
        destroyDriver();
    }

    public void destroyDriver() {
        if (WebDriverRunner.hasWebDriverStarted()) {
            log.info("Destroying WebDriver");
            try {
                WebDriver driver = WebDriverRunner.getWebDriver();
                driver.close();
                driver.quit();
                driver = null;
                WebDriverRunner.closeWebDriver();
            } catch (Throwable exception) {
                log.info("There was an exception: {}", exception.getMessage());
            }
        }
    }

}
