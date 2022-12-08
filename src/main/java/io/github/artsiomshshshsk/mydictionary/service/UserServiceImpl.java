package io.github.artsiomshshshsk.mydictionary.service;

import io.github.artsiomshshshsk.mydictionary.model.User;
import io.github.artsiomshshshsk.mydictionary.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserDetailsService,UserService {

    private final UserRepository userRepository;
    private JavaMailSender mailSender;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, JavaMailSender mailSender) {
        this.userRepository = userRepository;
        this.mailSender = mailSender;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email).orElseThrow(
                () -> new UsernameNotFoundException("User not found")
        );
        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getEmail())
                .password(user.getPassword())
                .authorities(new ArrayList<>())
                .disabled(user.getDisabled())
                .build();
    }

    public void sendVerificationEmail(User user,String baseUrl)
            throws MessagingException, UnsupportedEncodingException{
        String toAddress = user.getEmail();
        String fromAddress = "io.dictionary.io@gmail.com";
        String senderName = "dictionary io team";
        String subject = "Please verify your registration";
        String content = "Dear [[name]],<br>"
                + "Please click the link below to verify your registration:<br>"
                + "<h3><a href=\"[[URL]]\" target=\"_self\">VERIFY</a></h3>"
                + "Thank you,<br>"
                + "dictionary.io";

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom(fromAddress, senderName);
        helper.setTo(toAddress);
        helper.setSubject(subject);

        content = content.replace("[[name]]", user.getUsername());
        String verifyURL = baseUrl + "/api/auth/verify?verificationToken=" + user.getVerificationToken();

        content = content.replace("[[URL]]", verifyURL);

        helper.setText(content, true);

        mailSender.send(message);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    public boolean verify(String verificationCode){
        Optional<User> userOptional = userRepository.findByVerificationToken(verificationCode);
        if(userOptional.isEmpty()) return false;
        else{
            User user = userOptional.get();
            user.setVerificationToken(null);
            user.setDisabled(false);
            userRepository.save(user);
            return true;
        }
    }


    public User register(User user){
        user.setDisabled(true);
        user.setVerificationToken(UUID.randomUUID().toString());
        return userRepository.save(user);
    }

    public User getCurrentlyLoggedInUser(){
        String email = ((org.springframework.security.core.userdetails.User) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal()).getUsername();
        return findByEmail(email).orElseThrow(
                () -> new UsernameNotFoundException("User not found")
        );
    }

    public String getCurrentlyLoggedInUserId(){
        return getCurrentlyLoggedInUser().getId();
    }


    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
