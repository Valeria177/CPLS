package com.sas.alex.controllers;

import com.sas.alex.dto.auth.someQuest.QuestionResponse;
import com.sas.alex.repository.UserRepository;
import com.sas.alex.service.TestService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
public class TestController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    TestService testService;

    @GetMapping("/questions")
    @ApiOperation("Список вопросов")
    public ResponseEntity<QuestionResponse> questions() {
            return ResponseEntity.ok(new QuestionResponse(testService.getAllQuestions()));
    }

    //Тут ещё куча говна по типу старта теста, статистики и чот ещё.
}
