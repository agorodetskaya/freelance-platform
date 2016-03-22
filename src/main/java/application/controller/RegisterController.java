package application.controller;

import application.model.Role;
import application.model.User;
import application.service.CustomAuthenticationProvider;
import application.service.RoleService;
import application.service.UserService;
import application.validator.RegisterUserViewValidator;
import application.view.RegisterUserView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;

import javax.validation.Valid;
import java.util.List;

@Controller
public class RegisterController {
    public static final String ROLES = "roles";
    public static final String USER = "user";
    @Autowired
    private UserService userService;
    @Autowired
    private RegisterUserViewValidator registerUserViewValidator;
    @Autowired
    private RoleService roleService;
    @Autowired
    private CustomAuthenticationProvider customAuthenticationProvider;

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String gotoSignUp(Model model) {
        List<Role> roles = roleService.findAll();
        model.addAttribute(ROLES, roles);
        model.addAttribute(USER, new RegisterUserView());
        return "register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String saveNewUser(@Valid @ModelAttribute("user") RegisterUserView userView, BindingResult result,
                              SessionStatus status) {
        registerUserViewValidator.validate(userView, result);
        if (result.hasErrors()) {
            return "register";
        }
        status.setComplete();
        User user = userView.toUser();
        userService.saveUserWithRoleOrDefault(user, userView.getRoleName());
        UsernamePasswordAuthenticationToken token =
                new UsernamePasswordAuthenticationToken(user.getLogin(), user.getPassword(),
                        user.getRole().getPermissions());
        SecurityContextHolder.getContext().setAuthentication(customAuthenticationProvider.authenticate(token));
        return "redirect:/index";
    }
}
