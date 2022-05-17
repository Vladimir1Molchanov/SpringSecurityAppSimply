package vm.projects.SpringSecurityApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import vm.projects.SpringSecurityApp.model.*;
import vm.projects.SpringSecurityApp.repository.*;
import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserR userR;
    private final RoleR roleR;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserR userR
            , RoleR roleR
            , PasswordEncoder passwordEncoder) {
        this.userR = userR;
        this.roleR = roleR;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User findById(long id) {
        return userR.getOne(id);
    }

    @Override
    public List<User> findAll() {
        return userR.findAll();
    }

    @Override
    public void saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Set<Role> roleSet = new HashSet<>();
        roleSet.add(roleR.getById(1L));
        user.setRoles(roleSet);
        userR.save(user);
    }

    @Override
    public void updateUser(User user, Long[] roles) {
        Set<Role> roleSet = new HashSet<>();
        for (Long l : roles) {
            roleSet.add(roleR.getById(l));
        }
        Optional<User> userOpt = userR.findById(user.getId());
        User updateUser;
        updateUser = userOpt.get();
        updateUser.setFirstName(user.getFirstName());
        updateUser.setLastName(user.getLastName());
        updateUser.setAge(user.getAge());
        updateUser.setRoles(roleSet);
        userR.save(updateUser);
    }


    @Override
    public void deleteById(long id) {
        userR.deleteById(id);
    }

    @Override
    public User findByName(String name) {
        return userR.findByFirstName(name).orElse(new User());

    }
}
