package com.nikhiln.meme.service;

import java.util.List;
import java.util.Optional;

import com.nikhiln.meme.dto.MemeDto;
import com.nikhiln.meme.dto.MemeIdDto;
import com.nikhiln.meme.exception.MemeAlreadyExistsException;
import com.nikhiln.meme.model.Meme;

public interface MemeService {
    
    MemeIdDto createMeme(MemeDto memeDto) throws MemeAlreadyExistsException;
    List<Meme> getMemes(int n);
    Optional<Meme> getMemeById(String id);
    Meme updateMeme(String id, MemeDto updatedMemeDto);
    void deleteMemeById(String id);

}
