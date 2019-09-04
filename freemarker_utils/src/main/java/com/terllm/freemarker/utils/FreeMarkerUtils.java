package com.terllm.freemarker.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

@Component
public class FreeMarkerUtils {
	
	private Logger log =LoggerFactory.getLogger(getClass());
	
	@Autowired
	private FreeMarkerConfigurer freeMarkerConfigurer;
	 
	
	
	public  void freeMarkerUtils(File targetFile,Map<String,Object> paramMap,String templateName) {
		
		Configuration configuration = freeMarkerConfigurer.getConfiguration();
		BufferedWriter writer = null;
		/*File targetFile =new File("E:\\one.xls");
		Map<String,String> paramMap = new HashMap<String,String>();*/
		
		try {
			
			Template template = configuration.getTemplate(templateName);
			writer = new BufferedWriter(new OutputStreamWriter(  
                    new FileOutputStream(targetFile), Charset.forName("utf-8")));
            template.process(paramMap, writer); 
            writer.flush();  
  
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TemplateException e) {
			e.printStackTrace();
		}
		finally
		{
			try {
				writer.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		
	}
	
	//页面响应
	public void getResponse(HttpServletResponse response,File targetFile) {
		ServletOutputStream out=null;
		InputStream in = null;
		try {
			
			response.reset();
			response.setContentType("application/msword");
			String exportFileName = new String(targetFile.getName().getBytes("gb2312"),"iso8859-1");
			// 设定输出头文件
			response.setHeader("Content-Disposition", "attachment;fileName=" + exportFileName);
			
			 out = response.getOutputStream();
			 in = new FileInputStream(targetFile);
			byte[] buffer = new byte[1024];
			int i = -1;
			while ((i = in.read(buffer)) != -1) {
				out.write(buffer, 0, i);
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
			log.error("响应失败：Exception： "+e.getMessage());
			throw new RuntimeException(e);
		}finally {
			try {
				out.close();
				in.close();
				
			} catch (Exception e2) {
				// TODO: handle exception
				log.error("关闭流失败：Exception："+e2.getMessage());
				throw new RuntimeException(e2);
			}
			
			
		}
		
		
		
	}
	
	
	
	
}
