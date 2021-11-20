package com.sas.alex.service;

import com.sas.alex.persist.Role;
import com.sas.alex.persist.User;
import com.sas.alex.persist.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;


@Service
public class UserService {

    private final UserRepository repository;

    private final BCryptPasswordEncoder passwordEncoder;


    @Autowired
    public UserService(UserRepository repository, BCryptPasswordEncoder passwordEncoder){
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    public User create(UserRep userRep) {
        User user = new User();
        user.setUsername(userRep.getUsername());
        user.setPassword(passwordEncoder.encode(userRep.getPassword()));
        user.setEmail(userRep.getEmail());
        user.setSex(userRep.getSex());
        //user.setRoles(Collections.singleton(new Role(2L, "Test")));
        repository.save(user);
        return user;
    }

    public List<User> allUsers() {
        return repository.findAll();
    }

    public void deleteById(Long id){
        repository.deleteById(id);
    }

    public User saveUser(User user){
        return repository.save(user);
    }

}