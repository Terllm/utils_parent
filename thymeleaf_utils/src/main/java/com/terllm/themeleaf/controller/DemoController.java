package com.terllm.themeleaf.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class DemoController {

    @RequestMapping("hello")
    @ResponseBody
   public String demo1(){

       return "hello world !!!";

   }

    @RequestMapping("index")
    public String index(Map<String,Object> map){

        map.put("name","hello");
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("12");
        list.add("13");
        map.put("list",list);
        return "index";

    }



}
