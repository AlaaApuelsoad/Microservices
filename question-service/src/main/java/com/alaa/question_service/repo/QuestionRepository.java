package com.alaa.question_service.repo;

import com.alaa.question_service.model.QuestionWrapper;
import com.alaa.question_service.model.Questions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Questions,Long> {


    @Query(value = "SELECT * FROM questions WHERE LOWER(category) LIKE LOWER(CONCAT('%',:category,'%'))",nativeQuery = true)
    List<Questions> getAllQuestionsByCategory(@Param("category") String category);


    @Query(value = "SELECT * FROM questions WHERE LOWER(category) LIKE LOWER(:category) ORDER BY RANDOM() LIMIT :numQ",nativeQuery = true)
    List<Questions> findRandomQuestionByCategory(@Param("category") String category, @Param("numQ") int numQ);

    @Query(value = "SELECT question_id FROM questions WHERE LOWER(category) LIKE LOWER(:categoryName)" +
            " ORDER BY RANDOM() LIMIT :numQ",nativeQuery = true)
    List<Long> getQuestionsIdsByCategory(@Param("categoryName") String category,int numQ);

    @Query(value = "SELECT new com.alaa.question_service.model.QuestionWrapper" +
            "(q.id,q.questionTitle,q.option1,q.option2,q.option3,q.option4) " +
            "From Questions q WHERE q.id IN :questionsIds")
    List<QuestionWrapper> getQuestionsByIds(@Param("questionsIds") List<Long> questionsIds);
}
