package com.alaa.quiz_service.service;

import com.alaa.quiz_service.dto.QuizDto;
import com.alaa.quiz_service.feign.QuizInterface;
import com.alaa.quiz_service.model.QuestionWrapper;
import com.alaa.quiz_service.model.Quiz;
import com.alaa.quiz_service.model.Response;
import com.alaa.quiz_service.repo.QuizRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QuizService {

    private final QuizRepository quizRepository;
    @Autowired
    private QuizInterface quizInterface;


    public String createQuiz(QuizDto quizDto) {

        List<Long> questionsIds = quizInterface.getQuestionsIdsForQuiz
                (quizDto.getCategoryName(),quizDto.getNumQuestions()).getBody();

        Quiz quiz = new Quiz();
        quiz.setTitle(quizDto.getTitle());
        quiz.setQuestionsIds(questionsIds);
        quizRepository.save(quiz);

        return "Quiz created successfully";
    }

    public List<QuestionWrapper> getQuizQuestions(long quizId) {

        Optional<Quiz> quiz = quizRepository.findById(quizId);

        return quizInterface.getQuestionsForQuiz(quiz.get().getQuestionsIds()).getBody();
    }

    public Integer calculateResult(List<Response> responses) {
        if (responses.isEmpty()){
            throw new RuntimeException("User answer is empty");
        }

        return quizInterface.getScore(responses).getBody();

    }
}
