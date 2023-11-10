package web.dao;

import jakarta.persistence.EntityManager;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import web.model.User;
import web.util.Util;

import java.util.ArrayList;
import java.util.List;

import static web.util.Util.getEntityManager;


@Component
public class UserDAOImpl implements UserDAO {
    private static final EntityManager entityManager = Util.getEntityManager();
    private List<User> people;

    public List<User> index() {
        entityManager.getTransaction().begin();
        people = entityManager.createQuery("from User").getResultList();
        entityManager.getTransaction().commit();

        return people;
    }
    public User show(Integer id) {
        return people.stream().filter(user -> user.getId() == id).findAny().orElse(null);
    }
    public void save(User user) {
        entityManager.getTransaction().begin();
        entityManager.persist(user);
        entityManager.getTransaction().commit();
    }
    public void update(Integer id, String updatedFirstName, String updatedLastName, int updatedAge) {
        User userToBeUpdated = show(id);

        userToBeUpdated.setFirstName(updatedFirstName);
        userToBeUpdated.setLastName(updatedLastName);
        userToBeUpdated.setAge(updatedAge);
    }
    public void delete(Integer id) {
        entityManager.getTransaction().begin();
        User user = entityManager.find(User.class, id);
        entityManager.remove(user);
        entityManager.getTransaction().commit();
    }
}
