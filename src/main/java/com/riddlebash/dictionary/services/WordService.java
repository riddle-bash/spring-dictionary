package com.riddlebash.dictionary.services;

import com.riddlebash.dictionary.domains.entites.WordEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface WordService {
    WordEntity save(WordEntity wordEntity);

    Optional<WordEntity> find(Long id);

    Page<WordEntity> findAll(Pageable pageable);

    boolean isExists(Long id);

    WordEntity updatePartial(Long id, WordEntity wordEntity);
}
