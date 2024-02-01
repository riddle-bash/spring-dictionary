package com.riddlebash.dictionary.mappers.impl;

import com.riddlebash.dictionary.domains.dtos.WordDto;
import com.riddlebash.dictionary.domains.entites.WordEntity;
import com.riddlebash.dictionary.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class WordMapper implements Mapper<WordEntity, WordDto> {

    private ModelMapper modelMapper;

    public WordMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public WordDto mapTo(WordEntity wordEntity) {
        return modelMapper.map(wordEntity, WordDto.class);
    }

    @Override
    public WordEntity mapFrom(WordDto wordDto) {
        return modelMapper.map(wordDto, WordEntity.class);
    }
}
