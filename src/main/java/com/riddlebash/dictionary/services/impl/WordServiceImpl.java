package com.riddlebash.dictionary.services.impl;

import com.riddlebash.dictionary.domains.entites.WordEntity;
import com.riddlebash.dictionary.repositories.WordRepository;
import com.riddlebash.dictionary.services.WordService;
import org.springframework.stereotype.Service;

@Service
public class WordServiceImpl implements WordService {

    private WordRepository wordRepository;

    public WordServiceImpl(WordRepository wordRepository) {
        this.wordRepository = wordRepository;
    }

    @Override
    public WordEntity save(WordEntity wordEntity) {
        return wordRepository.save(wordEntity);
    }
}
