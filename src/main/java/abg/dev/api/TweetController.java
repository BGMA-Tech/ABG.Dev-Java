package abg.dev.api;

import abg.dev.business.abstracts.TweetService;
import abg.dev.business.validators.ValidationService;
import abg.dev.core.utilities.results.DataResult;
import abg.dev.core.utilities.results.Result;
import abg.dev.entities.concretes.Tweet;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tweets")
@CrossOrigin
public class TweetController extends ValidationService {
    private TweetService tweetService;

    @Autowired
    public TweetController(TweetService tweetService) {
        this.tweetService = tweetService;
    }

    @GetMapping("/getAll")
    DataResult<List<Tweet>> getAll() {
        return this.tweetService.getAll();
    }

    @GetMapping("/getAllByUserId")
    DataResult<List<Tweet>> getAllByUserId(@RequestParam int userId) {
        return this.tweetService.getAllByUserId(userId);
    }

    @GetMapping("/getById")
    DataResult<Tweet> getById(@RequestParam int id) {
        return this.tweetService.getById(id);
    }

    @DeleteMapping("/deleteById")
    Result deleteById(@RequestParam int id) {
        return this.tweetService.deleteById(id);
    }

    @PostMapping("/add")
    DataResult<Tweet> add(@Valid @RequestBody Tweet tweet) {
        return this.tweetService.add(tweet);
    }
}
