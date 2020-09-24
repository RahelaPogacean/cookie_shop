package businessLayer.generateReports;

import com.itextpdf.text.*;
import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.PdfWriter;
import org.apache.pdfbox.pdmodel.font.encoding.WinAnsiEncoding;
import presentationLayer.view.AdminTasks;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

public class ConcreteReportPdf extends WinAnsiEncoding implements Report {


    public ConcreteReportPdf() throws IOException, DocumentException {

        boughtCookiesEvidence();
    }


    public void boughtCookiesEvidence() throws FileNotFoundException, DocumentException {
        Document document = new Document();

        PdfWriter.getInstance(document, new FileOutputStream("pdfGood.pdf"));
        document.open();
        Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);

        Chunk chunk = new Chunk(" REPORT ", font);
        Chunk chunk2 = null;

        Date actualDate = new Date(System.currentTimeMillis());
        Date startDate = ConcreteGenerator.subtractDay(actualDate, AdminTasks.noOfDays);

        System.out.println("reports size =" + ConcreteGenerator.reports.size());
        for(int i = 0; i < ConcreteGenerator.reports.size(); i++){

            document.add(new Paragraph(" "));
            chunk2 = new Chunk(String.valueOf(ConcreteGenerator.reports.get(i)), font);
            document.add(chunk2);
            document.add(new Paragraph(" "));
            System.out.println(ConcreteGenerator.reports.get(i));
        }

        document.close();


        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFileChooser chooser = null;
                try {
                    chooser = new JFileChooser(String.valueOf(PdfWriter.getInstance(document, new FileOutputStream("pdf6.pdf"))));
                } catch (DocumentException e) {
                    e.printStackTrace();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                chooser.setFileSelectionMode( JFileChooser.DIRECTORIES_ONLY );
                chooser.showSaveDialog( null );
                System.out.println( chooser.getSelectedFile() );
            }
        } );


    }


}
