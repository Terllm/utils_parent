package com.terllm.utils;

import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.plugins.jpeg.JPEGImageWriteParam;
import javax.imageio.stream.ImageInputStream;


/**
 * 图片压缩
 * @author terllm 2019-08-15
 *
 */
public class ImageCompressionUtils implements Runnable{

	public static final String SUFFIX_TYPE = "jpg";
	
	private File file;
	

	private static void compressPic(File file) throws Exception {

		BufferedImage src = null;
		FileOutputStream out = null;
		ImageWriter imgWrier;
		ImageWriteParam imgWriteParams;
		ImageInputStream imagein= null;
		// 指定写图片的方式为 jpg
		imgWrier = ImageIO.getImageWritersByFormatName(SUFFIX_TYPE).next();
		ImageIO.createImageInputStream(file);
		imgWriteParams = setImageParams(file);

		src = ImageIO.read(file);
		out = new FileOutputStream(file);
		imgWrier.reset();
		// 必须先指定 out值，才能调用write方法, ImageOutputStream可以通过任何
		// OutputStream构造
		imgWrier.setOutput(ImageIO.createImageOutputStream(out));
		// 调用write方法，就可以向输入流写图片
		imgWrier.write(null, new IIOImage(src, null, null), imgWriteParams);
		out.flush();
		out.close();

		
		
	}

	private static ImageWriteParam setImageParams(File ins) throws IOException {
		ImageWriteParam imgWriteParams;
		imgWriteParams = new JPEGImageWriteParam(null);
		// 要使用压缩，必须指定压缩方式为MODE_EXPLICIT
		imgWriteParams.setCompressionMode(imgWriteParams.MODE_EXPLICIT);
		// 这里指定压缩的程度，参数qality是取值0~1范围内，
		imgWriteParams.setCompressionQuality((float) .66);
		imgWriteParams.setProgressiveMode(imgWriteParams.MODE_DISABLED);
		ColorModel colorModel = ImageIO.read(ins).getColorModel();// ColorModel.getRGBdefault();
		// 指定压缩时使用的色彩模式
		imgWriteParams.setDestinationType(
				new javax.imageio.ImageTypeSpecifier(colorModel, colorModel.createCompatibleSampleModel(16, 16)));
		return imgWriteParams;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		try {
			compressPic(file);
		} catch (Exception e) {
			// TODO Auto-generated catch block

		}
		
		
	}
	
	
	protected ImageCompressionUtils(File file) {
		this.file = file;
	}

	
	public static void getInstance(File file) {
		
		ImageCompressionUtils imageUtils = new ImageCompressionUtils(file);
		Thread thread = new Thread(imageUtils);  
		thread.start();  
		
	}
	

}
