package security.spring.security;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class StudentsController {

    List<Students> students=new ArrayList<>(List.of(new Students(1,"Raman",120),
                                                     new Students(2,"Hari",230)
                                                     ));

    @GetMapping("/students")
    public List<Students> getStudents(){
        return students;
    }

    @GetMapping("/great")
    public String great(){
        return "well come to the Raman app hear you can see the first project of spring security"+
                "this is just for demo"+ " in this if you trying to change or add like post put delete operations "+
                " if you want to perform you must remove the spring security dependency it's not accept that the"
                +" Get only we can perform ";
    }


    @PostMapping("/students")
    public Students addStudents(@RequestBody Students student)
    {
        students.add(student);// hear students is the List of Students
        return student;//student is the user given student
    }


    @GetMapping("csrf-token")
    public CsrfToken getcsrfToken(HttpServletRequest request){
        return (CsrfToken) request.getAttribute("_csrf");
    }
}
