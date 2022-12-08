package io.github.artsiomshshshsk.mydictionary.mapper;

import io.github.artsiomshshshsk.mydictionary.dto.WordRequest;
import io.github.artsiomshshshsk.mydictionary.dto.WordResponse;
import io.github.artsiomshshshsk.mydictionary.model.Word;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class WordMapper {

    public Word toWord(WordRequest wordRequest, String userId){
        return Word.builder()
                .original(wordRequest.original())
                .transcription(wordRequest.transcription())
                .translations(wordRequest.translations())
                .id(userId)
                .build();
    }


    public WordResponse toResponse(Word word){
        return new WordResponse(
                word.getId(),
                word.getOriginal(),
                word.getTranscription(),
                word.getTranslations()
        );
    }

    public List<WordResponse> toResponses(List<Word> words){
        return words.stream()
                .map(this::toResponse)
                .toList();
    }
}
