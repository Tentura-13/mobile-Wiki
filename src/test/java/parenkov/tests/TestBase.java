package parenkov.tests;

import com.codeborne.selenide.Configuration;
import io.qameta.allure.selenide.AllureSelenide;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import parenkov.config.CredentialsConfig;
import parenkov.config.HostConfig;
import parenkov.drivers.BrowserStackMobileDriver;
import parenkov.drivers.LocalMobileDriver;
import parenkov.drivers.SelenoidMobileDriver;
import parenkov.helpers.Attach;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.logevents.SelenideLogger.addListener;
import static parenkov.helpers.Attach.getSessionId;

public class TestBase {

    private static HostConfig config = ConfigFactory.create(HostConfig.class);

    public CredentialsConfig credentialsConfig;

    @BeforeAll
    public static void setup() {
        addListener("AllureSelenide", new AllureSelenide());

        switch (config.getDeviceHost()) {
            case "browserstack":
                Configuration.browser = BrowserStackMobileDriver.class.getName();
                break;
            case "selenoid":
                Configuration.browser = SelenoidMobileDriver.class.getName();
                break;
            case "emulator":
            case "smartphone":
                Configuration.browser = LocalMobileDriver.class.getName();
                break;
            default:
                Configuration.browser = LocalMobileDriver.class.getName();
                break;
        }

        Configuration.startMaximized = false;
        Configuration.browserSize = null;
        Configuration.timeout = 10000;
    }

    @BeforeEach
    public void startDriver() {
        open();
    }

    @AfterEach
    public void afterEach() {
        String sessionId = getSessionId();
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        closeWebDriver();
        Attach.attachVideo(sessionId);
    }
}