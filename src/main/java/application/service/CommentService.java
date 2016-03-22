package application.service;

import application.model.Comment;
import application.model.Task;
import application.model.User;
import application.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;

    @Transactional(readOnly = true)
    public List<Comment> findByTask(Task task) {
        return commentRepository.findAllByTask(task);
    }

    @Transactional
    public void deleteAllByAuthor(User user) {
        commentRepository.deleteByAuthor(user);
    }

    @Transactional
    public void deleteAllByTaskIn(List<Task> tasks) {
        commentRepository.deleteByTaskIn(tasks);
    }

    @Transactional
    public Comment save(Comment comment) {
        return commentRepository.save(comment);
    }

    public void deleteByAuthor(User user) {
        commentRepository.deleteByAuthor(user);
    }
}
