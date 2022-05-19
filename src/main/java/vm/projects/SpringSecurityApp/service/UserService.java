package vm.projects.SpringSecurityApp.service;

import vm.projects.SpringSecurityApp.model.User;

import java.util.List;

public interface UserService {
    List<User> findAll();

    void saveUser(User user);

    User findByName(String name);

    void updateUser(User user, Long[] roles);

    void deleteById(long id);
}
