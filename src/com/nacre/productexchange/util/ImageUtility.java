package com.nacre.productexchange.util;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.codec.binary.Base64;

import com.nacre.productexchange.dto.ProductDto;

public class ImageUtility
{
		public static String encodeImage(InputStream imgInputStream){
		
		String encodedImage = "";
		
		Base64 b64 = new Base64();
		byte[] imgData = null;
		
		try {
			imgData = new byte[imgInputStream.available()];
			int i = 0;
			while (imgInputStream.available() > 0) {
				
				imgData[i] = (byte) imgInputStream.read();
				i++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		encodedImage = b64.encodeAsString(imgData);
		
	//	System.out.println("Encoded Image: "+encodedImage);
		
		return encodedImage;
		}		
	public static String BinaryToString(ProductDto regproduct)throws IOException
	{
		InputStream str=null;
		int i=0;
		
		str = regproduct.getProduct_image();
		
		Base64 base = new Base64();
		
		byte[] byteArray = new byte[str.available()];
		while(str.available()>0){
			byteArray[i] = (byte) str.read();
			i++;
		}
		//System.out.println("String---->"+base.encodeAsString(byteArray));
		
		return base.encodeAsString(byteArray);
		
	}
}
