package application.controller;

import application.model.Task;
import application.model.User;
import application.service.TaskService;
import application.service.UserService;
import application.validator.TaskViewValidator;
import application.view.TaskView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;

import javax.validation.Valid;

@Controller
public class NewtaskController {

    @Autowired
    private TaskService taskService;

    @Autowired
    private UserService userService;

    @Autowired
    private TaskViewValidator taskViewValidator;


    @RequestMapping(value = "/newtask", method = RequestMethod.GET)
    public String gotoNewtask(Model model) {
        model.addAttribute("task", new TaskView());
        return "newtask";
    }

    @RequestMapping(value = "/newtask", method = RequestMethod.POST)
    public String createTask(@Valid @ModelAttribute("task") TaskView taskView, BindingResult result,
                             SessionStatus status) {
        taskViewValidator.validate(taskView, result);
        if (result.hasErrors()) {
            return "newtask";
        }
        status.setComplete();
        Task task = taskView.toTask();
        User currentUser = userService.getCurrentUser();
        task.setCustomer(currentUser);
        taskService.save(task);
        return "redirect:/index";
    }


}
