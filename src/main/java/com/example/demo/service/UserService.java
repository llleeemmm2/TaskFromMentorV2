package com.example.demo.service;

import com.example.demo.repository.UserRepository;
import com.example.demo.repository.UserRepositoryJdbc;
import com.example.demo.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepositoryJdbc userRepositoryJdbc;
    private final UserRepository userRepository;

//    JPA
    //Посмотреть как можно сделать более красиво
    //сделал через метод orElseThrow, он возвращает значение, если оно не нул, иначе exceptionSupplier.get()
    public User getByIdJpa(Long id) {
        if(id == null){
            throw new RuntimeException();
        }
        Optional<User> userCandidate = userRepository.findById(id);
        return userCandidate.orElseThrow();
//        if (userCandidate.isEmpty()) {
//            throw new RuntimeException();
//        }
//        return userCandidate.get();
    }

    // первая реализация возможна благодаря JpaRepository<User, Long>
    public List<User> getAll(){
        return userRepository.findAll();
    }

    public List<User> getAllJpa(){
        List<User> list = new ArrayList<>();
        for(User user : userRepository.findAll()){
            list.add(user);
        }
        return list;
    }

    public void createJpa(User user){
        if(user.getId() == null) {
            userRepository.save(user);
        } else {
            throw new RuntimeException();
        }
    }

    public void updateJpa(User user){
        if(user.getId() != null) {
            Optional<User> userReal = userRepository.findById(user.getId());
            if(!userReal.isEmpty()){
                userRepository.save(user);
            } else {
                throw new RuntimeException(); }
        } else {
            throw new RuntimeException();
        }
    }

public void deleteJpa(Long id){
        if(id != null){
            userRepository.deleteById(id);
        }
}


//    JDBC
    public User getByIdJdbc(long id) {
        return userRepositoryJdbc.getUserById(id);
    }

    public List<User> getAllJdbc() {
        return userRepositoryJdbc.getAllUsers();
    }

    public void createJdbc(User user) {
        userRepositoryJdbc.createUser(user);
    }

    public void updateJdbc(User user) {
        userRepositoryJdbc.updateUser(user);
    }

    public void deleteJdbc(Long id) {
        userRepositoryJdbc.deleteUser(id);
    }
}


