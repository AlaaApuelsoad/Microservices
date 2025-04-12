package com.alaa.quiz_service.feign;

import com.alaa.quiz_service.model.QuestionWrapper;
import com.alaa.quiz_service.model.Response;
import org.springframework.cloud.openfeign.FeignClient;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "QUESTION-SERVICE/service/questions")
public interface QuizInterface {

    @GetMapping("/generateQuestionsForQuiz")
    ResponseEntity<List<Long>> getQuestionsIdsForQuiz(
            @RequestParam("categoryName") String categoryName,
            @RequestParam("numQ") int numQ);

    @PostMapping("/getQuestionsForQuiz")
    ResponseEntity<List<QuestionWrapper>> getQuestionsForQuiz(@RequestBody List<Long> questionIds);

    @PostMapping("/getScore")
    ResponseEntity<Integer> getScore(@RequestBody List<Response> responses);
}
