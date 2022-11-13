package io.github.artsiomshshshsk.mydictionary.service;

import io.github.artsiomshshshsk.mydictionary.model.Word;
import io.github.artsiomshshshsk.mydictionary.repository.WordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WordServiceImpl implements WordService{
    private final WordRepository wordRepository;
    @Override
    public Word save(Word word) {
        return wordRepository.save(word);
    }
    @Override
    public void deleteById(String id) {
        wordRepository.deleteById(id);
    }
    @Override
    public List<Word> getAll() {
        return wordRepository.findAll();
    }
}
