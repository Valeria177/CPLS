package com.sas.alex.controllers;


import com.sas.alex.persist.User;
import com.sas.alex.persist.UserRepository;
import com.sas.alex.service.UserRep;
import com.sas.alex.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hibernate.criterion.Restrictions.and;

@RestController
public class RegisterController {

    private final UserService userService;

    @Autowired
    public RegisterController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/users")
    public List<User> list(){
        return userService.allUsers();
    }

    /*@PostMapping("/register")
    public ResponseEntity<?> registerNewUser(@Valid UserRep userRep, BindingResult  bindingResult){
        if(bindingResult.hasErrors()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        else {
            userService.create(userRep);
            return new ResponseEntity<>(HttpStatus.OK);
        }

    }*/

    @DeleteMapping("/user/{id}")
    @ApiOperation("Удаление пользователя по id")
    public void delete(@PathVariable Long id){
        userService.deleteById(id);
    }

    @GetMapping("/register")
    public ResponseEntity<?> registerNewUser(@RequestBody Map<String, Object> register, UserRepository userRepository){

        if (register.containsKey("username")
                & register.containsKey("password")
                & register.containsKey("email")
                & register.containsKey("sex"))
        {

            UserRep userRep = new UserRep();
            userRep.setEmail(register.get("email").toString());
            userRep.setUsername(register.get("username").toString());
            userRep.setPassword(register.get("password").toString());
            userRep.setSex(register.get("sex").toString());
            userService.create(userRep);
            Map<String, Object> map = new HashMap<>();
            map.put("name", userRep.getUsername());
            return new ResponseEntity<>(HttpStatus.OK);

        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            //return new HashMap<>();
        }
    }
}
