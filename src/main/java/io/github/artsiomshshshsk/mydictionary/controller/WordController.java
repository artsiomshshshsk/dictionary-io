package io.github.artsiomshshshsk.mydictionary.controller;

import io.github.artsiomshshshsk.mydictionary.model.User;
import io.github.artsiomshshshsk.mydictionary.model.Word;
import io.github.artsiomshshshsk.mydictionary.service.UserService;
import io.github.artsiomshshshsk.mydictionary.service.WordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

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
        String currentUserId = getCurrentlyLoggedInUser().getId();
        List<Word> userWords = wordService.getAll().stream()
                .filter((word)->word.getUserId().equals(currentUserId))
                .toList();
        return new ResponseEntity<>(userWords,HttpStatus.OK);
    }

    @PutMapping("/{id}")
    void updateWord(@PathVariable String  id, @RequestBody Word word){
        wordService.updateWord(id,word);
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
