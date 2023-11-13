package web.service;

import web.dao.UserDAO;
import web.dao.UserDAOImpl;
import web.model.User;

import java.util.List;

public class UserServiceImpl implements UserService {
    private final UserDAO userDAO = new UserDAOImpl();

    public  List<User> index() {
        return userDAO.index();
    }
    public User show(Integer id) {
        return userDAO.show(id);
    }
    public void save(User user) {
        userDAO.save(user);
    }
    public void update(Integer id, User user) {
        userDAO.update(id, user);
    }
    public void delete(Integer id) {
        userDAO.delete(id);
    }
}
