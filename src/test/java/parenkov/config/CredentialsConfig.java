package parenkov.config;

import org.aeonbits.owner.Config;

@Config.Sources({"classpath:config/credentials.properties"})
public interface CredentialsConfig extends Config {
    @Key("username")
    String getUsername();

    @Key("password")
    String getPassword();
}
