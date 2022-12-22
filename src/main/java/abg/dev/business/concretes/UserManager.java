package abg.dev.business.concretes;

import abg.dev.business.constants.UserMessages;
import abg.dev.business.abstracts.UserService;
import abg.dev.core.utilities.results.*;
import abg.dev.core.utilities.security.hashing.HashingHelper;
import abg.dev.dataAccess.abstracts.UserDao;
import abg.dev.entities.concretes.User;
import abg.dev.entities.dtos.UserLoginDto;
import abg.dev.entities.dtos.UserRegisterDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
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

    @Override
    public DataResult<User> login(UserLoginDto userLoginDto)
            throws UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeyException {
        User userToCheck = this.userDao.getByEmail(userLoginDto.getEmail());
        if (userToCheck == null) {
            return new ErrorDataResult<User>("User not found.");
        }
        if (!HashingHelper.verifyPasswordHash(userLoginDto.getPassword(), userToCheck.getPasswordHash()))
        {
            return new ErrorDataResult<User>(userToCheck,"Password error");
        }
        return new SuccessDataResult<User>(userToCheck,"Succesfull login");
    }

    @Override
    public DataResult<UserRegisterDto> register(UserRegisterDto userRegisterDto) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        if(this.userDao.existsUserByEmail(userRegisterDto.getEmail())
        ){
            return new ErrorDataResult<UserRegisterDto>(userRegisterDto, new UserMessages().geteMailAlreadyExists());
        }
        if(this.userDao.existsUserByUserName(userRegisterDto.getUserName()))
        {
            return new ErrorDataResult<UserRegisterDto>(userRegisterDto, new UserMessages().getUserNameAlreadyExists());
        }

        String passwordHash = HashingHelper.createPasswordHash(userRegisterDto.getPassword());

        User user = new User();
        user.setUserName(userRegisterDto.getUserName());
        user.setEmail(userRegisterDto.getEmail());
        user.setPassword(userRegisterDto.getPassword());
        user.setPasswordHash(passwordHash);
        this.userDao.save(user);

        return new SuccessDataResult<UserRegisterDto>(userRegisterDto, new UserMessages().getAddUser());
    }
}
