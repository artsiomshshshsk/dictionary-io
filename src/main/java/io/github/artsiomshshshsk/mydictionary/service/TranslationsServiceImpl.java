package io.github.artsiomshshshsk.mydictionary.service;

import io.github.artsiomshshshsk.mydictionary.model.Translation;
import io.github.artsiomshshshsk.mydictionary.repository.TranslationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TranslationsServiceImpl implements TranslationsService{
    private final TranslationRepository translationRepository;

    @Autowired
    public TranslationsServiceImpl(TranslationRepository translationRepository) {
        this.translationRepository = translationRepository;
    }
    @Override
    public Translation save(Translation translation) {
        translationRepository.save(translation);
        return translation;
    }


    @Override
    public List<Translation> allTranslations(String username) {
        return translationRepository.findAllByUserIdEquals(username);
    }
}
