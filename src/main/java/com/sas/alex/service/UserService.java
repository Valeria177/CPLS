package com.sas.alex.service;

import com.sas.alex.dto.user.UserResponse;
import com.sas.alex.model.User;
import com.sas.alex.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public UserResponse getDetails(User user){
        UserResponse userResponse = new UserResponse();
        userResponse.setUsername(user.getUsername());
        userResponse.setSexF(user.getSexF());
        userResponse.setEmail(user.getEmail());
        return userResponse;
    }

    @Transactional
    public boolean changeEmail(User user, String email){
        user.setEmail(email);
        try {
            userRepository.save(user);
        }catch (Exception e){
            return false;
        }
        return true;
    }

    @Transactional
    public boolean changeUsername(User user, String username){
        user.setUsername(username);
        try {
            userRepository.save(user);
        }catch (Exception e){
            return false;
        }
        return true;
    }
}
