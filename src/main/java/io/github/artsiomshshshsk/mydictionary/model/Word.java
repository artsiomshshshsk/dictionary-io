package io.github.artsiomshshshsk.mydictionary.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;

@Document(collection = "words")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Word {
    @Id
    private String id;
    private String original;
    private String transcription;
    private List<String> translations;
    @DBRef
    private User user;
}
