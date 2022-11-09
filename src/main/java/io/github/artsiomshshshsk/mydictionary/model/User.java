package io.github.artsiomshshshsk.mydictionary.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.time.LocalDate;

@Document(collection = "users")
@Data
@Builder
public class User {
    @Id
    private String nickname;
    private LocalDate registrationDate;
}
