package abg.dev.dataAccess.abstracts;

import abg.dev.core.utilities.results.DataResult;
import abg.dev.entities.concretes.Tweet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TweetDao extends JpaRepository<Tweet, Integer> {
    List<Tweet> getByUserId(int userId);
}
