package application.repository;

import application.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findAllByOrderByCreateDateDesc();

    List<Task> findAllBySubcategoriesIdIn(Long[] subcategories);

    List<Task> findByCustomerIdOrderByCreateDateDesc(Long id);

    List<Task> findByExecutorIdOrderByCreateDateDesc(Long id);
}