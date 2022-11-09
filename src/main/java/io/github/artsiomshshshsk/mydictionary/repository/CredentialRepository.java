package io.github.artsiomshshshsk.mydictionary.repository;

import io.github.artsiomshshshsk.mydictionary.model.Credential;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CredentialRepository extends MongoRepository<Credential, String> {
}