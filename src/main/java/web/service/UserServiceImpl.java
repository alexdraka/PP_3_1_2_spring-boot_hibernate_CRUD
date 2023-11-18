package web.service;

import web.model.User;
import web.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> index() {
        return userRepository.findAll();
    }
    public User show(Integer id) {
        return userRepository.findById(id).orElse(null);
    }
    public void save(User user) {
        userRepository.save(user);
    }
    public void update(Integer id, User user) {
        User userToBeUpdated = show(id);

        userToBeUpdated.setFirstName(user.getFirstName());
        userToBeUpdated.setLastName(user.getLastName());
        userToBeUpdated.setAge(user.getAge());

        userRepository.saveAndFlush(user);
    }
    public void delete(Integer id) {
        userRepository.deleteById(id);
    }
}

