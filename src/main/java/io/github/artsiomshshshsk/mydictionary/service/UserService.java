package io.github.artsiomshshshsk.mydictionary.service;

import io.github.artsiomshshshsk.mydictionary.model.User;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.util.Optional;

public interface UserService {
    User save(User user);
    Optional<User> findByEmail(String email);
    User register(User user);
    void sendVerificationEmail(User user,String baseUrl) throws MessagingException, UnsupportedEncodingException;
    boolean verify(String code);
    User getCurrentlyLoggedInUser();
    String getCurrentlyLoggedInUserId();
}
