package com.riddlebash.dictionary.repositories;

import com.riddlebash.dictionary.domains.entites.WordEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WordRepository extends CrudRepository<WordEntity, Long>,
        PagingAndSortingRepository<WordEntity, Long> {
}
