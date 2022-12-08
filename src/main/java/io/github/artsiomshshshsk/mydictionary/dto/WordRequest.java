package io.github.artsiomshshshsk.mydictionary.dto;

import lombok.Builder;
import java.util.List;

@Builder
public record WordRequest(
        String original,
        String transcription,
        List<String> translations) {
}
