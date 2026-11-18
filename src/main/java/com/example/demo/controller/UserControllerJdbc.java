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
@RequestMapping("/jdbc/users")
public class UserControllerJdbc {
    private final UserFacade userFacade;
    @GetMapping("{id}")
    public GetUserResponse getUserJdbc(Long id){
        return userFacade.getId(id);
//        return new GetUserResponse("Kaliningrad", Instant.now(), "Ilya");
    }

    @GetMapping
    public List<GetUserResponse> getAllUsersJdbc(){
        return userFacade.getAll();
    }

    @PostMapping
    public GetUserResponse createUserContrJdbc(@RequestBody UserModifyRequest userRequest){
        return userFacade.create(userRequest);
    }

    @PatchMapping("{id}")
    public GetUserResponse updateUserContrJdbc(@PathVariable Long id, @RequestBody UserModifyRequest userRequest){
        return userFacade.update(id, userRequest);
    }

    @DeleteMapping("{id}")
    public void deleteUserContrJdbc(@PathVariable Long id){
        userFacade.delete(id);
    }
}
