package io.github.artsiomshshshsk.mydictionary.dto;

import io.github.artsiomshshshsk.mydictionary.model.User;

import java.util.List;

public record WordResponse(
        String id,
        String original,
        String transcription,
        List<String> translations,
        User user
) {
}
