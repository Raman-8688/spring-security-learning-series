package security.spring.security;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Hellocontorller {
    @GetMapping("")
    public String greating(HttpServletRequest request){
        return "Hellow well come to the Raman security project--> "+request.getSession().getId();
    }
}
