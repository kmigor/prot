package tests;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.api.BeforeEach;
import pages.FormPage;
import pages.AuthPage;
import screenplay.abilities.UseBrowser;
import screenplay.actions.Login;

import java.util.stream.Stream;

import static io.qameta.allure.Allure.step;


public class FormTests extends TestBase {

    @BeforeEach
    void loginBefore() {
        step("Открытие веб-страницы", () -> UseBrowser.openLocal("qa-test.html"));
        step("Ввод логина и пароля из проперти конфига", () -> Login.as(auth.login(), auth.password()));
        step("Проверка успешного ввода логина и пароля", () -> new AuthPage().checkSuccessAuth());
    }

    static Stream<TestData> formData() {
        return Stream.of(
                new TestData("badmail", "Иван", "Мужской", false, false, 1, "emailError"),
                new TestData("test@mail.ru", "", "Мужской", true, false, 2, "nameError"),
                new TestData("user@site.ru", "Анна", "Женский", true, true, 3, "success")
        );
    }

    @ParameterizedTest(name = "Форма: {0}")
    @MethodSource("formData")
    void paramFormTest(TestData data) {
        FormPage form = new FormPage();

        step("Заполнение email, имени, пола", () -> form.fill(data.email, data.name, data.gender));

        step("Заполнение первого чекбокса", () -> form.setCheck11(data.opt11));
        step("Заполнение второго чекбокса", () -> form.setCheck12(data.opt12));
        step("Заполнение Radio-button", () -> form.selectRadio(data.radio));

        step("Нажатие кнопки Добавить", form::submit);

        switch (data.expected) {
            case "emailError" -> step("Проверка ошибки в email", form::checkEmailError);
            case "nameError" -> step("Проверка ошибки в имени", form::checkNameError);
            case "success" -> step("Проверка успешного добавления данных в таблицу",
                    () -> form.checkSuccessAddingEmail(data.email));
            default -> throw new RuntimeException("Некорректный ожидаемый результат");
        }
    }

    public record TestData(String email, String name, String gender,
                           boolean opt11, boolean opt12,
                           int radio, String expected) {
    }

}
