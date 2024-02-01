package com.riddlebash.dictionary.domains.entites;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table(name = "words")
public class WordEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(columnDefinition = "text")
    private String word;

    @Column(columnDefinition = "text")
    private String pronunciation;

    @Column(columnDefinition = "text")
    private String type;

    @Column(columnDefinition = "text")
    private String meaning;
}
