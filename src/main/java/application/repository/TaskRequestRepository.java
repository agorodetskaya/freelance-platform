package application.repository;

import application.model.Task;
import application.model.TaskRequest;
import application.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface TaskRequestRepository extends JpaRepository<TaskRequest, Long> {
    List<TaskRequest> findAllByTask(Task task);

    List<TaskRequest> findByExecutorIdOrderByCreateDateDesc(Long id);

    List<TaskRequest> findByTaskIn(Collection<Task> task);

    void deleteByExecutor(User user);
}
