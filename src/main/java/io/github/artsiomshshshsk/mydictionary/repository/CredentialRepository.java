package io.github.artsiomshshshsk.mydictionary.repository;

import io.github.artsiomshshshsk.mydictionary.model.Credential;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CredentialRepository extends MongoRepository<Credential, String> {
}