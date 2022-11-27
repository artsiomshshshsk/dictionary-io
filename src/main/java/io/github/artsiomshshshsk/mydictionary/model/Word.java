package io.github.artsiomshshshsk.mydictionary.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;

@Document(collection = "words")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Word {


    @Id
    private String id;
    private String original;
    private String transcription;
    private List<String> translations;
    @DBRef
    @JsonIgnore
    private User user;

    public String getUserId() {
        return user.getId();
    }
}
