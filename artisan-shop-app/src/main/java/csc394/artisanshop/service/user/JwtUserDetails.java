package csc394.artisanshop.service.user;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

import csc394.artisanshop.model.User;

public class JwtUserDetails implements UserDetails{

    private User user;

    public JwtUserDetails(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
         return Collections.singleton(new SimpleGrantedAuthority("USER"));  //can change to other roles
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUserName();
    }

    @Override
    public boolean isEnabled() {
        return true;                    //Logic implementation is needed
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;                   //Logic implementation is needed
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;                    //Logic implementation is needed
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;                    //Logic implementation is needed
    }





    
}
