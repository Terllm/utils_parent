package com.terllm.mvc.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DemoController {


    @RequestMapping("demo")
    @ResponseBody
    public String demo(){

        return "hello world !!!";

    }


    @RequestMapping("index")
    public String index(){

        return "index";

    }



}
