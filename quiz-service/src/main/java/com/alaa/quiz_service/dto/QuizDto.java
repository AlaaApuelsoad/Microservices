package com.alaa.quiz_service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuizDto {

    @JsonProperty("categoryName")
    private String categoryName;
    @JsonProperty("numQ")
    private int numQuestions;
    private String title;
}
