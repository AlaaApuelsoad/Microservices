package com.alaa.question_service.controller;

import com.alaa.question_service.model.QuestionWrapper;
import com.alaa.question_service.model.Questions;
import com.alaa.question_service.model.Response;
import com.alaa.question_service.service.QuestionService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("questions")
@RequiredArgsConstructor
public class QuestionController {

    private final QuestionService questionService;


    @PostMapping("/load")
    public ResponseEntity<List<Questions>> loadQuestions() {
        return new ResponseEntity<>(questionService.loadQuestions(), HttpStatus.OK);
    }

    @GetMapping("/getAllQuestions")
    public ResponseEntity<List<Questions>> getAllQuestions() {
        return new ResponseEntity<>(questionService.getAllQuestions(), HttpStatus.OK);
    }


    @GetMapping("/filter")
    public ResponseEntity<List<Questions>> getQuestionsByCategory(@RequestParam("category") String category) {
        return new ResponseEntity<>(questionService.getAllQuestionsByCategoryName(category), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<String> addQuestion(@RequestBody Questions question) {
        return new ResponseEntity<>(questionService.addQuestion(question), HttpStatus.CREATED);
    }

    @GetMapping("/generateQuestionsForQuiz")
    public ResponseEntity<List<Long>> getQuestionsIdsForQuiz(
            @RequestParam("categoryName") String categoryName,@RequestParam("numQ") int numQ) {
        return new ResponseEntity<>(questionService.getQuestionsIdsByCategory(categoryName,numQ),HttpStatus.OK);
    }

    @PostMapping("/getQuestionsForQuiz")
    public ResponseEntity<List<QuestionWrapper>> getQuestionsForQuiz(@RequestBody List<Long> questionIds) {
        return new ResponseEntity<>(questionService.getQuestionsByIds(questionIds),HttpStatus.OK);
    }

    @PostMapping("/getScore")
    public ResponseEntity<Integer> getScore(@RequestBody List<Response> responses) {
        return new ResponseEntity<>(questionService.calculateScore(responses),HttpStatus.OK);
    }

}
