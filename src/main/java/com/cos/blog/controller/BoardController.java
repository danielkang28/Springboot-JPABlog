package com.cos.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BoardController {

    @GetMapping("/")
    public String index()   {

        // /WEB-INF/views/index.jsp
        return "index";     // application.yml 에 spring 파트를 적어놨기 때문에, prifix로 앞에(/WEB-INF/views/), suffix로 뒤에(.jsp) 붙음
    }
}
