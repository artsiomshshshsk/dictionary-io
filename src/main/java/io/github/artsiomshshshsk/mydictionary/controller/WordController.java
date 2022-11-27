package io.github.artsiomshshshsk.mydictionary.controller;

import io.github.artsiomshshshsk.mydictionary.model.User;
import io.github.artsiomshshshsk.mydictionary.model.WORD_SORT_BY;
import io.github.artsiomshshshsk.mydictionary.model.Word;
import io.github.artsiomshshshsk.mydictionary.service.UserService;
import io.github.artsiomshshshsk.mydictionary.service.WordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RequestMapping("/api/words")
@RestController
@CrossOrigin(origins = "https://my-dictionary-io.herokuapp.com", maxAge = 3600)
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

    @GetMapping("/pagination")
    public ResponseEntity<?> getAllWordsWithPagination(@RequestParam(defaultValue = "0") int page,
                                         @RequestParam(defaultValue = "10")int pageSize,
                                         @RequestParam(defaultValue = "id") WORD_SORT_BY sortBy,
                                         @RequestParam(defaultValue = "ASC") Sort.Direction sortDirection){
        String currentUserId = getCurrentlyLoggedInUser().getId();
        List<Word> userWords = wordService.getAllWordsByUserId(currentUserId,page,pageSize,sortBy,sortDirection);
        return new ResponseEntity<>(userWords,HttpStatus.OK);
    }


    @GetMapping
    public ResponseEntity<?> getAllWords(){
        String currentUserId = getCurrentlyLoggedInUser().getId();
        List<Word> userWords = wordService.getAllWordsByUserId(currentUserId);
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
