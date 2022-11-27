package io.github.artsiomshshshsk.mydictionary.service;

import io.github.artsiomshshshsk.mydictionary.model.WORD_SORT_BY;
import io.github.artsiomshshshsk.mydictionary.model.Word;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface WordService {
    Word save(Word word);

    void deleteById(String id);

    List<Word> getAll();

    void updateWord(String id,Word word);

    List<Word> getAllWordsByUserId(String currentUserId, int page, int size, WORD_SORT_BY sortBy, Sort.Direction sortDir);
    List<Word> getAllWordsByUserId(String currentUserId);
}
