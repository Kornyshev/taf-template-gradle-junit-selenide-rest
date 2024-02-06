package taf.template.fe.flow;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import taf.template.core.annotations.DefaultUrl;
import taf.template.fe.pom.PageObject;
import taf.template.fe.pom.Pages;

@Slf4j
public class BrowserFlow {

    @Step("Open browser page")
    public void open(Pages pageObjectName, String... params) {
        open(getPageObjectClass(pageObjectName.getClassName()), params);
    }

    public void open(Class<? extends PageObject> pageObject, String... params) {
        Selenide.open(getDefaultUrl(pageObject, params), pageObject);
        waitUntilPageIsLoaded(pageObject);
    }

    @SuppressWarnings("unchecked")
    public Class<PageObject> getPageObjectClass(String pageObjectName) {
        try {
            return (Class<PageObject>) Class.forName("taf.template.fe.pom." +
                    pageObjectName.replaceAll("\\s", StringUtils.EMPTY));
        } catch (ClassNotFoundException e) {
            log.error("Unable to map page object name to a class!", e);
            throw new IllegalStateException("Unable to map page object name to a class!");
        }
    }

    protected String getDefaultUrl(Class<? extends PageObject> pageObject, String... params) {
        String url = pageObject.getAnnotation(DefaultUrl.class).url();
        return formatURL(url, params);
    }

    public void waitUntilPageIsLoaded(Class<? extends PageObject> pageObjectClass) {
        Selenide.page(pageObjectClass).pageIsLoaded();
    }

    public String formatURL(String urlTemplate, String... params) {
        long placeholdersCount = urlTemplate.chars().filter(ch -> ch == '{').count();
        if (params == null || placeholdersCount != params.length) {
            throw new IllegalArgumentException("The number of placeholders and parameters does not match.");
        }
        String formattedUrl = urlTemplate;
        for (String param : params) {
            formattedUrl = formattedUrl.replaceFirst("\\{[^}]*}", param);
        }
        return formattedUrl;
    }

}
