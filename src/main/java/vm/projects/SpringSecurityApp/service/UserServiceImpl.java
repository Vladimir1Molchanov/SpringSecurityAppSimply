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

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository
            , RoleRepository roleRepository
            , PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public void saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Set<Role> roleSet = new HashSet<>();
        roleSet.add(roleRepository.getById(1L));
        user.setRoles(roleSet);
        userRepository.save(user);
    }

    @Override
    public void updateUser(User user, Long[] roles) {
        Set<Role> roleSet = new HashSet<>();
        for (Long l : roles) {
            roleSet.add(roleRepository.getById(l));
        }
        Optional<User> userOpt = userRepository.findById(user.getId());
        User updateUser;
        updateUser = userOpt.get();
        updateUser.setFirstName(user.getFirstName());
        updateUser.setLastName(user.getLastName());
        updateUser.setAge(user.getAge());
        updateUser.setRoles(roleSet);
        userRepository.save(updateUser);
    }


    @Override
    public void deleteById(long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User findByName(String name) {
        return userRepository.findByFirstName(name).orElse(new User());

    }
}
