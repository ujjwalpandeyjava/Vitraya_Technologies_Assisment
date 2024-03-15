package com.vitraImageTextExtraction.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Blob;
import java.util.Map;

import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.vitraImageTextExtraction.entities.Image;
import com.vitraImageTextExtraction.repository.ImageRepository;

import net.sourceforge.tess4j.Tesseract;

@RestController
@CrossOrigin(value = { "*" })
public class ImageProcessingController {

	@Autowired
	private ImageRepository imageRepository;

	@PostMapping("/img")
	public ResponseEntity<Map<String, Object>> uploadImage(@RequestParam MultipartFile file) {
		try {
			Image img = new Image();
			img.setName(file.getOriginalFilename());
			img.setData(BlobProxy.generateProxy(file.getBytes()));
			Image savedImage = imageRepository.save(img);
			String textFromImage = processImage(savedImage);
			return new ResponseEntity<>(
					Map.of("id", savedImage.getId(), "name", savedImage.getName(), "text", textFromImage),
					HttpStatus.OK);
		} catch (IOException e) {
			return new ResponseEntity<>(Map.of("Message", e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	private String processImage(Image savedImage) {
		System.out.println(savedImage);

		try {

			Blob blob = savedImage.getData();
			System.out.println("Read " + blob.length() + " bytes ");
			byte[] array = blob.getBytes(1, (int) blob.length());
			File file = File.createTempFile("something-", ".binary", new File("."));
			FileOutputStream out = new FileOutputStream(file);
			out.write(array);

		  Tesseract tesseract = new Tesseract();
			  tesseract.setDatapath(".");
		  
		} catch (Exception e) {}
		 
		return "will be text";
	}
}
