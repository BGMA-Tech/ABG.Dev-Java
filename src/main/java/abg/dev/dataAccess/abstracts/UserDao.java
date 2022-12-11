package abg.dev.dataAccess.abstracts;

import abg.dev.entities.concretes.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User,Integer> {
    boolean existsUserByEmail(String email);

    boolean existsUserByUserName(String userName);

}
