package com.sas.alex.controllers;


import com.sas.alex.dto.auth.response.MessageResponse;
import com.sas.alex.dto.user.ChangeUsernameRequest;
import com.sas.alex.dto.user.UserResponse;
import com.sas.alex.dto.user.ChangeEmailRequest;
import com.sas.alex.model.User;
import com.sas.alex.repository.UserRepository;
import com.sas.alex.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/user")
@RestController
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @GetMapping("/details")
    @ApiOperation("Инфа о аккаунте")
    public ResponseEntity<UserResponse> userDetails(Principal principal){
        User user = userRepository.findByUsername(principal.getName()).get();
        return ResponseEntity.ok(userService.getDetails(user));
    }

    @PostMapping("/changeEmail")
    @ApiOperation("Изменить почту")
    public ResponseEntity<MessageResponse> changeEmail(@Valid @RequestBody ChangeEmailRequest changeEmailRequest, Principal principal){
        User user = userRepository.findByUsername(principal.getName()).get();
        if(userService.changeEmail(user, changeEmailRequest.getEmail()))
            return ResponseEntity.ok(new MessageResponse("Email change successful"));
        else
            return ResponseEntity.badRequest().body(new MessageResponse("Error"));
    }

    @PostMapping("/changeUsername")
    @ApiOperation("Изменить кликуху")
    public ResponseEntity<MessageResponse> changeEmail(@Valid @RequestBody ChangeUsernameRequest changeUsernameRequest, Principal principal){
        User user = userRepository.findByUsername(principal.getName()).get();
        if(userService.changeEmail(user, changeUsernameRequest.getUsername()))
            return ResponseEntity.ok(new MessageResponse("Username change successful"));
        else
            return ResponseEntity.badRequest().body(new MessageResponse("Error"));
    }
}
