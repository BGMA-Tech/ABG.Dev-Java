package abg.dev.business.concretes;

import abg.dev.business.abstracts.CommentService;
import abg.dev.core.utilities.results.*;
import abg.dev.dataAccess.abstracts.CommentDao;
import abg.dev.entities.concretes.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentManager implements CommentService {
    private CommentDao commentDao;

    @Autowired
    public CommentManager(CommentDao commentDao) {
        this.commentDao = commentDao;
    }

    @Override
    public DataResult<List<Comment>> getAllByTweetId(int tweetId) {
        return new SuccessDataResult<List<Comment>>(this.commentDao.getByTweetId(tweetId));
    }

    @Override
    public Result deleteById(int id) {
        Optional<Comment> comment = this.commentDao.findById(id);
        if (comment.isPresent()) {
            this.commentDao.deleteById(id);
            return new SuccessResult("Comment deleted successfully");
        }
        return new ErrorDataResult<Comment>("Comment not found");
    }


    @Override
    public DataResult<Comment> add(Comment comment) {
        return new SuccessDataResult<Comment>(this.commentDao.save(comment), "Comment added successfully");
    }

    @Override
    public DataResult<Comment> update(Comment comment) {
        if (this.commentDao.existsById(comment.getId())) {
            this.commentDao.save(comment);
            return new SuccessDataResult<Comment>(comment, "Baasariyla guncellendi");
        }
        return new ErrorDataResult<Comment>("Yorum bulunamadi");
    }
}
