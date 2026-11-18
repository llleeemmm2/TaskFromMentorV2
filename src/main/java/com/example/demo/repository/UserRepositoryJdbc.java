package com.example.demo.repository;

import com.example.demo.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional

public class UserRepositoryJdbc {
    @PersistenceContext
    private EntityManager entityManager;

    //докинуть исключение, когда нет юзера с таким айди
    //сделал
    public User getUserById(long id) {
        User userResult = entityManager.find(User.class, id);
        if(userResult == null){
            throw new RuntimeException("User with id " + id + " not found");
        }
        return userResult;
    }

    public List<User> getAllUsers() {
        return entityManager.createQuery("from User", User.class).getResultList();

    }

    //    сходить в бд и вернуть айди(посмотреть метод)
    //    сделал в User @GeneratedValue(strategy = GenerationType.IDENTITY), чтобы было ключ автоматически был в user.id
    public Long createUser(User user) {
        entityManager.persist(user);
        return user.getId();
    }

    public User updateUser(User user) {
        return entityManager.merge(user);
    }

    public void deleteUser(long id) {
        User user = getUserById(id);
        if (user != null) {
            entityManager.remove(user);
        }
    }
}

