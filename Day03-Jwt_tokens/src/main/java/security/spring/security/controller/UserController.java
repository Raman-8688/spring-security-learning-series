package security.spring.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import security.spring.security.model.Users;
import security.spring.security.service.UserService;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    //Bcrypting the password using the libray that defalut spring security have

    private BCryptPasswordEncoder encoder=new BCryptPasswordEncoder(12);

    @PostMapping("/register")
    public Users register(@RequestBody Users users){
        users.setPassword(encoder.encode(users.getPassword()));
        return userService.saveUser(users);
    }


    @PostMapping("/login")
    public String login(@RequestBody Users user){
        System.out.println("user: "+user);
       // return "Successful";
        return userService.verify(user);
    }
}
