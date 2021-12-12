package com.sas.alex.controllers;

import com.sas.alex.dto.auth.response.MessageResponse;
import com.sas.alex.dto.for_answer.InputRequest;
import com.sas.alex.dto.some.QuestionResponse;
import com.sas.alex.model.User;
import com.sas.alex.repository.ResultRepository;
import com.sas.alex.repository.UserRepository;
import com.sas.alex.service.TestService;
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
    public ResponseEntity<MessageResponse> endTest(Principal principal) {
        User user = userRepository.findByUsername(principal.getName()).get();

        if (testService.finishTest(user))
            return ResponseEntity.ok(new MessageResponse("Test is finished."));
        else
            return ResponseEntity.badRequest().body(new MessageResponse("Error: not all answer."));
    }

    @PostMapping("/sendAnswer")
    @ApiOperation("Отправить ответ на вопрос")
    public ResponseEntity<MessageResponse> sendAnswer(@Valid @RequestBody InputRequest inputRequest, Principal principal){
        User user = userRepository.findByUsername(principal.getName()).get();
        if(testService.giveAnswer(user, inputRequest.getIdQuest(), inputRequest.getIdAnswer()))
            return ResponseEntity.ok(new MessageResponse("Ok"));
        else
            return ResponseEntity.badRequest().body(new MessageResponse("Error!"));
    }

    //Подтяну штанишки, прочту книгу по этому тесту и посчитаю баллы за вопросы. А там и работать всё будет
    /*@PostMapping("/result")
    @ApiOperation("Результат прохождения")
    public ResponseEntity<?> getResult(){
        return ResponseEntity.ok(new );
    }*/

}
