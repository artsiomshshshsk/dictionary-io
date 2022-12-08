package io.github.artsiomshshshsk.mydictionary.service;

import io.github.artsiomshshshsk.mydictionary.dto.WordRequest;
import io.github.artsiomshshshsk.mydictionary.dto.WordResponse;
import io.github.artsiomshshshsk.mydictionary.model.Word;
import java.util.List;

public interface WordService {
    WordResponse save(WordRequest request);
    List<WordResponse> getAllUserWords();
    void deleteById(String id);

    List<Word> getAll();

    WordResponse updateWord(String id,WordRequest request);
    List<Word> getAllWordsByUserId(String currentUserId);

}
