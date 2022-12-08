package io.github.artsiomshshshsk.mydictionary.repository;

import io.github.artsiomshshshsk.mydictionary.model.Word;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import java.util.List;

public interface WordRepository extends MongoRepository<Word, String> {
    @Query("{ 'user._id' : ?0}")
    List<Word> findByUserId(String userId);
}
