package io.github.artsiomshshshsk.mydictionary.repository;


import io.github.artsiomshshshsk.mydictionary.model.Translation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TranslationRepository extends MongoRepository<Translation,String> {
    List<Translation> findAllByUserIdEquals(String userId);
}
