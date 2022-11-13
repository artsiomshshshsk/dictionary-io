package io.github.artsiomshshshsk.mydictionary.controller;

import io.github.artsiomshshshsk.mydictionary.model.User;
import io.github.artsiomshshshsk.mydictionary.model.Word;
import io.github.artsiomshshshsk.mydictionary.service.UserService;
import io.github.artsiomshshshsk.mydictionary.service.WordService;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/words")
@RestController
public class WordController {
    private UserService userService;
    private WordService wordService;

    @Autowired
    public WordController(UserService userService, WordService wordService) {
        this.userService = userService;
        this.wordService = wordService;
    }

    @PostMapping
    ResponseEntity<?> addWord(@RequestBody Word word){
        word.setUser(getCurrentlyLoggedInUser());
        return new ResponseEntity<>(wordService.save(word), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    void deleteWord(@PathVariable String id){
        wordService.deleteById(id);
    }

    @GetMapping
    ResponseEntity<?> getAllWords(){
        return new ResponseEntity<>(wordService.getAll(),HttpStatus.OK);
    }

    private User getCurrentlyLoggedInUser(){
        String email = ((org.springframework.security.core.userdetails.User)SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal()).getUsername();
        User user = userService.findByEmail(email).get();
        return user;
    }
}
