package com.riddlebash.dictionary.services.impl;

import com.riddlebash.dictionary.domains.entites.WordEntity;
import com.riddlebash.dictionary.repositories.WordRepository;
import com.riddlebash.dictionary.services.WordService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

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

    @Override
    public Optional<WordEntity> find(Long id) {
        return wordRepository.findById(id);
    }

    @Override
    public Page<WordEntity> findAll(Pageable pageable) {
        return wordRepository.findAll(pageable);
    }

    @Override
    public boolean isExists(Long id) {
        return wordRepository.existsById(id);
    }

    @Override
    public WordEntity updatePartial(Long id, WordEntity wordEntity) {
        return wordRepository.findById(id).map(existWord -> {
            Optional.ofNullable(wordEntity.getWord()).ifPresent(existWord::setWord);
            Optional.ofNullable(wordEntity.getPronunciation()).ifPresent(existWord::setPronunciation);
            Optional.ofNullable(wordEntity.getType()).ifPresent(existWord::setType);
            Optional.ofNullable(wordEntity.getMeaning()).ifPresent(existWord::setMeaning);
            return wordRepository.save(existWord);
        }).orElseThrow(() -> new RuntimeException("Word does not exist"));
    }

    @Override
    public void delete(Long id) {
        wordRepository.deleteById(id);
    }
}
