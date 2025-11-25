package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;

public class AuthPage {
    private final SelenideElement email = $("#loginEmail");
    private final SelenideElement password = $("#loginPassword");
    private final SelenideElement loginButton = $("#authButton");
    private final SelenideElement errorAllert = $("#authAlertsHolder");
    private final SelenideElement submitButton = $("#dataSend");

    public AuthPage login(String e, String p) {
        email.setValue(e);
        password.setValue(p);
        loginButton.click();
        return this;
    }

    public void checkSuccessAuth() {
        submitButton.shouldBe(visible)
                .shouldHave(text("Добавить"));
    }

    public void checkEmailFormatError() {
        errorAllert.shouldBe(visible)
                .shouldHave(text("Неверный формат E-Mail"));
    }

    public void checkInvalidPasswordError() {
        errorAllert.shouldBe(visible)
                .shouldHave(text("Неверный E-Mail или пароль"));
    }
}
