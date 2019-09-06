package com.terllm.test;

import com.terllm.utils.ZipUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Controller
public class DemoController {


    @RequestMapping("demo")
    public void demo(HttpServletRequest req, HttpServletResponse res) throws IOException {

     //   res.setContentType("application/x-zip-compressed");
        res.setHeader("Content-Disposition","attachment;filename=34.zip");
        res.setCharacterEncoding("UTF-8");
        ServletOutputStream write =  res.getOutputStream();
        String[] str = new String[3];
        str[0]="E:\\01.jpg";
        str[1]="E:\\02.jpg";
        str[2]="E:\\001.jpg";
        ByteArrayOutputStream bos =  ZipUtils.getZipCompress(str);
        write.write(bos.toByteArray());

        bos.close();
        write.flush();
        write.close();

    }







}
