package security.spring.security.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import security.spring.security.service.JWtService;
import security.spring.security.service.MyUserDetails;

import java.io.IOException;
@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    JWtService jWtService;

    @Autowired
    ApplicationContext context;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
       //Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqeW8iLCJpYXQiOjE3NjExNTI0MzAsImV4cCI6MTc2MTE1MjUzOH0.KISSqrXJ_1ZsFlGMTmXEfiAFVDusj8wRvo0McOjgEO4

        String authHeader= request.getHeader("Authorization");
        String token = null;
        String username = null;

        if( authHeader != null && authHeader.startsWith("Bearer ")){
            token=authHeader.substring(7);
            username=jWtService.extractUserName(token);
        }

        if(username !=null && SecurityContextHolder.getContext().getAuthentication() == null){

            UserDetails userDetail= context.getBean(MyUserDetails.class).loadUserByUsername(username);

            if(jWtService.validateToken(token,userDetail)){

                UsernamePasswordAuthenticationToken authToken=new UsernamePasswordAuthenticationToken(userDetail,null,userDetail.getAuthorities());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);

            }
        }

        filterChain.doFilter(request,response);
    }
}
