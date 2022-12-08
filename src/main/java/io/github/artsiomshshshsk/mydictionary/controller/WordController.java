package io.github.artsiomshshshsk.mydictionary.controller;

import io.github.artsiomshshshsk.mydictionary.model.WORD_SORT_BY;
import io.github.artsiomshshshsk.mydictionary.model.Word;
import io.github.artsiomshshshsk.mydictionary.service.UserService;
import io.github.artsiomshshshsk.mydictionary.service.WordService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RequestMapping("/api/words")
@RestController
@CrossOrigin(origins = "https://my-dictionary-io.herokuapp.com", maxAge = 3600)
@AllArgsConstructor
public class WordController {
    private UserService userService;
    private WordService wordService;

    @PostMapping
    ResponseEntity<?> addWord(@RequestBody Word word){
        word.setUser(userService.getCurrentlyLoggedInUser());
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
        String currentUserId = userService.getCurrentlyLoggedInUserId();
        List<Word> userWords = wordService.getAllWordsByUserId(currentUserId,page,pageSize,sortBy,sortDirection);
        return new ResponseEntity<>(userWords,HttpStatus.OK);
    }


    @GetMapping
    public ResponseEntity<?> getAllWords(@RequestParam(defaultValue = "english") String language){
        String currentUserId = userService.getCurrentlyLoggedInUserId();
        List<Word> userWords = wordService.getAllWordsByUserId(currentUserId, language);
        return new ResponseEntity<>(userWords,HttpStatus.OK);
    }


    @PutMapping("/{id}")
    void updateWord(@PathVariable String  id, @RequestBody Word word){
        wordService.updateWord(id,word);
    }
}
