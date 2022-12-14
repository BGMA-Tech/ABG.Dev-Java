package abg.dev.business.abstracts;

import abg.dev.core.utilities.results.DataResult;
import abg.dev.core.utilities.results.Result;
import abg.dev.entities.concretes.Comment;
import abg.dev.entities.concretes.Tweet;

import java.util.List;

public interface CommentService {
    DataResult<List<Comment>> getAllByTweetId(int tweetId);
    Result deleteById(int id);
    DataResult<Comment> add(Comment comment);
    DataResult<Comment> update(Comment comment);
}
