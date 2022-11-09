package io.github.artsiomshshshsk.mydictionary.controller;

import io.github.artsiomshshshsk.mydictionary.model.Credential;
import io.github.artsiomshshshsk.mydictionary.model.Translation;
import io.github.artsiomshshshsk.mydictionary.model.User;
import io.github.artsiomshshshsk.mydictionary.service.TranslationsService;
import io.github.artsiomshshshsk.mydictionary.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/translations")
public class TranslationController {
    private TranslationsService translationsService;
    private UserService userService;


    @Autowired
    public TranslationController(TranslationsService translationsService, UserService userService) {
        this.translationsService = translationsService;
        this.userService = userService;
    }

    @PostMapping
    public Translation addTranslation(@RequestBody Translation translation){
        System.out.println("hit1");
        translation.setUserId(getCurrentlyLoggedInUser().getNickname());
        return translationsService.save(translation);
    }


    @GetMapping
    public List<Translation> getAllTranslations(){
        return translationsService.allTranslations(getCurrentlyLoggedInUser().getNickname());
    }

    private User getCurrentlyLoggedInUser(){
        Credential credential = (Credential) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userService.findById(credential.getUsername()).get();
        System.out.println(user);
        return user;
    }
}
