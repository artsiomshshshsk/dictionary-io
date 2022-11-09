package io.github.artsiomshshshsk.mydictionary.service;

import com.fasterxml.jackson.databind.annotation.JsonAppend;
import io.github.artsiomshshshsk.mydictionary.model.Credential;
import io.github.artsiomshshshsk.mydictionary.repository.CredentialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class CredentialService implements UserDetailsService {

    private CredentialRepository credentialRepository;

    @Autowired
    public CredentialService(CredentialRepository credentialRepository) {
        this.credentialRepository = credentialRepository;
    }

    @Override
    public Credential loadUserByUsername(String username) throws UsernameNotFoundException {
        return credentialRepository.findById(username)
                .orElseThrow(() -> new UsernameNotFoundException("username not found"));
    }
}
