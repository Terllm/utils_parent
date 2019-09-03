package com.terllm.utils;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/***
 *
 * @author terllm 2019-09-03
 * zip文件打包
 *
 */
public class ZipUtils {


    /**
     * 获取压缩内存流
     * @param files
     * @return
     * @throws IOException
     */
    public static ByteArrayOutputStream getZipCompress(String[] files) throws IOException {

        if(files.length ==0) throw new NullPointerException("files = null");

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ZipOutputStream zos = new ZipOutputStream(bos);
        zipCompress(zos,files);
        zos.flush();
        zos.close();
        return bos;

    }

    /**
     * 打压缩包
     * @param zos
     * @param files
     * @throws IOException
     */
    public static void zipCompress(ZipOutputStream zos,String[] files) throws IOException {


        for(String filePath:files){

            File file = new File(filePath);
            if(!file.exists()) continue;
            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
            ZipEntry entry = new ZipEntry(file.getName());
            zos.putNextEntry(entry);
            int len;
            byte[] b = new byte[1024];
            while ((len = bis.read(b)) != -1) {
                zos.write(b, 0, len);
            }
            bis.close();


        }



    }





    public static void main(String[] args) throws IOException {

        String[] str = new String[3];
        str[0]="E:\\01.jpg";
        str[1]="E:\\02.jpg";
        str[2]="E:\\001.jpg";

        ByteArrayOutputStream bos =  getZipCompress(str);
        System.out.println(bos.size());
        ByteArrayInputStream bin = new ByteArrayInputStream(bos.toByteArray());
        FileOutputStream fos1 = new FileOutputStream(new File("E:\\45.zip"));
        int len;
        byte[] b = new byte[1024];
        while ((len = bin.read(b)) != -1) {
            fos1.write(b, 0, len);
        }



    }


}
