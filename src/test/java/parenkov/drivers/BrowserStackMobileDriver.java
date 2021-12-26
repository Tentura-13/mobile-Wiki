package parenkov.drivers;

import com.codeborne.selenide.WebDriverProvider;
import io.appium.java_client.android.AndroidDriver;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import parenkov.config.BrowserStackConfig;

import java.net.MalformedURLException;
import java.net.URL;

public class BrowserStackMobileDriver implements WebDriverProvider {

    private static BrowserStackConfig config = ConfigFactory.create(BrowserStackConfig.class);

    public static URL getBrowserstackUrl() {
        try {
            return new URL(config.getUrl());
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public WebDriver createDriver(DesiredCapabilities desiredCapabilities) {

        desiredCapabilities.setCapability("browserstack.user", config.getUser());
        desiredCapabilities.setCapability("browserstack.key", config.getKey());
        desiredCapabilities.setCapability("app", config.getAppUrl());
        desiredCapabilities.setCapability("device", config.getDevice());
        desiredCapabilities.setCapability("os_version", config.getOSVersion());
        desiredCapabilities.setCapability("project", config.getProjectName());
        desiredCapabilities.setCapability("build", config.getBuildName());
        desiredCapabilities.setCapability("name", config.getRunName());

        return new AndroidDriver(getBrowserstackUrl(), desiredCapabilities);
    }
}
