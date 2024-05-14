package com.nikhiln.meme.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.nikhiln.meme.dto.MemeDto;
import com.nikhiln.meme.dto.MemeIdDto;
import com.nikhiln.meme.exception.MemeAlreadyExistsException;
import com.nikhiln.meme.exception.MemeNotFoundException;
import com.nikhiln.meme.model.Meme;
import com.nikhiln.meme.repository.MemeRepository;

@Service
public class MemeServiceImpl implements MemeService {

    @Autowired
    private MemeRepository memeRepository;


    @Override
    public MemeIdDto createMeme(MemeDto memeDto) throws MemeAlreadyExistsException {
        
        if(isMemePresent(memeDto.getName(), memeDto.getUrl(), memeDto.getCaption())) {
            throw new MemeAlreadyExistsException();
        }

        Meme createdMeme = memeRepository.save(convertDtoToEntity(memeDto));
        MemeIdDto memeIdDto = new MemeIdDto(createdMeme.getId()); 
        
        return memeIdDto;

    }


    // simplicity and readability
    @Override
    public List<Meme> getMemes(int n) {
        Pageable pageable = PageRequest.of(0, n);
        return memeRepository.findAllByOrderByCreatedAtDesc(pageable);
    }

    // OR 
    // performance and scalability
    // @Override
    // public List<Meme> getMemes(int n) {
    //     Pageable pageable = PageRequest.of(0, n, Sort.by("createdAt").descending());
    //     return memeRepository.findAll(pageable).getContent();
    // }


    @Override
    public Optional<Meme> getMemeById(String id) {
        return memeRepository.findById(id);
    }


    @Override
    public Meme updateMeme(String id, MemeDto updatedMemeDto) {

        Optional<Meme> existingMeme = memeRepository.findById(id);

        if( !existingMeme.isPresent() ) {
            throw new MemeNotFoundException();
        }

        Meme meme = existingMeme.get();
        meme.setName(updatedMemeDto.getName().trim());
        meme.setUrl(updatedMemeDto.getUrl().trim());
        meme.setCaption(updatedMemeDto.getCaption().trim());
        meme.setCreatedAt(LocalDateTime.now());

        Meme updatedMeme = memeRepository.save(meme);

        return updatedMeme;

    }


    @Override
    public void deleteMemeById(String id) {

        if(!memeRepository.existsById(id)) {
            throw new MemeNotFoundException();
        }

        memeRepository.deleteById(id);
        
    }


    private boolean isMemePresent(String name, String url, String caption) {
        return memeRepository.existsByNameAndUrlAndCaption(name, url, caption);
    }

    private Meme convertDtoToEntity(MemeDto memeDto) {
        Meme meme = new Meme();
        meme.setName(memeDto.getName().trim());
        meme.setUrl(memeDto.getUrl().trim());
        meme.setCaption(memeDto.getCaption().trim());
        meme.setCreatedAt(LocalDateTime.now());

        return meme;
    }

}
