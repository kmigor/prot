package configs;

import org.aeonbits.owner.Config;

@Config.Sources("classpath:credentials.properties")
public interface AuthConfig extends Config {
    @Key("login")
    String login();

    @Key("password")
    String password();
}
