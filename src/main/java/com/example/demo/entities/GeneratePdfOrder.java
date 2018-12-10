package com.example.demo.entities;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GeneratePdfOrder {
	 public static ByteArrayInputStream orderReport(List<Product> list) {

	        Document document = new Document();
	        ByteArrayOutputStream out = new ByteArrayOutputStream();

	        try {

	            PdfPTable table = new PdfPTable(6);
	            table.setWidthPercentage(60);
	            table.setWidths(new int[]{1, 4, 4, 4, 4, 4});
	           

	            Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);

	            PdfPCell hcell;
	            hcell = new PdfPCell(new Phrase("Id", headFont));
	            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
	            table.addCell(hcell);

	            hcell = new PdfPCell(new Phrase("Product Name", headFont));
	            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
	            table.addCell(hcell);

	            hcell = new PdfPCell(new Phrase("Original Price", headFont));
	            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
	            table.addCell(hcell);
	            
	            hcell = new PdfPCell(new Phrase("New Price", headFont));
	            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
	            table.addCell(hcell);
	            
	            hcell = new PdfPCell(new Phrase("Save", headFont));
	            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
	            table.addCell(hcell);
	            
	            hcell = new PdfPCell(new Phrase("Quantity", headFont));
	            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
	            table.addCell(hcell);
	            
	           
	            
	            double scale = Math.pow(10, 2);
	            for (Product product : list) {

	                PdfPCell cell;
	                

	                cell = new PdfPCell(new Phrase(String.valueOf(product.getProductId())));
	                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
	                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	                table.addCell(cell);

	                cell = new PdfPCell(new Phrase(product.getProductName()));
	                cell.setPaddingLeft(5);
	                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
	                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
	                table.addCell(cell);

	                cell = new PdfPCell(new Phrase("R"+String.valueOf(Math.round(product.getOriginPrice()*scale)/scale)));
	                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
	                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
	                cell.setPaddingRight(5);
	                table.addCell(cell);
	                
	                cell = new PdfPCell(new Phrase("R"+String.valueOf(Math.round(product.getNewPrice()*scale)/scale)));
	                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
	                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
	                cell.setPaddingRight(5);
	                table.addCell(cell);
	                  
	              
	                
	                cell = new PdfPCell(new Phrase("R"+String.valueOf(Math.round(product.getSave()*scale)/scale)));
	                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
	                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
	                cell.setPaddingRight(5);
	                table.addCell(cell);
	                
	                cell = new PdfPCell(new Phrase(String.valueOf(product.getQuantity())));
	                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
	                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
	                cell.setPaddingRight(5);
	                table.addCell(cell);
	                
	                
	            }
	           

	            PdfWriter.getInstance(document, out);
	            document.open();
	            document.add(table);
	            
	            document.close();
	            
	        } catch (DocumentException ex) {
	        
	            Logger.getLogger(GeneratePdfOrder.class.getName()).log(Level.SEVERE, null, ex);
	        }

	        return new ByteArrayInputStream(out.toByteArray());
	    }
}
