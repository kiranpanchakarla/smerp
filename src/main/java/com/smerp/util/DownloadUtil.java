package com.smerp.util;

import java.io.File;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
@Component
public class DownloadUtil {

	@Value(value = "${file.download.path}")
	private String downloadPath;

	public String getDownloadPath() {
		if (!downloadPath.endsWith("/")) {
			return downloadPath + "/";
		}
		return downloadPath;
	}

	public boolean deleteFile(File file) {
		try {
			return file.delete();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return false;
	}
}
