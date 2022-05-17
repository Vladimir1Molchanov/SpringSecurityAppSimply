package app.mv.project.Service;

import app.mv.project.model.User;

import java.util.List;

public interface UserService {
    void addUser(User user);

    User getUserById(int id);

    List<User> listUser();

    void changeUser(int id, User user);

    void deleteUserById(int id);
}
