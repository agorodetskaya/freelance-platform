package application.view;

import application.model.User;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

public class RegisterUserView extends UserView {
    @NotEmpty(message = "* заполните поле")
    @Length(min = 4, max = 25, message = "* от 4 до 25 символов")
    protected String password;
    @NotEmpty(message = "* заполните поле")
    @Length(min = 4, max = 25, message = "* от 4 до 25 символов")
    protected String password2;

    public User toUser() {
        User user = super.toUser();
        user.setPassword(password);
        return user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }
}
