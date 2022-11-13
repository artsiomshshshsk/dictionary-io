package io.github.artsiomshshshsk.mydictionary.service;

import io.github.artsiomshshshsk.mydictionary.model.Word;
import org.bson.types.ObjectId;

import java.util.List;

public interface WordService {
    Word save(Word word);

    void deleteById(String id);

    List<Word> getAll();
}
