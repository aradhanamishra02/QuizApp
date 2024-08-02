package com.aradhana.quizapp.service;

import com.aradhana.quizapp.dao.QuestionDao;
import com.aradhana.quizapp.dao.QuizDao;
import com.aradhana.quizapp.model.Question;
import com.aradhana.quizapp.model.QuestionWrapper;
import com.aradhana.quizapp.model.Quiz;
import com.aradhana.quizapp.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {
    @Autowired
    QuizDao quizDao;
    @Autowired
    QuestionDao questionDao;
    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {
        List<Question> questions= questionDao.findRandomQuestionsByCategory(category,numQ);
        Quiz quiz =new Quiz();
        quiz.setTitle(title);
        quiz.setQuestion(questions);

        quizDao.save(quiz);
        return new ResponseEntity<>("sucess", HttpStatus.CREATED);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestion(Integer id) {
        Optional<Quiz> quiz=quizDao.findById(id);
        List<Question> questionFromDB=quiz.get().getQuestion();
        List<QuestionWrapper> questionForUser=new ArrayList<>();
        for(Question q:questionFromDB){
            QuestionWrapper qw=new QuestionWrapper(q.getId(),q.getQuestionTitle(),q.getOption1(),q.getOption2(),q.getOption3(),q.getOption4());
            questionForUser.add(qw);
        }
        quizDao.findById(id);
        return new ResponseEntity<>(questionForUser,HttpStatus.OK);
    }

    public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {
        Quiz quiz=quizDao.findById(id).get();
        List<Question> questions=quiz.getQuestion();
        int right=0,i=0;
        for(Response response:responses){
            if(response.getResponse().equals(questions.get(i).getRightAnswer()))
                right++;
            i++;
        }
        return new ResponseEntity<>(right,HttpStatus.OK);
    }
}

