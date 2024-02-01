package com.riddlebash.dictionary.assertJ;

import com.riddlebash.dictionary.TestDataUtil;
import com.riddlebash.dictionary.domains.entites.WordEntity;
import com.riddlebash.dictionary.repositories.WordRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class WordRepositoryIntegrationTests {

    private WordRepository wordRepository;

    @Autowired
    public WordRepositoryIntegrationTests(WordRepository wordRepository) {
        this.wordRepository = wordRepository;
    }

    @Test
    public void testThatWordCreated() {
        WordEntity testWordA = TestDataUtil.createTestWordA();
        wordRepository.save(testWordA);

        Optional<WordEntity> result = wordRepository.findById(testWordA.getId());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(testWordA);
    }

    @Test
    public void testThatMultipleWordsCreated() {
        WordEntity testWordA = TestDataUtil.createTestWordA();
        wordRepository.save(testWordA);

        WordEntity testWordB = TestDataUtil.createTestWordB();
        wordRepository.save(testWordB);

        WordEntity testWordC = TestDataUtil.createTestWordC();
        wordRepository.save(testWordC);

        Iterable<WordEntity> result = wordRepository.findAll();
        assertThat(result).hasSize(3).containsExactly(testWordA, testWordB, testWordC);
    }

    @Test
    public void testThatWordUpdated() {
        WordEntity testWordA = TestDataUtil.createTestWordA();
        wordRepository.save(testWordA);

        testWordA.setWord("Updated");
        wordRepository.save(testWordA);

        Optional<WordEntity> result = wordRepository.findById(testWordA.getId());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(testWordA);
    }

    @Test
    public void testThatWordDeleted() {
        WordEntity testWordA = TestDataUtil.createTestWordA();
        wordRepository.save(testWordA);
        wordRepository.deleteById(testWordA.getId());

        Optional<WordEntity> result = wordRepository.findById(testWordA.getId());
        assertThat(result).isEmpty();
    }
}
