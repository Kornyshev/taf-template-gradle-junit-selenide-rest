package taf.template.tests;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import taf.template.core.annotations.Flow;
import taf.template.core.annotations.Injections;
import taf.template.core.properties.PropertiesLoader;
import taf.template.fe.flow.BrowserFlow;
import taf.template.fe.flow.MainPageFlow;

@Slf4j
public class BaseTest {

    @Flow
    BrowserFlow browserFlow;
    @Flow
    MainPageFlow mainPageFlow;

    protected BaseTest() {
        Injections.inject(this);
    }

    @BeforeAll
    static void loadProperties() {
        PropertiesLoader.getInstance().readAllProperties();
    }

}
