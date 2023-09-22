package com.autarklab.springsecurityclient.service;

import com.autarklab.springsecurityclient.entity.PasswordResetToken;
import com.autarklab.springsecurityclient.entity.User;
import com.autarklab.springsecurityclient.entity.VerificationToken;
import com.autarklab.springsecurityclient.model.UserModel;
import com.autarklab.springsecurityclient.repository.PasswordResetTokenRepository;
import com.autarklab.springsecurityclient.repository.UserRepository;
import com.autarklab.springsecurityclient.repository.VerificationTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final VerificationTokenRepository verificationTokenRepository;
    private final PasswordResetTokenRepository passwordResetTokenRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           PasswordEncoder passwordEncoder,
                           VerificationTokenRepository verificationTokenRepository,
                           PasswordResetTokenRepository passwordResetTokenRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.verificationTokenRepository = verificationTokenRepository;
        this.passwordResetTokenRepository = passwordResetTokenRepository;
    }

    @Override
    public User registerUser(UserModel userModel) {
        User user = new User();
        user.setEmail(userModel.getEmail());
        user.setFirstName(userModel.getFirstName());
        user.setLastName(userModel.getLastName());
        user.setRole("USER");

        user.setPassword(passwordEncoder.encode(userModel.getPassword()));

        userRepository.save(user);
        return user;
    }

    @Override
    public void saveVerificationTokenForUser(String token, User user) {
        VerificationToken verificationToken =
                new VerificationToken(user,token);

        verificationTokenRepository.save(verificationToken);
    }

    @Override
    public String validateVerificationToken(String token) {

        VerificationToken verificationToken =
                verificationTokenRepository.findByToken(token);

        if(verificationToken == null){
            return "Invalid Token!";
        }

        User user = verificationToken.getUser();
        Calendar calendar = Calendar.getInstance();

        long calculateDate = verificationToken.getExpirationTime().getTime() - calendar.getTime().getTime();

        if(calculateDate <= 0){
            verificationTokenRepository.delete(verificationToken);
            return "expired";
        }

        user.setEnabled(true);
        userRepository.save(user);
        String userData = user.getFirstName() +" " + user.getLastName();
        return "valid "+ userData;
    }

    @Override
    public VerificationToken generateNewVerificationToken(String oldToken) {
        VerificationToken verificationToken =
                verificationTokenRepository.findByToken(oldToken);

        verificationToken.setToken(UUID.randomUUID().toString());
        verificationTokenRepository.save((verificationToken));
        return verificationToken;
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public void createPasswordResetTokenForUser(User user, String token) {
        PasswordResetToken passwordResetToken = new PasswordResetToken(user,token);
        passwordResetTokenRepository.save(passwordResetToken);
    }

    @Override
    public Optional<User> getUserByPasswordResetToken(String token) {
        return Optional.ofNullable(passwordResetTokenRepository.findByToken(token).getUser());
    }

    @Override
    public void changePassword(User user, String newPassword) {
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
    }

    @Override
    public String validatePasswordResetToken(String token) {
        PasswordResetToken passwordResetToken =
                passwordResetTokenRepository.findByToken(token);

        if(passwordResetToken == null){
            return "Invalid Token!";
        }

        User user = passwordResetToken.getUser();
        Calendar calendar = Calendar.getInstance();

        long calculateDate = passwordResetToken.getExpirationTime().getTime() - calendar.getTime().getTime();

        if(calculateDate <= 0){
            passwordResetTokenRepository.delete(passwordResetToken);
            return "expired";
        }

        user.setEnabled(true);
        userRepository.save(user);
        return "valid ";
    }
}

