package application.validator;

import application.service.UserService;
import application.view.RegisterUserView;
import application.view.UserView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class RegisterUserViewValidator implements Validator {
    @Autowired
    private UserService userService;

    @Override
    public boolean supports(Class clazz) {
        return UserView.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        RegisterUserView userView = (RegisterUserView) target;
        //password
        if (!(userView.getPassword().equals(userView.getPassword2()))) {
            errors.rejectValue("password", "notmatch.password", "* пароли не совпадают");
        }

        //login
        String userViewLogin = userView.getLogin();
        boolean loginExist = userService.isLoginExist(userViewLogin);
        if (loginExist) {
            errors.rejectValue("login", "required.login", "* логин уже существует");
        }

        //email
        String userViewEmail = userView.getEmail();
        boolean emailExist = userService.isEmailExist(userViewEmail);
        if (emailExist) {
            errors.rejectValue("email", "required.email", "* email уже существует");
        }
    }
}
