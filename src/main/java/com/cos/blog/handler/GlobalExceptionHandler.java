package com.cos.blog.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

// IlligalArgumentException 이 그냥 진행하게 놔두면, 다른 에러 문구까지 지저분하게 발생함
// 그래서 Exception이 발생했을때, 받아서 깔끔하게 오류문만 실행되는 함수를 만듬.

@ControllerAdvice       // Exception이 발생했을때, 이 파일을 실행시키려면, 어디에서든 여기로 오려면, ControllerAdvice 가 있어야함
@RestController
public class GlobalExceptionHandler {

//    @ExceptionHandler(value = IllegalArgumentException.class)       // IllegalArgumentException 이 발생하면, Exception에 대한 errer를 e 함수로 전달
//    public String handleArgumentException(IllegalArgumentException e) {
//        return "<h1>" + e.getMessage() + "</h1>";
//    }

    // 만약 모든 Exception을 받고 싶으면, 상위로 걸면 됨.
    @ExceptionHandler(value = Exception.class)       // IllegalArgumentException 이 발생하면, Exception에 대한 errer를 e 함수로 전달
    public String handleArgumentException(Exception e) {
        return "<h1>" + e.getMessage() + "</h1>";
    }
}
