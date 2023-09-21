package com.autarklab.springsecurityclient.service;

import com.autarklab.springsecurityclient.entity.User;
import com.autarklab.springsecurityclient.model.UserModel;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    User registerUser(UserModel userModel);

    void saveVerificationTokenForUser(String token, User user);

    String validateVerificationToken(String token);
}
