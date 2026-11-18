package com.example.demo.facade;

import com.example.demo.dto.request.UserModifyRequest;
import com.example.demo.dto.response.GetUserResponse;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
@RequiredArgsConstructor

public class UserFacade {
    private final UserService userService;

    private GetUserResponse toResponse(User user){
        return new GetUserResponse(user.getCity(), user.getDate(), user.getName());
    }

    private UserModifyRequest toRequest(User user){
        return new UserModifyRequest(user.getCity(), user.getDate(), user.getName());
    }

    private User toEntity(UserModifyRequest request) {
        User user = new User();
        user.setCity(request.city());
        user.setDate(request.date());
        user.setName(request.name());
        return user;
    }

//    JPA

    public GetUserResponse getUserById(Long id){
        return toResponse(userService.getByIdJpa(id));
    }

    public List<GetUserResponse> getAllUsers(){
        List<GetUserResponse> responses = new ArrayList<>();
        for(User user : userService.getAllJpa()){
            responses.add(toResponse(user));
        }
        return responses;
    }

    public GetUserResponse createUser(UserModifyRequest request){
        User user = toEntity(request);
        userService.createJpa(user);
        return toResponse(user);
    }


    public GetUserResponse updateUser(Long id, UserModifyRequest request){
        User user = toEntity(request);
        user.setId(id);
        userService.updateJpa(user);
        return toResponse(user);
    }

    public void deleteUser(Long id){
        userService.deleteJpa(id);
    }



//    JDBC

    public GetUserResponse getId(long id) {
        return toResponse(userService.getByIdJdbc(id));
    }

    public List<GetUserResponse> getAll() {
        List<GetUserResponse> responses = new ArrayList<>();
        for(User user : userService.getAllJdbc()){
            responses.add(toResponse(user));
        }
        return responses;
    }

    public GetUserResponse create(UserModifyRequest request) {
        User user = toEntity(request);
        userService.createJdbc(user);
        return toResponse(user);
    }

    public GetUserResponse update(Long id, UserModifyRequest request){
        User user = toEntity(request);
        user.setId(id);
        userService.updateJdbc(user);
        return toResponse(user);
    }

    public void delete(Long id) {
        userService.deleteJdbc(id);
    }

}
