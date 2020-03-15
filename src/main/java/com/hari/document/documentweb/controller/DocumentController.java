package com.hari.document.documentweb.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import com.hari.document.documentweb.entities.Document;
import com.hari.document.documentweb.repo.DocumentRepository;


@Controller
public class DocumentController {
	
	@Autowired
	DocumentRepository repository;
	
	@RequestMapping("/")
	public String showUpload(ModelMap model) {
		List<Document> documents = repository.findAll();
		model.addAttribute("documents",documents);
		return "documentupload";
	
	}
	
	@RequestMapping(value="/upload",method= RequestMethod.POST)
	public String uploadDocument(@RequestParam("document") MultipartFile multipartFile,@RequestParam("id") Long id,ModelMap model) {
		Document document = new Document();
		document.setId(id);
		document.setName(multipartFile.getOriginalFilename());
		try {
			document.setData(multipartFile.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
		repository.save(document);
		List<Document> documents = repository.findAll();
		model.addAttribute("documents",documents);
		return "documentupload";
	
	}
	
	@RequestMapping("/download")
	public StreamingResponseBody download(@RequestParam("id")Long id,HttpServletResponse response ) {
		Document document = repository.findById(id).get();
		System.out.println("ame="+document.getName());
		byte[] data = document.getData();
		response.setHeader("Content-Disposition", "attachment:filename-download.jpeg");
		
		return outputstream->{
			outputstream.write(data);
		};
	
	}
}
