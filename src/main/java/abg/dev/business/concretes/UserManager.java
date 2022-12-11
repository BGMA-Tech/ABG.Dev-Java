package abg.dev.business.concretes;

import abg.dev.business.constants.UserMessages;
import abg.dev.business.abstracts.UserService;
import abg.dev.core.utilities.results.*;
import abg.dev.dataAccess.abstracts.UserDao;
import abg.dev.entities.concretes.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserManager implements UserService {
    private UserDao userDao;

    @Autowired
    public UserManager(UserDao userDao) {
        super();
        this.userDao = userDao;
    }

    @Override
    public DataResult<List<User>> getAll() {
        return new SuccessDataResult<List<User>>(this.userDao.findAll(),new UserMessages().getUserInformationBrought());
    }

    @Override
    public DataResult<User> getById(int userId) {
        if(this.userDao.existsById(userId)){
            return new SuccessDataResult<User>(this.userDao.findById(userId).get(),new UserMessages().getUserInformationHasBeenRetrieved());
        }
        return new ErrorDataResult<User>(new UserMessages().getUserNotFound());
    }

    @Override
    public DataResult<User> add(User user) {
        if(this.userDao.existsUserByEmail(user.getEmail())
        ){
            return new ErrorDataResult<User>(user, new UserMessages().geteMailAlreadyExists());
        }
        if(this.userDao.existsUserByUserName(user.getUserName()))
        {
            return new ErrorDataResult<User>(user, new UserMessages().getUserNameAlreadyExists());
        }
        this.userDao.save(user);
        return new SuccessDataResult<User>(user, new UserMessages().getAddUser());
    }

    @Override
    public Result delete(int userId) {
        if(!this.userDao.existsById(userId)){
            return new ErrorResult(new UserMessages().getUserNotFound());
        }
        this.userDao.deleteById(userId);
        return new SuccessResult(new UserMessages().getDeleteUser());
    }

    @Override
    public DataResult<User> update(User user) {
        if(!this.userDao.existsById(user.getId())){
           return new ErrorDataResult<User>(new UserMessages().getUserNotFound());
        }
        this.userDao.save(user);
        return new SuccessDataResult<User>(user,new UserMessages().getUpdateUser());
    }
}
