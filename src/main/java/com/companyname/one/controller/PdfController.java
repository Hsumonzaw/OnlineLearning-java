package com.companyname.one.controller;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.companyname.one.dto.PDFCentificate;
import com.lowagie.text.DocumentException;

@RestController
@RequestMapping("/api/v1/")
public class PdfController {

	 	@GetMapping("/free/pdf/certificate")
	    public void pdfInvoice(HttpServletResponse response,@RequestParam("score")int score,
	    		@RequestParam("languagesName")String languagesName) throws DocumentException, IOException { 

	 		try {
		 		response.setContentType("application/pdf");
		        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		        String currentDateTime = dateFormatter.format(new Date());
		         
		        String headerKey = "Content-Disposition";
		        String headerValue = "attachment; filename=users_" + currentDateTime + ".pdf";
		        response.setHeader(headerKey, headerValue);
		        PDFCentificate exporter = new PDFCentificate();
			     exporter.export(response,score,languagesName);
	 		}catch (Exception e) {
				// TODO: handle exception
			}

	        
	    }
	 	
}
