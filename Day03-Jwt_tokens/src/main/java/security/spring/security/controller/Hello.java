package security.spring.security.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Hello {


    @GetMapping("")
    public String great(HttpServletRequest request){
        System.out.println("this is the great method");
        return "Hello welcome to the Raman custom filter chain project"+
                "session id-->" +request.getSession().getId();
    }
}
