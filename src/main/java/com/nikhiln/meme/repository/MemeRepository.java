package com.nikhiln.meme.repository;

import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.nikhiln.meme.model.Meme;

public interface MemeRepository extends MongoRepository<Meme, String> {

    boolean existsByNameAndUrlAndCaption(String name, String url, String caption);
    List<Meme> findAllByOrderByCreatedAtDesc(Pageable pageable);

}
