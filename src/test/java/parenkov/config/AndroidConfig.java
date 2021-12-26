package parenkov.config;

import org.aeonbits.owner.Config;

@Config.Sources({"classpath:config/${deviceHost}.properties"})
public interface AndroidConfig extends Config {
    @Key("platformName")
    String getPlatformName();

    @Key("url")
    String getUrl();

    @Key("deviceName")
    String getDeviceName();

    @Key("osVersion")
    String getOSVersion();

    @Key("locale")
    String getLocale();

    @Key("language")
    String getLanguage();

    @Key("appPackage")
    String getAppPackage();

    @Key("appActivity")
    String getAppActivity();

    @Key("appPath")
    String getAppPath();
}
