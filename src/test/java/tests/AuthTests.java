package tests;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import pages.AuthPage;
import screenplay.abilities.UseBrowser;
import screenplay.actions.Login;

import static io.qameta.allure.Allure.step;


public class AuthTests extends TestBase {


    @ParameterizedTest(name = "Авторизация: {0} / {1}")
    @CsvSource({
            "test@protei.ru, test",
            "wrongemail, test",
            "test@protei.ru, wrongpass",
            "invalid@protei.ru, invalid"
    })
    void authParameterized(String login, String password) {

        step("Открытие веб-страницы", () -> UseBrowser.openLocal("qa-test.html"));
        step("Ввод логина и пароля", () -> Login.as(login, password));

        if (login.equals("test@protei.ru") && password.equals("test")) {
            step("Проверка успешного ввода логина и пароля", () -> new AuthPage().checkSuccessAuth());
        } else if (!login.contains("@")) {
            step("Проверка ошибки в формате логина", () -> new AuthPage().checkEmailFormatError());
        } else {
            step("Проверка ошибки в логине/пароле", () -> new AuthPage().checkInvalidPasswordError());
        }
    }

}
