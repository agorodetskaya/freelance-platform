package application.validator;

import application.model.User;
import application.service.UserService;
import application.view.UserView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UpdateUserViewValidator implements Validator {
    @Autowired
    private UserService userService;

    @Override
    public boolean supports(Class clazz) {
        return UserView.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        UserView userView = (UserView) target;
        User oldUser = userService.findById(userView.getId());

        //login
        String userViewLogin = userView.getLogin();
        boolean loginExist = userService.isLoginExist(userViewLogin);
        if (loginExist && !oldUser.getLogin().equals(userViewLogin)) {
            errors.rejectValue("login", "", "* логин уже существует");
        }

        //email
        String userViewEmail = userView.getEmail();
        boolean emailExist = userService.isEmailExist(userViewEmail);
        if (emailExist && !oldUser.getEmail().equals(userViewEmail)) {
            errors.rejectValue("email", "", "* email уже существует");
        }
    }
}
