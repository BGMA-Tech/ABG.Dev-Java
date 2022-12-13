package abg.dev.business.concretes;

import abg.dev.business.abstracts.TweetService;
import abg.dev.core.utilities.results.*;
import abg.dev.dataAccess.abstracts.TweetDao;
import abg.dev.entities.concretes.Tweet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TweetManager implements TweetService {
    private TweetDao tweetDao;

    @Autowired
    public TweetManager(TweetDao tweetDao) {
        this.tweetDao = tweetDao;
    }

    @Override
    public DataResult<List<Tweet>> getAll() {
        return new SuccessDataResult<List<Tweet>>(this.tweetDao.findAll());
    }

    @Override
    public DataResult<List<Tweet>> getAllByUserId(int userId) {
        return new SuccessDataResult<List<Tweet>>(this.tweetDao.getByUserId(userId));
    }

    @Override
    public DataResult<Tweet> getById(int id) {
        Optional<Tweet> tweet = this.tweetDao.findById(id);
        if (tweet.isPresent()) {
            return new SuccessDataResult<Tweet>(tweet.get());
        }
        return new ErrorDataResult<Tweet>("Tweet not found");
    }

    @Override
    public Result deleteById(int id) {
        Optional<Tweet> tweet = this.tweetDao.findById(id);
        if (tweet.isPresent()) {
            this.tweetDao.deleteById(id);
            return new SuccessResult("Tweet deleted successfully");
        }
        return new ErrorDataResult<Tweet>("Tweet not found");
    }

    @Override
    public DataResult<Tweet> add(Tweet tweet) {
        return new SuccessDataResult<Tweet>(this.tweetDao.save(tweet), "Tweet added successfully");
    }
}
