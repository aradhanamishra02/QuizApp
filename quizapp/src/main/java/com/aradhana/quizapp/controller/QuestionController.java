package com.aradhana.quizapp.controller;

import com.aradhana.quizapp.model.Question;
import com.aradhana.quizapp.service.questionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("question")
public class QuestionController {
    @Autowired
    questionService questionservice;
    @GetMapping("allQuestion")
    public ResponseEntity<List<Question>> getAllQuestion(){
        return questionservice.getAllQuestion();
    }
    @GetMapping("category/{category}")
    public ResponseEntity<List<Question>> getQuestionsByCategory( String category){
        return questionservice.getQuestionsByCategory(category);

    }
    @PostMapping("/add")
    public ResponseEntity<String> addQuestion(@RequestBody Question question){
        return questionservice.addQuestion(question);
    }
}
