package screenplay.abilities;

import com.codeborne.selenide.Selenide;
import tests.TestBase;

public class UseBrowser {

    public static void openLocal(String pageName) {
        Selenide.open(TestBase.localPageUrl(pageName));
    }
}