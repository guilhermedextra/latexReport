package br.com.dextra.latexReport;

import java.io.FileOutputStream;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

public class IText {
	public IText() throws Exception{
		Document document = new Document();
		PdfWriter.getInstance(document,
			new FileOutputStream("HelloWorld.pdf"));
		document.open();
		document.add(new Paragraph("Hello World"));
		document.close();
	}

	public static void main(String args[]){
		try{
			new IText();
		}catch(Exception e){
			System.out.println(e);
		}
	}
}
