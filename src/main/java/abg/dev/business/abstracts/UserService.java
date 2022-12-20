package abg.dev.business.abstracts;

import abg.dev.core.utilities.results.DataResult;
import abg.dev.core.utilities.results.Result;
import abg.dev.entities.concretes.User;

import java.util.List;

public interface UserService {
    DataResult<List<User>> getAll();
    DataResult<User> getById(int userId);
    DataResult<User> add(User user);
    Result delete(int userId);
    DataResult<User> update(User user);
    DataResult<User> login(User user);
}
