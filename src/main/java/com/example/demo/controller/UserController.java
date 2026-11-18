package com.example.demo.controller;

import com.example.demo.dto.request.UserModifyRequest;
import com.example.demo.dto.response.GetUserResponse;
import com.example.demo.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserFacade userFacade;
    @GetMapping("{id}")
    public GetUserResponse getUser(@PathVariable Long id){
        return userFacade.getUserById(id);
//        return new GetUserResponse("Kaliningrad", Instant.now(), "Ilya"); Заглушка
    }

    @GetMapping
    public List<GetUserResponse> getAllUsers(){
        return userFacade.getAllUsers();
    }

    @PostMapping
    public GetUserResponse createUserContr(@RequestBody UserModifyRequest userRequest){
        return userFacade.createUser(userRequest);
    }

    @PatchMapping("{id}")
    public GetUserResponse updateUserContr(@PathVariable Long id, @RequestBody UserModifyRequest userRequest){
        return userFacade.updateUser(id, userRequest);
    }

    @DeleteMapping("{id}")
    public void deleteUserContr(@PathVariable Long id){
        userFacade.deleteUser(id);
    }


}
