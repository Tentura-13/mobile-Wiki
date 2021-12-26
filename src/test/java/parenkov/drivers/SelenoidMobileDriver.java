package parenkov.drivers;

import com.codeborne.selenide.WebDriverProvider;
import io.appium.java_client.android.AndroidDriver;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import parenkov.config.AndroidConfig;

import java.net.MalformedURLException;
import java.net.URL;

public class SelenoidMobileDriver implements WebDriverProvider {

    private static AndroidConfig config = ConfigFactory.create(AndroidConfig.class);

    public static URL getSelenoidUrl() {
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
        desiredCapabilities.setCapability("enableVNC", true);
        desiredCapabilities.setCapability("enableVideo", true);
        desiredCapabilities.setCapability("appPackage", config.getAppPackage());
        desiredCapabilities.setCapability("appActivity", config.getAppActivity());
        desiredCapabilities.setCapability("app", getAppUrl());

        return new AndroidDriver(getSelenoidUrl(), desiredCapabilities);
    }

    private URL getAppUrl() {
        try {
            return new URL(config.getAppPath());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
