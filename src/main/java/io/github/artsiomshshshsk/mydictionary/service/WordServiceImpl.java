package io.github.artsiomshshshsk.mydictionary.service;

import io.github.artsiomshshshsk.mydictionary.dto.WordRequest;
import io.github.artsiomshshshsk.mydictionary.dto.WordResponse;
import io.github.artsiomshshshsk.mydictionary.mapper.WordMapper;
import io.github.artsiomshshshsk.mydictionary.model.Word;
import io.github.artsiomshshshsk.mydictionary.repository.WordRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@AllArgsConstructor
public class WordServiceImpl implements WordService{
    private final WordRepository wordRepository;
    private final WordMapper wordMapper;

    private final UserService userService;

    @Override
    public WordResponse save(WordRequest request) {
        Word word = wordMapper.toWord(
                request,
                userService.getCurrentlyLoggedInUser()
        );
        return wordMapper.toResponse(
                wordRepository.save(word)
        );
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
    public List<WordResponse> getAllUserWords() {
        List<Word> words = wordRepository.findByUserId(userService.getCurrentlyLoggedInUserId());
        return wordMapper.toResponses(words);
    }

    @Override
    public WordResponse updateWord(String id,WordRequest word) {
        Word w = wordRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Word not found")
        );
        if(word.original() != null){
            w.setOriginal(word.original());
        }
        if(word.translations() != null){
            w.setTranslations(word.translations());
        }
        if(word.transcription() != null){
            w.setTranscription(word.transcription());
        }
        return wordMapper.toResponse(wordRepository.save(w));
    }

    @Override
    public List<Word> getAllWordsByUserId(String currentUserId) {
        return wordRepository.findByUserId(currentUserId);
    }
}
