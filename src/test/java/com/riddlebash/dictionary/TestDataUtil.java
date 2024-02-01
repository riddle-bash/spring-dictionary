package com.riddlebash.dictionary;

import com.riddlebash.dictionary.domains.entites.WordEntity;

public class TestDataUtil {

    public static WordEntity createTestWordA() {
        return WordEntity.builder()
                .word("apple")
                .pronunciation("/ˈapəl/")
                .type("noun")
                .meaning("quả táo")
                .build();
    }

    public static WordEntity createTestWordB() {
        return WordEntity.builder()
                .word("butterfly")
                .pronunciation("ˈbədərˌflī")
                .type("noun")
                .meaning("con bướm")
                .build();
    }

    public static WordEntity createTestWordC() {
        return WordEntity.builder()
                .word("cat")
                .pronunciation("kat")
                .type("noun")
                .meaning("con mèo")
                .build();
    }
}
