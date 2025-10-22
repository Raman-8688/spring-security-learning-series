package security.spring.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import security.spring.security.model.Users;
import security.spring.security.repo.UserRepo;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;

    public Users saveUser(Users users){

       return userRepo.save(users);
    }
}
