package io.github.artsiomshshshsk.mydictionary.service;

import io.github.artsiomshshshsk.mydictionary.model.Translation;

import java.util.List;

public interface TranslationsService {
    Translation save(Translation translation);
    List<Translation> allTranslations(String username);
}
