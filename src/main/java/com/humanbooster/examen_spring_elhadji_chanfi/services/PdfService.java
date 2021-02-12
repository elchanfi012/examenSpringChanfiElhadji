package com.humanbooster.examen_spring_elhadji_chanfi.services;


import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import com.humanbooster.examen_spring_elhadji_chanfi.models.Advert;
import com.lowagie.text.DocumentException;

@Service
public class PdfService {
	
	public static String output = "src/main/resources/static/pdf/rest_with_spring.pdf";
	
	private String parseThymeleafTemplate(Advert advert) {
		ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
		templateResolver.setSuffix(".html");
		templateResolver.setTemplateMode(TemplateMode.HTML);
		
		TemplateEngine templateEngine = new TemplateEngine();
		templateEngine.setTemplateResolver(templateResolver);
		
		Context context = new Context();
		context.setVariable("advert", advert);
		return templateEngine.process("templates/advert/advert_pdf_template.html", context);
	}
	
	public void generatePdfFromHtml(Advert advert) throws IOException, DocumentException {
		String html = this.parseThymeleafTemplate(advert);
		OutputStream os = new FileOutputStream(output);
		ITextRenderer renderer = new ITextRenderer();
		renderer.setDocumentFromString(html);
		renderer.layout();
		renderer.createPDF(os);
		
		os.close();
	}
}
