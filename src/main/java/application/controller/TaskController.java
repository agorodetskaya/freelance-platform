package application.controller;

import application.model.Comment;
import application.model.Task;
import application.model.TaskRequest;
import application.model.TaskStatus;
import application.service.CommentService;
import application.service.TaskRequestService;
import application.service.TaskService;
import application.service.UserService;
import application.validator.TaskRequestViewValidator;
import application.view.TaskRequestView;
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

import java.util.Date;

@Controller
public class TaskController {

    @Autowired
    private TaskService taskService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private UserService userService;

    @Autowired
    private TaskRequestService taskRequestService;

    @Autowired
    private TaskRequestViewValidator taskRequestViewValidator;

    @RequestMapping(value = "/task/{taskId}", method = RequestMethod.GET)
    public String gotoTask(@PathVariable("taskId") Long taskId, Model model) {
        Task task = taskService.saveVisitToTaskByTaskId(taskId);
        saveAttrToModel(task, model);
        return "task";
    }

    @RequestMapping(value = "/task/{taskId}/comment", method = RequestMethod.GET)
    public String doComment(@PathVariable("taskId") Long taskId, Model model) {
        saveAttrToModel(taskService.findById(taskId), model);
        model.addAttribute("leaveComment", true);
        return "task";
    }

    @RequestMapping(value = "/task/{taskId}/taskrequest", method = RequestMethod.GET)
    public String doTaskRequest(@PathVariable("taskId") Long taskId, Model model) {
        saveAttrToModel(taskService.findById(taskId), model);
        model.addAttribute("leaveTaskRequest", true);
        model.addAttribute("taskRequest", new TaskRequestView());
        return "task";
    }

    @RequestMapping(value = "/task/{taskId}/comment", method = RequestMethod.POST)
    public String createComment(@PathVariable("taskId") Long taskId,
                                @RequestParam("commentContent") String commentContent) {
        if (commentContent.equals("")) {
            return "redirect:/task/{taskId}";
        }
        saveComment(commentContent, taskService.findById(taskId));
        return "redirect:/task/{taskId}";
    }

    @RequestMapping(value = "/task/{taskId}/taskrequest", method = RequestMethod.POST)
    public String createTaskRequest(@PathVariable("taskId") Long taskId,
                                    @ModelAttribute("taskRequest") TaskRequestView taskRequestView,
                                    BindingResult result, SessionStatus status) {
        taskRequestViewValidator.validate(taskRequestView, result);
        if (result.hasErrors()) {
            return "redirect:/task/{taskId}";
        }
        status.setComplete();
        TaskRequest taskRequest = populateTaskRequest(taskRequestView, taskId);
        taskService.addTaskRequest(taskId, taskRequest);
        return "redirect:/task/{taskId}";
    }

    @RequestMapping(value = "/task/{taskId}/accept/{taskRequestId}", method = RequestMethod.GET)
    public String acceptTaskRequest(@PathVariable("taskId") Long taskId,
                                    @PathVariable("taskRequestId") Long taskRequestId) {
        if (userService.getCurrentUser().getId() == taskService.findById(taskId).getCustomer().getId()) {
            taskService.acceptTaskRequest(taskId, taskRequestId);
        }
        return "redirect:/task/{taskId}";
    }

    @RequestMapping(value = "task/{taskId}/delete/{taskRequestId}", method = RequestMethod.GET)
    public String deleteTaskRequest(@PathVariable("taskId") Long taskId,
                                    @PathVariable("taskRequestId") Long taskRequestId) {
        Task task = taskService.findById(taskId);
        TaskRequest taskRequest = taskRequestService.findById(taskRequestId);
        if (task.getTaskStatus().equals(TaskStatus.CLOSE) || task.getTaskStatus().equals(TaskStatus.DEVELOPMENT)) {
            return "redirect:/task/{taskId}";
        }
        taskService.deleteTaskRequest(task, taskRequest);
        return "redirect:/task/{taskId}";
    }


    private void saveAttrToModel(Task task, Model model) {
        model.addAttribute("user", userService.getCurrentUser());
        model.addAttribute("task", task);
        model.addAttribute("comments", commentService.findByTask(task));
        model.addAttribute("taskRequests", taskRequestService.findByTask(task));
    }

    private void saveComment(String commentContent, Task task) {
        Comment comment = new Comment();
        comment.setContent(commentContent);
        comment.setTask(task);
        comment.setAuthor(userService.getCurrentUser());
        comment.setCreateDate(new Date());
        commentService.save(comment);
    }

    private TaskRequest populateTaskRequest(TaskRequestView taskRequestView, Long taskId) {
        TaskRequest taskRequest = taskRequestView.toTaskRequest();
        taskRequest.setExecutor(userService.getCurrentUser());
        taskRequest.setTask(taskService.findById(taskId));
        return taskRequest;
    }
}
