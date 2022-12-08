package io.github.artsiomshshshsk.mydictionary.controller;

import io.github.artsiomshshshsk.mydictionary.dto.WordRequest;
import io.github.artsiomshshshsk.mydictionary.dto.WordResponse;
import io.github.artsiomshshshsk.mydictionary.service.WordService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RequestMapping("/api/words")
@RestController
@CrossOrigin(origins = "https://my-dictionary-io.herokuapp.com", maxAge = 3600)
@AllArgsConstructor
public class WordController {
    private final WordService wordService;
    @PostMapping
    public ResponseEntity<WordResponse> addWord(@RequestBody WordRequest wordRequest) {
        return new ResponseEntity<>(
                wordService.save(wordRequest),
                HttpStatus.OK
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteWord(@PathVariable String id){
        wordService.deleteById(id);
        return new ResponseEntity<>(
                "Word deleted",
                HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<WordResponse>> getAllWords(){
        return new ResponseEntity<>(
                wordService.getAllUserWords(),
                HttpStatus.OK
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<WordResponse> updateWord(@PathVariable String  id,
                                                   @RequestBody WordRequest request){
        WordResponse response = wordService.updateWord(id,request);
        return new ResponseEntity<>(
                response,
                HttpStatus.OK
        );
    }
}
