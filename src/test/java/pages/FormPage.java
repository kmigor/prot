package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;

public class FormPage {
    private final SelenideElement emailInput = $("#dataEmail");
    private final SelenideElement nameInput = $("#dataName");
    private final SelenideElement genderOption = $("#dataGender");
    private final SelenideElement opt11 = $("#dataCheck11");
    private final SelenideElement opt12 = $("#dataCheck12");
    private final SelenideElement submitButton = $("#dataSend");
    private final SelenideElement emailErrorAllert = $("#dataAlertsHolder #emailFormatError");
    private final SelenideElement nameErrorAllert = $("#dataAlertsHolder #blankNameError");
    private final SelenideElement fillingTableEmailColumn = $("#dataTable tbody tr:last-child td:nth-child(1)");


    public FormPage fill(String email, String name, String gender) {
        emailInput.setValue(email);
        nameInput.setValue(name);
        genderOption.selectOption(gender);
        return this;
    }

    public FormPage setCheck11(boolean val) {
        opt11.setSelected(val);
        return this;
    }

    public FormPage setCheck12(boolean val) {
        opt12.setSelected(val);
        return this;
    }

    public FormPage selectRadio(int n) {
        $("#dataSelect2" + n).click();
        return this;
    }

    public FormPage submit() {
        submitButton.click();
        return this;
    }

    public void checkEmailError() {
        emailErrorAllert.shouldHave(text("Неверный формат E-Mail"));
    }

    public void checkNameError() {
        nameErrorAllert.shouldHave(text("Поле имя не может быть пустым"));
    }

    public void checkSuccessAddingEmail(String email) {
        fillingTableEmailColumn.shouldHave(text(email));
    }
}