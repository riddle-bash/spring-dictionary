package com.riddlebash.dictionary.domains.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WordDto {

    private Long id;

    private String word;

    private String pronunciation;

    private String type;

    private String meaning;
}
