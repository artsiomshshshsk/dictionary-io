package io.github.artsiomshshshsk.mydictionary.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "translations")
@Data
@Builder
public class Translation {
    @JsonIgnore
    private String userId;
    private String original;
    private List<String> translations;
    private List<String> examples;
}
