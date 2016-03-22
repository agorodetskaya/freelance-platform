package application.repository;

import application.model.Comment;
import application.model.Task;
import application.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByTask(Task task);

    void deleteByAuthor(User user);

    void deleteByTaskIn(List<Task> tasks);
}
