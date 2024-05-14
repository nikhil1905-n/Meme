package com.nikhiln.meme.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nikhiln.meme.dto.MemeDto;
import com.nikhiln.meme.dto.MemeIdDto;
import com.nikhiln.meme.exception.MemeAlreadyExistsException;
import com.nikhiln.meme.exception.MemeNotFoundException;
import com.nikhiln.meme.model.Meme;
import com.nikhiln.meme.service.MemeServiceImpl;


@RestController
@RequestMapping(MemeController.XMEME_API_ENDPOINT)
public class MemeController {

    public static final String XMEME_API_ENDPOINT = "/memes";
    public static final String MEME_ID = "/{id}";
    
    @Autowired
    private MemeServiceImpl memeService;


    @PostMapping
    public ResponseEntity<MemeIdDto> createMeme(@Valid @RequestBody MemeDto memeDto) {

        if (memeDto == null || isEmpty(memeDto.getName()) || isEmpty(memeDto.getUrl()) || isEmpty(memeDto.getCaption()) ) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        MemeIdDto memeIdDto = memeService.createMeme(memeDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(memeIdDto);
        
    }

    private boolean isEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }


    @GetMapping
    public ResponseEntity<List<Meme>> getMemes(@RequestParam(defaultValue = "100") int n) {
        
        List<Meme> memes = memeService.getMemes(n);
        return ResponseEntity.ok().body(memes);

    }
    
    
    @GetMapping(MEME_ID)
    public ResponseEntity<Meme> getMemeById(@PathVariable String id) {
        
        Optional<Meme> meme = memeService.getMemeById(id);

        if(meme.isEmpty() ) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.ok().body(meme.get());

    }
    
    
    @PutMapping(MEME_ID)
    public ResponseEntity<Meme> updateMeme(@PathVariable String id, @RequestBody MemeDto updatedMemeDto) {

        Meme meme = memeService.updateMeme(id, updatedMemeDto);
        return ResponseEntity.ok(meme);

    }
    

    @DeleteMapping(MEME_ID)
    public ResponseEntity<?> deleteMemeById(@PathVariable String id) {

        memeService.deleteMemeById(id);
        return ResponseEntity.ok().build();
        
    }


    @ExceptionHandler
    public ResponseEntity<String> memeAlreadyExistsException(MemeAlreadyExistsException exception) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body("Meme Already Exists");
    }

    @ExceptionHandler
    public ResponseEntity<String> memeNotFoundException(MemeNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Meme Not Found");
    }

}
