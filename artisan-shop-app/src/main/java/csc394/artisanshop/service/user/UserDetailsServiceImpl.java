package csc394.artisanshop.service.user;

import csc394.artisanshop.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
//@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {


    @Autowired
    private UserService userService;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.getByUserName(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return new JwtUserDetails(user);
    }

    public UserDetails loadUserById(int id) {
        User user = userService.getById(id);
        return new JwtUserDetails(user);
    }

}
