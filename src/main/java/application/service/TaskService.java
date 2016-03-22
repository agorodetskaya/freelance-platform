package application.service;

import application.model.Task;
import application.model.TaskRequest;
import application.model.TaskRequestStatus;
import application.model.TaskStatus;
import application.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private TaskRequestService taskRequestService;

    @Transactional
    public Task saveVisitToTaskByTaskId(Long taskId) {
        Task task = findById(taskId);
        task.setVisitCount(task.getVisitCount() + 1);
        return task;
    }

    @Transactional
    public void addTaskRequest(Long taskId, TaskRequest taskRequest) {
        Task task = findById(taskId);
        if (task.getTaskStatus().equals(TaskStatus.OPEN)) {
            task.setTaskStatus(TaskStatus.TENDER);
        }
        taskRequestService.save(taskRequest);
    }

    @Transactional
    public void acceptTaskRequest(Long taskId, Long taskRequestId) {
        Task task = findById(taskId);
        task.setTaskStatus(TaskStatus.DEVELOPMENT);
        task.getTaskRequests().forEach((taskRequest) -> {
            if (taskRequest.getId() == taskRequestId) {
                taskRequest.setTaskRequestStatus(TaskRequestStatus.CHOOSE);
                task.setExecutor(taskRequest.getExecutor());
                task.setAgreementEstimateTime(taskRequest.isEstimateTimeAgreement());
                task.setEstimateTimeDays(taskRequest.getEstimateTimeDays());
                task.setEstimateTimeHours(taskRequest.getEstimateTimeHours());
                task.setAgreementPrice(taskRequest.isPriceAgreement());
                task.setPrice(taskRequest.getPrice());
            } else {
                taskRequest.setTaskRequestStatus(TaskRequestStatus.REJECTED);
            }
        });
    }

    @Transactional
    public void deleteTaskRequest(Task task, TaskRequest taskRequest) {
        task.getTaskRequests().remove(taskRequest);
        if (task.getTaskRequests().size() == 0) {
            task.setTaskStatus(TaskStatus.OPEN);
        }
        save(task);
    }

    @Transactional
    public Task save(Task task) {
        return taskRepository.save(task);
    }

    @Transactional(readOnly = true)
    public List<Task> findAllBySubcategoriesIdIn(Long[] subcategories) {
        return taskRepository.findAllBySubcategoriesIdIn(subcategories);
    }

    @Transactional(readOnly = true)
    public List<Task> findByCustomerIdOrderByCreateDateDesc(long id) {
        return taskRepository.findByCustomerIdOrderByCreateDateDesc(id);
    }

    @Transactional(readOnly = true)
    public List<Task> findByExecutorIdOrderByCreateDateDesc(long id) {
        return taskRepository.findByExecutorIdOrderByCreateDateDesc(id);
    }

    @Transactional(readOnly = true)
    public Task findById(long id) {
        return taskRepository.findOne(id);
    }

    @Transactional
    public void deleteAllIn(List<Task> tasks) {
        taskRepository.delete(tasks);
    }

    @Transactional(readOnly = true)
    public List<Task> findAllByOrderByCreateDateAsc() {
        return taskRepository.findAllByOrderByCreateDateDesc();
    }
}
