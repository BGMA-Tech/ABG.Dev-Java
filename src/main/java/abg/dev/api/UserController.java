package abg.dev.api;

import abg.dev.business.validators.ValidationService;
import abg.dev.core.utilities.results.DataResult;
import abg.dev.business.abstracts.UserService;
import abg.dev.core.utilities.results.Result;
import abg.dev.entities.concretes.User;
import abg.dev.entities.dtos.UserLoginDto;
import abg.dev.entities.dtos.UserRegisterDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@RestController
@RequestMapping("/api/users")
@CrossOrigin
public class UserController extends ValidationService {
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        super();
        this.userService = userService;
    }

    @GetMapping("/getall")
    public DataResult<List<User>> getAll(){
        return this.userService.getAll();
    }

    @GetMapping("/getbyid")
    public DataResult<User> getById(@RequestParam int id){
        return this.userService.getById(id);
    }

    @PostMapping("/add")
    public ResponseEntity<DataResult<User>> add(@Valid @RequestBody User user) {
        return ResponseEntity.ok(userService.add(user));
    }
    @PostMapping("/register")
    public ResponseEntity<DataResult<UserRegisterDto>> register(@Valid @RequestBody UserRegisterDto userRegisterDto)
            throws UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeyException {
        return ResponseEntity.ok(userService.register(userRegisterDto));
    }

    @DeleteMapping("/deletebyid")
    Result deleteById(@RequestParam int id){
        return this.userService.delete(id);
    }

    @PutMapping("/update")
    ResponseEntity<?> update(@Valid @RequestBody User user){
        return ResponseEntity.ok(this.userService.update(user));
    }

    @PostMapping("/login")
    ResponseEntity<DataResult<User>> login(@Valid @RequestBody UserLoginDto userLoginDto) throws UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeyException {
        return ResponseEntity.ok(this.userService.login(userLoginDto));
    }

}
