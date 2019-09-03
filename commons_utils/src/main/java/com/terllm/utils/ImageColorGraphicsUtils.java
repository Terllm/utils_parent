package com.terllm.utils;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageInputStream;
import java.awt.*;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import static com.terllm.utils.ImageCompressionUtils.SUFFIX_TYPE;

/**
 * @author terllm 2019-09-03
 * @描述 去掉图片色彩
 */
public class ImageColorGraphicsUtils {


    public static void JPEGImageColorOff(File img) throws IOException {



        Image image = ImageIO.read(img);
        int srcH = image.getHeight(null);
        int srcW = image.getWidth(null);
        BufferedImage bufferedImage = new BufferedImage(srcW, srcH,BufferedImage.TYPE_3BYTE_BGR);
        bufferedImage.getGraphics().drawImage(image, 0,0, srcW, srcH, null);
        //灰色系
        bufferedImage=new ColorConvertOp(ColorSpace.getInstance(ColorSpace.CS_GRAY),null).filter (bufferedImage,null);
        FileOutputStream fos = new FileOutputStream(img);
        JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(fos);
        encoder.encode(bufferedImage);
        fos.close();




    }




}
