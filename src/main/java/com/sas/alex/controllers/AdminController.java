package com.sas.alex.controllers;

import com.sas.alex.dto.admin.AnswerCreateRequest;
import com.sas.alex.dto.admin.ChangeTextRequest;
import com.sas.alex.dto.admin.QuestionCreateRequest;
import com.sas.alex.dto.admin.QuestionDeleteRequest;
import com.sas.alex.dto.auth.response.MessageResponse;
import com.sas.alex.dto.some.AnswerResponse;
import com.sas.alex.dto.some.AttemptResponse;
import com.sas.alex.dto.some.QuestionResponse;
import com.sas.alex.service.AdminService;
import com.sas.alex.service.TestService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private TestService testService;

    @Autowired
    private AdminService adminService;


    @GetMapping("/allAttempt")
    @ApiOperation("Вернёт все попытки")
    public ResponseEntity<AttemptResponse> attempts1(){
        return ResponseEntity.ok(new AttemptResponse(testService.getAllAttempt()));
    }

    @GetMapping("/questions")
    @ApiOperation("Вернёт все вопросы")
    public ResponseEntity<QuestionResponse> questions(){
        return ResponseEntity.ok(new QuestionResponse(testService.getAllQuestions()));
    }

    /*@GetMapping("/questionById")
    @ApiOperation("Вернёт вопрос по айдишнику")
    public ResponseEntity<QuestionResponse> getQuestionById(@Valid @RequestBody QuestionRequestById questionRequestById) {
       return ResponseEntity.ok(new QuestionResponse(testService.getQuestionById(questionRequestById.getId())));
    }*/

    @PostMapping("/newQuestion")
    @ApiOperation("Создаёт вопрос")
    public ResponseEntity<MessageResponse> account(@Valid @RequestBody QuestionCreateRequest questionCreateRequest) {
        if(adminService.addQuest(questionCreateRequest.getText(), questionCreateRequest.getNumber(), questionCreateRequest.getInf_id()))
            return ResponseEntity.ok(new MessageResponse("Question add successfully!"));
        else
            return ResponseEntity.badRequest().body(new MessageResponse("Error"));

    }

    @PostMapping("/delQuestion")
    @ApiOperation("Удаляем вопрос")
    public ResponseEntity<MessageResponse> delQuest(@Valid @RequestBody QuestionDeleteRequest questionDeleteRequest ) {
        if(adminService.deleteQuestion(questionDeleteRequest.getId()))
            return ResponseEntity.ok(new MessageResponse("Question has been deleted"));
        else
            return ResponseEntity.badRequest().body(new MessageResponse("Error"));

    }

    @PostMapping("/editQuestion")
    @ApiOperation("Редактирование вопроса")
    public ResponseEntity<MessageResponse> editQuestion(@Valid @RequestBody ChangeTextRequest changeTextRequest) {
        if(adminService.changeTextRequest(changeTextRequest.getText(), changeTextRequest.getId()))
            return ResponseEntity.ok(new MessageResponse("Text has been changed!"));
        else
            return ResponseEntity.badRequest().body(new MessageResponse("Error!"));

    }

    @PostMapping("/newAnswer")
    @ApiOperation("Создаёт ответ")
    public ResponseEntity<MessageResponse> newAnswer(@Valid @RequestBody AnswerCreateRequest answerCreateRequest){
        if(adminService.addAnswer(answerCreateRequest.getText(), answerCreateRequest.getNumber(), answerCreateRequest.getScores()))
            return ResponseEntity.ok(new MessageResponse("Answer add successfully!"));
        else
            return ResponseEntity.badRequest().body(new MessageResponse("Error"));
    }

    @PostMapping("/editAnswer")
    @ApiOperation("Редактирование текста ответа. ТОЛЬКО ТЕКСТ, БАЛЛЫ И НОМЕР НЕ ДЕЛАЛ.")
    public ResponseEntity<MessageResponse> editAnswer(@Valid @RequestBody ChangeTextRequest changeTextRequest){
        if(adminService.changeTextAnswerRequest(changeTextRequest.getText(), changeTextRequest.getId()))
            return ResponseEntity.ok(new MessageResponse("Text has been changed!"));
        else
            return ResponseEntity.badRequest().body(new MessageResponse("Error!"));
    }

}
