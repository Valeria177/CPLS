package com.sas.alex.controllers;

import com.sas.alex.persist.Question;
import com.sas.alex.service.QuestionService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/question")
    @ApiOperation("Вернёт все вопросы")
    public List<Question> list() {
        return questionService.listAll();
    }

    @GetMapping("/question/{id}")
    @ApiOperation("Вернёт вопрос по айдишнику")
    public ResponseEntity<Question> getQuestion(@PathVariable Long id) {
        try {
            Question question = questionService.getQuestion(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/newQuestion")
    @ApiOperation("Создаёт вопрос. Заголовок - текст")
    public void add(Question question) {
        questionService.save(question);
    }

    @DeleteMapping("/question/{id}")
    @ApiOperation("Удаляем по айдишнику")
    public void delete(@PathVariable Long id) {
        questionService.delete(id);
    }

    @PutMapping("/editQuestion/{id}")
    @ApiOperation("Редактирование вопроса")
    public ResponseEntity<?> update(@RequestBody Question question, @PathVariable Long id) {
        try {
            Question existQuestion = questionService.getQuestion(id);
            questionService.save(question);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //НЕ ПРОДУМАНО
    @GetMapping("/result/{RngShite}")
    @ApiOperation("Получение результата")
    public String result(@PathVariable Integer RngShite){
        return questionService.getResult(RngShite);
    }
}
