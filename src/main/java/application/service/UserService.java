package application.service;

import application.model.Role;
import application.model.Task;
import application.model.TaskStatus;
import application.model.User;
import application.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleService roleService;
    @Autowired
    private TaskService taskService;
    @Autowired
    private TaskRequestService taskRequestService;
    @Autowired
    private CommentService commentService;

    @Transactional
    public User saveUserWithRoleOrDefault(User user, String roleName) {
        Role role = roleService.findByName(roleName);
        if (role == null) {
            role = roleService.findByName("USER");
        }
        user.setRole(role);
        return save(user);
    }

    @Transactional
    public void updateUser(User user) {
        User dbUser = findById(user.getId());
        user.setRole(dbUser.getRole());
        user.setPassword(dbUser.getPassword());
        save(user);
    }

    @Transactional
    public User save(User user) {
        return userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public User findByLogin(String login) {
        return userRepository.findByLogin(login);
    }

    @Transactional(readOnly = true)
    public User findById(Long id) {
        return userRepository.findOne(id);
    }

    @Transactional(readOnly = true)
    public boolean isLoginExist(String login) {
        User user = findByLogin(login);
        return user != null;
    }

    @Transactional(readOnly = true)
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Transactional(readOnly = true)
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Transactional(readOnly = true)
    public boolean isEmailExist(String email) {
        User user = findByEmail(email);
        return user != null;
    }

    @Transactional
    public boolean delete(Long id) {
        User user = findById(id);
        List<Task> tasksCustomer = taskService.findByCustomerIdOrderByCreateDateDesc(id);
        List<Task> tasksExecutor = taskService.findByExecutorIdOrderByCreateDateDesc(id);
        if (hasInDevelopmentTask(tasksCustomer) || hasInDevelopmentTask(tasksExecutor)) {
            return false;
        }
        tasksExecutor.forEach(task -> {
            task.setExecutor(null);
            taskService.save(task);
        });
        taskService.deleteAllIn(tasksCustomer);
        taskRequestService.deleteByExecutor(user);
        commentService.deleteByAuthor(user);
        userRepository.delete(user);
        return true;
    }

    private boolean hasInDevelopmentTask(List<Task> tasks) {
        return tasks.stream().filter(task -> task.getTaskStatus().equals(TaskStatus.DEVELOPMENT)).findAny().isPresent();
    }

    @Transactional(readOnly = true)
    public User getCurrentUser() {
        String login = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        return findByLogin(login);
    }
}
