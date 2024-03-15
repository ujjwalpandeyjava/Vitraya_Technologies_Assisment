package com.vitraImageTextExtraction.controller;

import java.io.File;
import java.io.IOException;
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
import net.sourceforge.tess4j.TesseractException;

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
			return new ResponseEntity<>(Map.of("id", savedImage.getId(), "name", savedImage.getName(), "text", textFromImage), HttpStatus.OK);
		} catch (IOException e) {
			return new ResponseEntity<>(Map.of("Message", e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	private String processImage(Image savedImage) {
		System.out.println(savedImage);
		
		/*
		
		Tesseract tesseract = new Tesseract(); 
        try { 
//            tesseract.setDatapath("D:/Tess4J/tessdata"); 
  
            // the path of your tess data folder 
            // inside the extracted file 
            String text 
                = tesseract.doOCR(new File("image.jpg")); 
  
            // path of your image file 
            System.out.print(text); 
        } 
        catch (TesseractException e) { 
            e.printStackTrace(); 
        } */
		return "will be text";
	}
}
