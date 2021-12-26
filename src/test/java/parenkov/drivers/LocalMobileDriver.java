package parenkov.drivers;

import com.codeborne.selenide.WebDriverProvider;
import io.appium.java_client.android.AndroidDriver;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import parenkov.config.AndroidConfig;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LocalMobileDriver implements WebDriverProvider {

    private static AndroidConfig config = ConfigFactory.create(AndroidConfig.class);

    public static URL getAppiumServerUrl() {
        try {
            return new URL(config.getUrl());
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public WebDriver createDriver(DesiredCapabilities desiredCapabilities) {

        desiredCapabilities.setCapability("platformName", config.getPlatformName());
        desiredCapabilities.setCapability("deviceName", config.getDeviceName());
        desiredCapabilities.setCapability("version", config.getOSVersion());
        desiredCapabilities.setCapability("locale", config.getLocale());
        desiredCapabilities.setCapability("language", config.getLanguage());
        desiredCapabilities.setCapability("appPackage", config.getAppPackage());
        desiredCapabilities.setCapability("appActivity", config.getAppActivity());
        desiredCapabilities.setCapability("app", getAbsolutePath(config.getAppPath()));

        return new AndroidDriver(getAppiumServerUrl(), desiredCapabilities);
    }

    private String getAbsolutePath(String filePath) {
        File file = new File(filePath);
        assertTrue(file.exists(), filePath + " not found");
        return file.getAbsolutePath();
    }
}
