package com.companyname.one.dto;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import com.companyname.one.util.ConvertDate;
import com.companyname.one.util.User;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

public class PDFCentificate {
	
	public void export(HttpServletResponse response,int score,String languagesName) throws DocumentException, IOException {
		float widthSize = 105f;
		Document document = new Document(PageSize.A4.rotate());

		PdfWriter.getInstance(document, response.getOutputStream());

		document.open();
		Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
		font.setSize(18);
		//font.setColor(Color.decode("#"+colorCode));

		PdfPTable tableOne = new PdfPTable(3);
		tableOne.setWidthPercentage(widthSize);
		tableOne.setWidths(new float[] {10f,4f, 10f});
		
		PdfPCell cell = new PdfPCell();
		cell.setBorder(PdfPCell.NO_BORDER);
		cell.setPhrase(new Phrase("  ", font));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setPadding(3);
		tableOne.addCell(cell);
		
		String pwd = new File("").getAbsolutePath();
		Image image = Image.getInstance(pwd + "/companyLogo/logo.png");
		cell = new PdfPCell();
		cell.setImage(image);
		cell.setBorder(PdfPCell.NO_BORDER);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setPadding(8);
		tableOne.addCell(cell);

		cell = new PdfPCell();
		cell.setBorder(PdfPCell.NO_BORDER);
		cell.setPhrase(new Phrase("  ", font));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setPadding(3);
		tableOne.addCell(cell);
		document.add(tableOne);
		
		PdfPTable tableTwo = new PdfPTable(3);
		tableTwo.setWidthPercentage(widthSize);
		tableTwo.setWidths(new float[] {10f,20f, 10f});
		
		cell = new PdfPCell();
		cell.setBorder(PdfPCell.NO_BORDER);
		cell.setPhrase(new Phrase("  ", font));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setPadding(3);
		tableTwo.addCell(cell);
		
		font.setSize(30);
		font.setColor(Color.decode("#0d47a1"));
		cell = new PdfPCell();
		cell.setBorder(PdfPCell.NO_BORDER);
		cell.setPhrase(new Phrase(" Certificate of Completion ", font));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setPadding(10);
		cell.setPaddingTop(20);
		tableTwo.addCell(cell);

		cell = new PdfPCell();
		cell.setBorder(PdfPCell.NO_BORDER);
		cell.setPhrase(new Phrase("  ", font));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setPadding(3);
		tableTwo.addCell(cell);
		document.add(tableTwo);
		
		PdfPTable tableThree = new PdfPTable(3);
		tableThree.setWidthPercentage(widthSize);
		tableThree.setWidths(new float[] {10f,10f, 10f});
		
		cell = new PdfPCell();
		cell.setBorder(PdfPCell.NO_BORDER);
		cell.setPhrase(new Phrase("  ", font));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setPadding(3);
		tableThree.addCell(cell);
		
		font = FontFactory.getFont(FontFactory.HELVETICA);
		font.setSize(13);
		font.setColor(Color.black);
		cell = new PdfPCell();
		cell.setBorder(PdfPCell.NO_BORDER);
		cell.setPhrase(new Phrase(" This certificate is proudly presented to ", font));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setPadding(8);
		cell.setPaddingTop(20);
		tableThree.addCell(cell);

		cell = new PdfPCell();
		cell.setBorder(PdfPCell.NO_BORDER);
		cell.setPhrase(new Phrase("  ", font));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setPadding(3);
		tableThree.addCell(cell);
		document.add(tableThree);
		
		PdfPTable tableFour = new PdfPTable(3);
		tableFour.setWidthPercentage(widthSize);
		tableFour.setWidths(new float[] {10f,10f, 10f});
		
		cell = new PdfPCell();
		cell.setBorder(PdfPCell.NO_BORDER);
		cell.setPhrase(new Phrase("  ", font));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setPadding(3);
		tableFour.addCell(cell);
		
		font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
		font.setSize(20);
		font.setColor(Color.decode("#0d47a1"));
		cell = new PdfPCell();
		cell.setBorder(PdfPCell.NO_BORDER);
		cell.setPhrase(new Phrase(User.getUserName(), font));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setPadding(3);
		//cell.setPaddingTop(10);
		tableFour.addCell(cell);

		cell = new PdfPCell();
		cell.setBorder(PdfPCell.NO_BORDER);
		cell.setPhrase(new Phrase("  ", font));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setPadding(3);
		tableFour.addCell(cell);
		document.add(tableFour);
		
		PdfPTable tableFive = new PdfPTable(3);
		tableFive.setWidthPercentage(widthSize);
		tableFive.setWidths(new float[] {10f,22f, 10f});
		tableFive.getDefaultCell().setBorder(0);

		
		cell = new PdfPCell();
		cell.setBorder(PdfPCell.NO_BORDER);
		cell.setPhrase(new Phrase("  ", font));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setPadding(3);
		tableFive.addCell(cell);
		
		font = FontFactory.getFont(FontFactory.HELVETICA);
		font.setSize(13);
		font.setColor(Color.black);
		
//		cell = new PdfPCell();
//		cell.setBorder(PdfPCell.NO_BORDER);
//		cell.setPhrase(new Phrase(+languagesName
//				++score+"/100 ", font));
//		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
//		cell.setPadding(15);
//		cell.setPaddingTop(20);
//		tableFive.addCell(cell);
		PdfPTable tableFiveOne = new PdfPTable(4);
		tableFiveOne.setWidthPercentage(widthSize);
		tableFiveOne.setWidths(new float[] {20f,6f, 14f,7f});
		
		
		
		cell = new PdfPCell();
		cell.setBorder(PdfPCell.NO_BORDER);
		cell.setPhrase(new Phrase(" For successfully completing ", font));
		//cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		//cell.setPadding(3);
		cell.setPaddingTop(20);
		tableFiveOne.addCell(cell);
		
		font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
		font.setSize(16);
		font.setColor(Color.black);
		cell = new PdfPCell();
		cell.setBorder(PdfPCell.NO_BORDER);
		cell.setPhrase(new Phrase(languagesName, font));
		//cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		//cell.setPadding(3);
		cell.setPaddingTop(20);
		tableFiveOne.addCell(cell);
		
		font = FontFactory.getFont(FontFactory.HELVETICA);
		font.setSize(13);
		font.setColor(Color.black);
		cell = new PdfPCell();
		cell.setBorder(PdfPCell.NO_BORDER);
		cell.setPhrase(new Phrase(" quiz with a score of ", font));
		//cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		//cell.setPadding(3);
		cell.setPaddingTop(20);
		tableFiveOne.addCell(cell);
		
		font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
		font.setSize(15);
		font.setColor(Color.black);
		cell = new PdfPCell();
		cell.setBorder(PdfPCell.NO_BORDER);
		cell.setPhrase(new Phrase(score+"/100 ", font));
		//cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		//cell.setPadding(3);
		cell.setPaddingTop(19);
		tableFiveOne.addCell(cell);
		tableFive.addCell(tableFiveOne);

		cell = new PdfPCell();
		cell.setBorder(PdfPCell.NO_BORDER);
		cell.setPhrase(new Phrase("  ", font));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setPadding(3);
		tableFive.addCell(cell);
		document.add(tableFive);
		
		PdfPTable tableSix = new PdfPTable(3);
		tableSix.setWidthPercentage(widthSize);
		tableSix.setWidths(new float[] {10f,20f, 10f});
		
		
		
		cell = new PdfPCell();
		cell.setBorder(PdfPCell.NO_BORDER);
		cell.setPhrase(new Phrase("  ", font));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setPadding(3);
		tableSix.addCell(cell);
		
		font = FontFactory.getFont(FontFactory.HELVETICA);
		font.setSize(11);
		font.setColor(Color.gray);
		cell = new PdfPCell();
		cell.setBorder(PdfPCell.NO_BORDER);
		cell.setPhrase(new Phrase("Date: "+ConvertDate.convertDateToStringDayMonthYear(new Date()), font));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setPadding(5);
		cell.setPaddingTop(20);
		tableSix.addCell(cell);

		cell = new PdfPCell();
		cell.setBorder(PdfPCell.NO_BORDER);
		cell.setPhrase(new Phrase("  ", font));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setPadding(3);
		tableSix.addCell(cell);
		document.add(tableSix);
		
		PdfPTable tableSeven = new PdfPTable(4);
		tableSeven.setWidthPercentage(widthSize);
		tableSeven.setWidths(new float[] {3f,5f,20f, 20f});
		
		font.setColor(Color.black);
		cell = new PdfPCell();
		cell.setBorder(PdfPCell.NO_BORDER);
		cell.setPhrase(new Phrase(" ", font));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		tableSeven.addCell(cell);
		
		font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
		font.setSize(15);
		pwd = new File("").getAbsolutePath();
		image = Image.getInstance(pwd + "/companyLogo/sign.png");
		cell = new PdfPCell();
		cell.setImage(image);
		cell.setBorder(PdfPCell.NO_BORDER);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setPaddingTop(50);
		//cell.setPaddingTop(80);
		tableSeven.addCell(cell);
		
		
		font.setColor(Color.black);
		cell = new PdfPCell();
		cell.setBorder(PdfPCell.NO_BORDER);
		cell.setPhrase(new Phrase(" ", font));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		tableSeven.addCell(cell);

		cell = new PdfPCell();
		cell.setBorder(PdfPCell.NO_BORDER);
		cell.setPhrase(new Phrase("  ", font));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		tableSeven.addCell(cell);
		document.add(tableSeven);
		
		PdfPTable tableEight = new PdfPTable(3);
		tableEight.setWidthPercentage(widthSize);
		tableEight.setWidths(new float[] {10f,20f, 10f});
		
		font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
		font.setSize(15);
		cell = new PdfPCell();
		cell.setBorder(PdfPCell.NO_BORDER);
		cell.setPhrase(new Phrase(" Principle of LearnHub ", font));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setPadding(3);
		tableEight.addCell(cell);
		
		
		font.setColor(Color.black);
		cell = new PdfPCell();
		cell.setBorder(PdfPCell.NO_BORDER);
		cell.setPhrase(new Phrase(" ", font));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setPadding(5);
		tableEight.addCell(cell);

		cell = new PdfPCell();
		cell.setBorder(PdfPCell.NO_BORDER);
		cell.setPhrase(new Phrase("  ", font));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setPadding(3);
		tableEight.addCell(cell);
		document.add(tableEight);
		
		document.close();
	}
}
