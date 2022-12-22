package abg.dev.business.abstracts;

import abg.dev.core.utilities.results.DataResult;
import abg.dev.core.utilities.results.Result;
import abg.dev.entities.concretes.User;
import abg.dev.entities.dtos.UserLoginDto;
import abg.dev.entities.dtos.UserRegisterDto;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public interface UserService {
    DataResult<List<User>> getAll();
    DataResult<User> getById(int userId);
    DataResult<User> add(User user);
    Result delete(int userId);
    DataResult<User> update(User user);
    DataResult<User> login(UserLoginDto userLoginDto) throws UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeyException;
    DataResult<UserRegisterDto> register(UserRegisterDto userRegisterDto) throws UnsupportedEncodingException, NoSuchAlgorithmException;
}
