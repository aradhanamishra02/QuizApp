package com.aradhana.quizapp.service;
import com.aradhana.quizapp.model.Question;
import com.aradhana.quizapp.dao.QuestionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;
@Service
public class questionService {
    @Autowired
    QuestionDao questionDao;
    public ResponseEntity <List<Question> > getAllQuestion() {
        try {
            return new ResponseEntity<>(questionDao.findAll(), HttpStatus.OK);
        } catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_GATEWAY);
    }
    public ResponseEntity< List<Question>> getQuestionsByCategory(String category) {
        try {
            return new ResponseEntity<>(questionDao.findByCategory(category),HttpStatus.OK);
        } catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_GATEWAY);
    }
    @PostMapping("add/")
    public ResponseEntity<String> addQuestion(Question question) {
         questionDao.save(question);
        return new ResponseEntity<>("sucesss",HttpStatus.CREATED);
    }
}
