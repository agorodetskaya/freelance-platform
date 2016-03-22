package application.service;

import application.model.Task;
import application.model.TaskRequest;
import application.model.User;
import application.repository.TaskRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TaskRequestService {
    @Autowired
    private TaskRequestRepository taskRequestRepository;

    @Transactional(readOnly = true)
    public List<TaskRequest> findByExecutorId(Long id) {
        return taskRequestRepository.findByExecutorIdOrderByCreateDateDesc(id);
    }

    @Transactional(readOnly = true)
    public List<TaskRequest> findByTask(Task task) {
        return taskRequestRepository.findAllByTask(task);
    }

    @Transactional(readOnly = true)
    public List<TaskRequest> findByTaskIn(List<Task> tasks) {
        return taskRequestRepository.findByTaskIn(tasks);
    }

    @Transactional
    public void deleteAllIn(List<TaskRequest> taskRequests) {
        taskRequestRepository.delete(taskRequests);
    }

    @Transactional
    public void deleteByExecutor(User user) {
        taskRequestRepository.deleteByExecutor(user);
    }

    @Transactional(readOnly = true)
    public TaskRequest findById(Long id) {
        return taskRequestRepository.findOne(id);
    }

    @Transactional
    public void save(TaskRequest taskRequest) {
        taskRequestRepository.save(taskRequest);
    }
}
