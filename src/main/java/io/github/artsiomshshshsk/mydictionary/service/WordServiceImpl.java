package io.github.artsiomshshshsk.mydictionary.service;

import io.github.artsiomshshshsk.mydictionary.model.WORD_SORT_BY;
import io.github.artsiomshshshsk.mydictionary.model.Word;
import io.github.artsiomshshshsk.mydictionary.repository.WordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    @Override
    public void updateWord(String id,Word word) {
        Word w = wordRepository.findById(id).get();
        if(word.getOriginal() != null){
            w.setOriginal(word.getOriginal());
        }
        if(word.getTranslations() != null){
            w.setTranslations(word.getTranslations());
        }
        if(word.getTranscription() != null){
            w.setTranscription(word.getTranscription());
        }
        wordRepository.save(w);
    }

    @Override
    public List<Word> getAllWordsByUserId(String currentUserId, int page, int size, WORD_SORT_BY sortBy, Sort.Direction sortDir) {
        return wordRepository.findByUserId(currentUserId, PageRequest.of(page,size,Sort.by(sortDir,sortBy.toString()))).stream().toList();
    }

    @Override
    public List<Word> getAllWordsByUserId(String currentUserId) {
        return wordRepository.findByUserId(currentUserId);
    }
}
