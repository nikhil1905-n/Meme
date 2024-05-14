package com.nikhiln.meme.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import java.time.LocalDateTime;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "memes")
public class Meme {
    
    @Id
    private String id;

    @NonNull
    private String name;

    @NonNull
    private String url;

    @NonNull
    private String caption;

    @CreatedDate
    private LocalDateTime createdAt;

}
