package configs;

import org.aeonbits.owner.Config;

@Config.Sources("classpath:web.properties")
public interface WebConfig extends Config {

    @Key("browser")
    @DefaultValue("chrome")
    String browser();

    @Key("browserSize")
    @DefaultValue("1920x1080")
    String browserSize();

}