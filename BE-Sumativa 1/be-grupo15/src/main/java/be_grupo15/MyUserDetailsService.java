package be_grupo15;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Configuration
@Service
public class MyUserDetailsService implements UserDetailsService {
    Logger logger = LoggerFactory.getLogger(MyUserDetailsService.class);

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String name) {
        User user =  userRepository.findByName(name);
        if(user==null){
            throw new UsernameNotFoundException(name);
        }
        return user;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        logger.trace("Llegué a PasswordEncoder");
        return new BCryptPasswordEncoder();
    }
    

}
