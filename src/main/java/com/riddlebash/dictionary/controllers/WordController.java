package com.riddlebash.dictionary.controllers;

import com.riddlebash.dictionary.domains.dtos.WordDto;
import com.riddlebash.dictionary.domains.entites.WordEntity;
import com.riddlebash.dictionary.mappers.Mapper;
import com.riddlebash.dictionary.services.WordService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class WordController {

    private Mapper<WordEntity, WordDto> wordMapper;

    private WordService wordService;

    public WordController(Mapper<WordEntity, WordDto> wordMapper, WordService wordService) {
        this.wordMapper = wordMapper;
        this.wordService = wordService;
    }

    @PostMapping(path = "/words")
    public ResponseEntity<WordDto> postSingleWord(@RequestBody WordDto wordDto) {
        WordEntity wordEntity = wordMapper.mapFrom(wordDto);
        WordEntity savedWordEntity = wordService.save(wordEntity);
        return new ResponseEntity<>(wordMapper.mapTo(savedWordEntity), HttpStatus.CREATED);
    }

    @GetMapping(path = "/words/{id}")
    public ResponseEntity<WordDto> getSingleWord(@PathVariable("id") Long id) {
        Optional<WordEntity> word = wordService.find(id);
        return word.map(wordEntity -> {
            return new ResponseEntity<>(wordMapper.mapTo(wordEntity), HttpStatus.FOUND);
        }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping(path = "/words")
    public Page<WordDto> getAllWords(Pageable pageable) {
        Page<WordEntity> words = wordService.findAll(pageable);
        return words.map(wordMapper::mapTo);
    }
}
