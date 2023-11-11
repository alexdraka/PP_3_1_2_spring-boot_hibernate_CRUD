package web.dao;

import web.model.User;

import java.util.List;

public interface UserDAO {
    List<User> index();
    User show(Integer id);
    void save(User user);
    void update(Integer id, User user);
    void delete(Integer id);

}
