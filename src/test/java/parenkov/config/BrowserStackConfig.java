package parenkov.config;

import org.aeonbits.owner.Config;

@Config.Sources({"classpath:config/browserstack.properties"})
public interface BrowserStackConfig extends Config {
    @Key("url")
    String getUrl();

    @Key("user")
    String getUser();

    @Key("key")
    String getKey();

    @Key("appUrl")
    String getAppUrl();

    @Key("device")
    String getDevice();

    @Key("osVersion")
    String getOSVersion();

    @Key("project")
    String getProjectName();

    @Key("build")
    String getBuildName();

    @Key("name")
    String getRunName();
}
