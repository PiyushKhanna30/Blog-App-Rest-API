package com.piyush.blog.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.piyush.blog.exceptions.APIException;

@Service
public class FileServiceImpl implements FileService {

	@Override
	public String uploadImage(String path, MultipartFile file, int postId) throws IOException {
		if (file.getSize() == 0)
			throw new APIException("Image not found.");
		else if (!file.getContentType().equals(MediaType.IMAGE_JPEG_VALUE)
				&& !file.getContentType().equals(MediaType.IMAGE_PNG_VALUE))
			throw new APIException("Image formats allowed are jpeg/png.");

// create folder if not exists
		File file2 = new File(path);
		if (!file2.exists())
			file2.mkdir();

// file name
		String fileName = postId + "_" + file.getOriginalFilename();
// full path
		String filePath = path + File.separator + fileName;
// check if this already exists delete it
		File file3 = new File(filePath);
		if (file3.exists())
			file3.delete();

// file copy
		Files.copy(file.getInputStream(), Paths.get(filePath));
		return fileName;
	}

	@Override
	public InputStream getResource(String path, String fileName) throws FileNotFoundException {
// full path
		String filePath = path + File.separator + fileName;
		InputStream inputStream = new FileInputStream(filePath);
		return inputStream;
	}

}
