package screenplay.actions;

import pages.AuthPage;

public class Login {

    public static void as(String email, String pass) {
        new AuthPage().login(email, pass);
    }

}
