package com.smerp.util;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FilenameUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;



public class FilePathUtil {
	
	private static final Logger logger = LogManager.getLogger(FilePathUtil.class);

	public static Map<String, String>  getFilePath(MultipartFile multipartFile,String saveFilePath,String folderName) {
		Map<String, String> responseObj=new HashMap<String, String>();
		
		String fullPath = multipartFile.getOriginalFilename();
		String pathToSave = null;
		File f = new File(saveFilePath);
		if(!f.isDirectory())
			f.mkdirs();
		File file = new File(f + File.separator + folderName);
		if (!file.exists()) {
			file.mkdir();
		}
		fullPath = file + File.separator+multipartFile.getOriginalFilename().substring(0, multipartFile.getOriginalFilename().lastIndexOf("."))+DateFormat.getTime()+"."+FilenameUtils.getExtension(multipartFile.getOriginalFilename());

		/*if (fullPath.length() > 1 && Character.isLetter(fullPath.charAt(0)) && fullPath.charAt(1) == ':') {
			pathToSave = fullPath.substring(3);
		}*/
		
		if (fullPath.length() > 1) {
			pathToSave = fullPath.substring(1);
		}
		
		responseObj.put("fullPath", fullPath);
		responseObj.put("pathToSave", pathToSave);
		
		return responseObj;
	}

	public static String saveFile(MultipartFile multipartFile, String filePath) throws IOException {
		byte [] buf=multipartFile.getBytes();
		InputStream inputStream = new ByteArrayInputStream(buf);
		String response;
		try {
			OutputStream outpuStream = null;
			int read = 0;
			byte[] bytes = new byte[8192];
			outpuStream = new FileOutputStream(new File(filePath));
			while ((read = inputStream.read(bytes)) != -1) {

				outpuStream.write(bytes, 0, read);
			}
			outpuStream.flush();
			outpuStream.close();
			inputStream.close();
			response = "File saved Successfully";
		} catch (IOException e) {
			response = "Execption Occured while Saving the file";

		}
		return response;
	}
	
	
	public static String convertPath(String logoPath) {
	//String logoPath ="data\\Company\\logo\\images20181224_101326.jpg'";
	logoPath = logoPath.replaceAll("\\\\", "/");  //" convert  \ to /"
	logger.info("logoPath-->" +logoPath);
	return logoPath;
	}
	
}
