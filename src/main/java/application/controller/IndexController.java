package application.controller;

import application.model.Task;
import application.service.CategoryService;
import application.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class IndexController {

    @Autowired
    private TaskService taskService;
    @Autowired
    private CategoryService categoryService;

    @RequestMapping(value = "/")
    public String gotoHome() {
        return "redirect:/index";
    }

    @RequestMapping(value = "/login")
    public String gotoLogin() {
        return "login";
    }

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String gotoIndex(Model model) {
        List<Task> tasks = taskService.findAllByOrderByCreateDateAsc();
        addAttrToModel(model, tasks);
        return "index";
    }

    @RequestMapping(value = "/index", params = {"subcategory"}, method = RequestMethod.GET)
    public String sortTasksByCategory(@RequestParam("subcategory") Long[] subcategoriesId, Model model) {
        List<Task> tasksBySubcategories = taskService.findAllBySubcategoriesIdIn(subcategoriesId);
        addAttrToModel(model, tasksBySubcategories);
        return "index";
    }

    private void addAttrToModel(Model model, List<Task> tasks) {
        model.addAttribute("tasks", tasks);
        model.addAttribute("categories", categoryService.findAll());
    }
}
