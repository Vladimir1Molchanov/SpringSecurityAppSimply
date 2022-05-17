package app.mv.project.Service;

import  app.mv.project.dao.UserDao;
import app.mv.project.model.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserDao ud;

    public UserServiceImpl(UserDao ud) {
        this.ud = ud;
    }

    @Transactional
    @Override
    public void addUser(User user) {
        ud.addUser(user);
    }

    @Override
    public User getUserById(int id) {
        return ud.getUserById(id);
    }

    @Override
    public List<User> listUser() {
        return ud.listUser();
    }

    @Transactional
    @Override
    public void changeUser(int id, User user) {
        ud.changeUser(id, user);
    }

    @Transactional
    @Override
    public void deleteUserById(int id) {
        ud.deleteUserById(id);
    }
}

