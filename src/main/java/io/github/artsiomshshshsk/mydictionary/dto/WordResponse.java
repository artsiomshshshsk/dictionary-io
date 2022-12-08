package io.github.artsiomshshshsk.mydictionary.dto;

import java.util.List;

public record WordResponse(
        String id,
        String original,
        String transcription,
        List<String> translations
) {
}
