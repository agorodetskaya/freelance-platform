package application.controller;

import application.model.User;
import application.service.RoleService;
import application.service.UserService;
import application.validator.UpdateUserViewValidator;
import application.view.UserView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;

import javax.validation.Valid;

@Controller
public class UserListController {
    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private UpdateUserViewValidator updateUserViewValidator;

    @RequestMapping(value = "/usermanagement", method = RequestMethod.GET)
    public String gotoUsers(Model model, @RequestParam(value = "error", required = false) boolean error) {
        model.addAttribute("users", userService.findAll());
        model.addAttribute("userView", new UserView());
        model.addAttribute("roles", roleService.findAll());
        model.addAttribute("error", error);
        return "usermanagement";
    }

    @RequestMapping(value = "/usermanagement/update", method = RequestMethod.POST)
    public String updateUser(@Valid @ModelAttribute("userView") UserView userView, BindingResult result,
                             SessionStatus status) {
        updateUserViewValidator.validate(userView, result);
        if (result.hasErrors()) {
            return "redirect:/usermanagement?error=true";
        }
        status.setComplete();
        User user = userView.toUser();
        userService.updateUser(user);
        return "redirect:/usermanagement";
    }

    @RequestMapping(value = "/usermanagement/delete/{userId}", method = RequestMethod.GET)
    public String deleteUser(@PathVariable("userId") Long id) {
        if (!userService.delete(id)) {
            return "redirect:/usermanagement?error=true";
        }
        return "redirect:/usermanagement";
    }
}
