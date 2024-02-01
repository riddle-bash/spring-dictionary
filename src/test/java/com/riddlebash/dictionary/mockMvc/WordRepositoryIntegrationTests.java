package com.riddlebash.dictionary.mockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.riddlebash.dictionary.TestDataUtil;
import com.riddlebash.dictionary.domains.entites.WordEntity;
import com.riddlebash.dictionary.services.WordService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureMockMvc
public class WordRepositoryIntegrationTests {

    private WordService wordService;

    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    @Autowired
    public WordRepositoryIntegrationTests(WordService wordService, MockMvc mockMvc) {
        this.wordService = wordService;
        this.mockMvc = mockMvc;
        this.objectMapper = new ObjectMapper();
    }

    @Test
    public void testThatWordCreated() throws Exception{
        WordEntity testWordA = TestDataUtil.createTestWordA();
        String wordJson = objectMapper.writeValueAsString(testWordA);

        mockMvc.perform(
                MockMvcRequestBuilders.post("/words")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(wordJson)
        ).andExpect(
                MockMvcResultMatchers.status().isCreated()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("word").value(testWordA.getWord())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("pronunciation").value(testWordA.getPronunciation())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("type").value(testWordA.getType())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("meaning").value(testWordA.getMeaning())
        );
    }

    @Test
    public void testThatWordFullyUpdated() throws Exception{
        WordEntity testWordA = TestDataUtil.createTestWordA();
        wordService.save(testWordA);

        testWordA.setWord("Updated");
        WordEntity updatedWord = WordEntity.builder()
                .word(testWordA.getWord())
                .pronunciation(testWordA.getPronunciation())
                .type(testWordA.getType())
                .meaning(testWordA.getMeaning())
                .build();
        String wordJson = objectMapper.writeValueAsString(updatedWord);

        mockMvc.perform(
                MockMvcRequestBuilders.put("/words/" + testWordA.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(wordJson)
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("word").value(testWordA.getWord())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("pronunciation").value(testWordA.getPronunciation())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("type").value(testWordA.getType())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("meaning").value(testWordA.getMeaning())
        );
    }

    @Test
    public void testThatWordPartiallyUpdated() throws Exception{
        WordEntity testWordA = TestDataUtil.createTestWordA();
        wordService.save(testWordA);

        testWordA.setWord("Updated");
        WordEntity updatedWord = WordEntity.builder()
                .word(testWordA.getWord())
                .build();
        String wordJson = objectMapper.writeValueAsString(updatedWord);

        mockMvc.perform(
                MockMvcRequestBuilders.patch("/words/" + testWordA.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(wordJson)
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("word").value(testWordA.getWord())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("pronunciation").value(testWordA.getPronunciation())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("type").value(testWordA.getType())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("meaning").value(testWordA.getMeaning())
        );
    }

    @Test
    public void testThatWordDeleted() throws Exception{
        WordEntity testWordA = TestDataUtil.createTestWordA();
        wordService.save(testWordA);

        mockMvc.perform(
                MockMvcRequestBuilders.delete("/words/" + testWordA.getId())
        ).andExpect(
                MockMvcResultMatchers.status().isNoContent()
        );
    }
}
