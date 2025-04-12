package com.alaa.question_service.service;

import com.alaa.question_service.model.QuestionWrapper;
import com.alaa.question_service.model.Questions;
import com.alaa.question_service.model.Response;
import com.alaa.question_service.repo.QuestionRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionRepository questionRepository;

    public List<Questions> loadQuestions() {
        List<Questions> questionsList = Arrays.asList(
                new Questions(
                        "Which keyword is used to inherit a class in Java?",
                        "implements", "extends", "inherit", "super",
                        "extends", "Easy", "Java"
                ),
                new Questions(
                        "What is the size of an int variable in Java?",
                        "2 bytes", "4 bytes", "8 bytes", "Depends on system",
                        "4 bytes", "Easy", "Java"
                ),
                new Questions(
                        "Which interface needs to be implemented to sort a custom object in Java?",
                        "Serializable", "Cloneable", "Comparable", "Iterable",
                        "Comparable", "Medium", "Java"
                ),
                new Questions(
                        "Which SQL statement is used to retrieve data from a database?",
                        "GET", "FETCH", "SELECT", "RETRIEVE",
                        "SELECT", "Easy", "SQL"
                ),
                new Questions(
                        "Which constraint ensures that all values in a column are different in SQL?",
                        "UNIQUE", "PRIMARY KEY", "NOT NULL", "FOREIGN KEY",
                        "UNIQUE", "Medium", "SQL"
                ),
                new Questions(
                        "What does the SQL JOIN clause do?",
                        "Deletes records", "Combines rows from two or more tables", "Updates records", "Creates tables",
                        "Combines rows from two or more tables", "Medium", "SQL"
                )
        );

        questionRepository.saveAll(questionsList);

        return questionRepository.findAll();
    }

    public List<Questions> getAllQuestions() {

        return questionRepository.findAll();
    }

    public List<Questions> getAllQuestionsByCategoryName(String category) {

        return questionRepository.getAllQuestionsByCategory(category);
    }

    public String addQuestion(Questions question) {
        questionRepository.save(question);
        return "Question added successfully";
    }


    public List<Long> getQuestionsIdsByCategory(String categoryName, int numQ) {

        return questionRepository.getQuestionsIdsByCategory(categoryName,numQ);
    }

    public List<QuestionWrapper> getQuestionsByIds(List<Long> questionIds) {
        return questionRepository.getQuestionsByIds(questionIds);
    }

    public Integer calculateScore(List<Response> responses) {

        if (responses.isEmpty()){
            throw new RuntimeException("User answer is empty");
        }

        int score = 0;
        for (Response response : responses) {
            Optional<Questions> question = questionRepository.findById(response.getId());
            if (response.getAnswer().equals(question.get().getRightAnswer())){
                score++;
            }
        }
        return score;
    }
}
