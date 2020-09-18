package com.terllm.emails.test;

import com.terllm.emails.commons.Emails;
import com.terllm.emails.constant.EmailConstants;
import org.apache.commons.mail.DataSourceResolver;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.ImageHtmlEmail;
import org.apache.commons.mail.resolver.DataSourceCompositeResolver;
import org.apache.commons.mail.resolver.DataSourceFileResolver;
import org.apache.commons.mail.resolver.DataSourceUrlResolver;

import java.net.MalformedURLException;
import java.net.URL;

public class ImageHtmlEmailTest {

    public static void main(String[] args) throws EmailException, MalformedURLException {

        ImageHtmlEmail imageHtmlEmail = (ImageHtmlEmail) Emails.constrator(ImageHtmlEmail.class);


        imageHtmlEmail.addTo(EmailConstants.RECEIVE_USER);

        imageHtmlEmail.setMsg("<!DOCTYPE html>"
                +"<html>"
                +"<head>"
                +"<meta charset='UTF-8'>"
                +"<title>"+"</title>"
                +"</head>"
                +"<body>"
                +"<h1>imagehtml</h1>"
                +"<img style='width:100% height:100%' src='E:\\个人/阿丽塔.jpg'></img>"
                +"<img style='width:100% height:100%' src='https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1563942171144&di=fad0cb694ff9f431e87356f991d07c53&imgtype=0&src=http%3A%2F%2F5b0988e595225.cdn.sohucs.com%2Fimages%2F20170913%2Fb82cac751d7f48529d62bad9ed2a6ddf.jpeg'></img>"
                +"</body>"
                +"</html>");

        imageHtmlEmail.setSubject("html测试");
        //DataSourceResolver
        DataSourceResolver[] dataSourceResolvers = {new DataSourceFileResolver(),//解析本地图片
                new DataSourceUrlResolver(new URL("https://"))}; //解析网络图片


        imageHtmlEmail.setDataSourceResolver(new DataSourceCompositeResolver(dataSourceResolvers));

        imageHtmlEmail.send();



    }




}
