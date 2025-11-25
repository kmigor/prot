package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import configs.WebConfig;
import configs.AuthConfig;
import helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import java.util.Objects;

import static com.codeborne.selenide.Selenide.clearBrowserCookies;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class TestBase {

    protected static WebConfig webConfig;
    protected static AuthConfig auth;

    @BeforeAll
    static void setup() {
        webConfig = ConfigFactory.create(WebConfig.class);
        auth = ConfigFactory.create(AuthConfig.class);

        Configuration.browser = webConfig.browser();
        Configuration.browserSize = webConfig.browserSize();
        Configuration.headless = false;
    }

    public static String localPageUrl(String fileName) {
        return Objects.requireNonNull(
                TestBase.class.getClassLoader().getResource("localResource/" + fileName)
        ).toExternalForm();
    }

    @BeforeEach
    void setupSelenideListener() {
        SelenideLogger.addListener("AllureSelenide",
                new AllureSelenide().screenshots(true).savePageSource(true));
    }

    @AfterEach
    void tearDown() {
        try {
            Attach.screenshotAs("LastScreenshot");
            if (!Objects.equals(Configuration.browser, "firefox")) {
                Attach.pageSource();
            }
            clearBrowserCookies();
            closeWebDriver();
        } catch (Exception ignored) {
        }
    }
}
