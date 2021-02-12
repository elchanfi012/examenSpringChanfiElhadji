package com.humanbooster.examen_spring_elhadji_chanfi.controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.server.ResponseStatusException;

import com.humanbooster.examen_spring_elhadji_chanfi.models.Advert;
import com.humanbooster.examen_spring_elhadji_chanfi.services.PdfService;
import com.lowagie.text.DocumentException;

@Controller
@RequestMapping(path = "/export")
public class ExportController {
	
	@Autowired
	private PdfService pdfService;
	
	@RequestMapping(value = "/pdf/{advert}", method = RequestMethod.GET)
	public void exportPdf(HttpServletResponse response, @PathVariable(name = "advert", required = false) Advert advert) throws IOException, DocumentException {
		
		if(advert == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Advert Not Found");
		}
		this.pdfService.generatePdfFromHtml(advert);
		
		InputStream is = new FileInputStream(
				new File("src/main/resources/static/pdf/rest_with_spring.pdf"));
		
		IOUtils.copy(is, response.getOutputStream());
		
		response.setContentType("application/octet-stream");
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = formatter.format(new Date());
		
		String headerKey = "Content-Disposition";
		String headervalue = "attachement; filename=export_candidats_" + advert.getTitle() + currentDateTime + ".pdf";
		response.setHeader(headerKey, headervalue);
		
		response.flushBuffer();
	}
}
