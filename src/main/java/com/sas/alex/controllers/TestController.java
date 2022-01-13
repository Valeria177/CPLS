package com.sas.alex.controllers;

import com.sas.alex.dto.auth.response.MessageResponse;
import com.sas.alex.dto.for_answer.InputRequest;
import com.sas.alex.dto.some.*;
import com.sas.alex.model.User;
import com.sas.alex.repository.UserRepository;
import com.sas.alex.service.TestService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

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

    @GetMapping("/answer")
    @ApiOperation("Вернёт список ответов")
    public  ResponseEntity<AnswerResponse> answers(){
        return ResponseEntity.ok(new AnswerResponse(testService.getAnswers()));
    }

    @PostMapping("/startTest")
    @ApiOperation("Начало теста. Если старая попытка осталась, дропаем")
    public ResponseEntity<MessageResponse> startTest(Principal principal) {
        User user = userRepository.findByUsername(principal.getName()).get();

        if (testService.startTest(user))

            return ResponseEntity.ok(new MessageResponse("Поееееехали!"));
        else

            return ResponseEntity.badRequest().body(new MessageResponse("Error"));
    }

    @PostMapping("/finishedTest")
    @ApiOperation("Завершить прохождение")
    public ResponseEntity<?> endTest(Principal principal) {
        User user = userRepository.findByUsername(principal.getName()).get();
        Long result = testService.finishTest(user);
        if (result!=null)
            return ResponseEntity.ok(new TestResult(result));
        else
            return ResponseEntity.badRequest().body(new MessageResponse("Error: not all question have answer."));
    }

    @PostMapping("/sendAnswer")
    public ResponseEntity<MessageResponse> sendAnswer(@Valid @RequestBody InputRequest inputRequest, Principal principal){


        User user = userRepository.findByUsername(principal.getName()).get();

        if(testService.sendAnswer(user, inputRequest.getIdQ(), inputRequest.getIdA()))
            return ResponseEntity.ok(new MessageResponse("Ok"));
        else


            return ResponseEntity.badRequest().body(new MessageResponse("Error!"));
    }

    //Подтяну штанишки, прочту книгу по этому тесту и посчитаю баллы за вопросы. А там и работать всё будет
    @GetMapping("/results")
    @ApiOperation("Получает данные о пройденном тесте и возвращает результат прохождения теста")
    public ResponseEntity<ResultResponse> results(@RequestParam Integer scores, @RequestParam Long id){
        //return ResponseEntity.ok(new ResultResponse(testService.results(attemptRequestID.getId())));
        return ResponseEntity.ok(new ResultResponse(testService.getResults(scores, id)));
    }

    @GetMapping("/attempts")
    @ApiOperation("Вернёт все попытки пользователя")
    public ResponseEntity<?> attempts(Principal principal){
        User user = userRepository.findByUsername(principal.getName()).get();
        return ResponseEntity.ok(new AttemptResponse(testService.getUserAttempts(user)));
    }



}



