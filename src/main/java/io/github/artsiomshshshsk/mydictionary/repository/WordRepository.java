package io.github.artsiomshshshsk.mydictionary.repository;

import io.github.artsiomshshshsk.mydictionary.model.Word;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;


import java.util.List;

public interface WordRepository extends MongoRepository<Word, String> {
    @Query("{ 'user._id' : ?0 }")
    Page<Word> findByUserId(String userId, Pageable pageable);

    @Query("{ 'user._id' : ?0 }")
    List<Word> findByUserId(String userId);
}
