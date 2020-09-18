package com.terllm.freemarker.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
public class DemoController {



    @RequestMapping("demo")
    @ResponseBody
    public String demo(){

        return "hello world !!!";

    }


    @RequestMapping("index")
    public String index(Map<String,Object> map){

        map.put("name","hello world !!!");

        return "index";

    }



}
