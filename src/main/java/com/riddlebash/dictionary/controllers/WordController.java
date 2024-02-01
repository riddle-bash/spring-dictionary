package com.riddlebash.dictionary.controllers;

import com.riddlebash.dictionary.domains.dtos.WordDto;
import com.riddlebash.dictionary.domains.entites.WordEntity;
import com.riddlebash.dictionary.mappers.Mapper;
import com.riddlebash.dictionary.services.WordService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WordController {

    private Mapper<WordEntity, WordDto> wordMapper;

    private WordService wordService;

    public WordController(Mapper<WordEntity, WordDto> wordMapper, WordService wordService) {
        this.wordMapper = wordMapper;
        this.wordService = wordService;
    }

    @PostMapping(path = "/words")
    public ResponseEntity<WordDto> createWord(@RequestBody WordDto wordDto) {
        WordEntity wordEntity = wordMapper.mapFrom(wordDto);
        WordEntity savedWordEntity = wordService.save(wordEntity);
        return new ResponseEntity<>(wordMapper.mapTo(savedWordEntity), HttpStatus.CREATED);
    }
}
