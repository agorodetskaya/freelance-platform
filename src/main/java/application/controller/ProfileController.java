package application.controller;

import application.model.User;
import application.service.TaskRequestService;
import application.service.TaskService;
import application.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ProfileController {

    @Autowired
    private UserService userService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private TaskRequestService taskRequestService;

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public String detUserIdAndGotoProfile() {
        long userId = userService.getCurrentUser().getId();
        return "redirect:/profile/" + userId;
    }

    @RequestMapping(value = "/profile/{userId}", method = RequestMethod.GET)
    public String gotoProfile(@PathVariable("userId") Long userId, Model model) {
        User userProfile = userService.findById(userId);
        User currentUser = userService.getCurrentUser();
        model.addAttribute("userProfile", userProfile);
        model.addAttribute("isCurrentUser", userProfile.getId() == currentUser.getId());
        model.addAttribute("tasks", taskService.findByCustomerIdOrderByCreateDateDesc(userId));
        model.addAttribute("taskRequests", taskRequestService.findByExecutorId(userId));
        return "profile";
    }
}
