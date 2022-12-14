package abg.dev.api;

import abg.dev.business.abstracts.CommentService;
import abg.dev.business.validators.ValidationService;
import abg.dev.core.utilities.results.DataResult;
import abg.dev.core.utilities.results.Result;
import abg.dev.entities.concretes.Comment;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
@CrossOrigin
public class CommentController extends ValidationService {
    private CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/getAllByTweetId")
    DataResult<List<Comment>> getAllByTweetId(@RequestParam int tweetId) {
        return this.commentService.getAllByTweetId(tweetId);
    }

    @DeleteMapping("/deleteById")
    Result deleteById(@RequestParam int id) {
        return this.commentService.deleteById(id);
    }

    @PostMapping("/add")
    DataResult<Comment> add(@Valid @RequestBody Comment comment) {
        return this.commentService.add(comment);
    }

    @PutMapping("/update")
    ResponseEntity<?> update(@Valid @RequestBody Comment comment) {
        return ResponseEntity.ok(this.commentService.update(comment));
    }

}


