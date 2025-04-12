package com.alaa.quiz_service.controller;

import com.alaa.quiz_service.dto.QuizDto;
import com.alaa.quiz_service.model.QuestionWrapper;
import com.alaa.quiz_service.model.Response;
import com.alaa.quiz_service.service.QuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz")
@RequiredArgsConstructor
public class QuizController {

    private final QuizService quizService;

    @PostMapping("/create")
    public ResponseEntity<String> createQuiz(@RequestBody QuizDto quizDto) {

        return new ResponseEntity<>(quizService.createQuiz(quizDto), HttpStatus.CREATED);
    }


    @GetMapping("/{quizId}")
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(@PathVariable("quizId") long quizId) {

        return new ResponseEntity<>(quizService.getQuizQuestions(quizId),HttpStatus.OK);
    }

    @PostMapping("/submit")
    public ResponseEntity<Integer> submitQuiz(@RequestBody List<Response> responses) {

        return new ResponseEntity<>(quizService.calculateResult(responses),HttpStatus.OK);
    }

}
