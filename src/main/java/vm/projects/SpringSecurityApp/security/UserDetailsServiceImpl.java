package vm.projects.SpringSecurityApp.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import vm.projects.SpringSecurityApp.model.Role;
import vm.projects.SpringSecurityApp.repository.UserR;

import java.util.*;

@Service("userDetailsServiceImpl")
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserR userR;

    @Autowired
    public UserDetailsServiceImpl(UserR userR) {
        this.userR = userR;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<vm.projects.SpringSecurityApp.model.User> securityUserOpt = userR.findByFirstName(username);

        vm.projects.SpringSecurityApp.model.User user = securityUserOpt.get();
        Set<GrantedAuthority> authority = new HashSet<>();
        for (Role role : user.getRoles()) {
            authority.add(new SimpleGrantedAuthority(role.getRoleName()));
        }
        return new User(
                user.getUsername(),
                user.getPassword(),
                authority);
    }
}
