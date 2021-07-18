package com.example.study.controller.api;

import com.example.study.model.network.Header;
import com.example.study.model.network.request.UserApiRequest;
import com.example.study.service.UserApiLogicService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/user2")
public class UserEmailApiController {

    @Autowired
    private UserApiLogicService userApiLogicService;

    @PostMapping("")
    public ResponseEntity DuplicateCheckCreate(@RequestBody Header<UserApiRequest> request) {
        log.info("{}", request);
        return userApiLogicService.DuplicateCheckCreate(request);
    }
}
