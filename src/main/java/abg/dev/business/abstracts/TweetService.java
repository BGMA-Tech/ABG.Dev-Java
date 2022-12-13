package abg.dev.business.abstracts;

import abg.dev.core.utilities.results.DataResult;
import abg.dev.core.utilities.results.Result;
import abg.dev.entities.concretes.Tweet;

import java.util.List;

public interface TweetService {
    DataResult<List<Tweet>> getAll();
    DataResult<List<Tweet>> getAllByUserId(int userId);
    DataResult<Tweet> getById(int id);
    Result deleteById(int id);
    DataResult<Tweet> add(Tweet tweet);
}
