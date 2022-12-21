package abg.dev.dataAccess.abstracts;

import abg.dev.entities.concretes.Comment;
import abg.dev.entities.concretes.Tweet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentDao extends JpaRepository<Comment, Integer> {
    List<Comment> getByTweet_Id(int id);
}

