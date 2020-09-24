package businessLayer.generateReports;


import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Date;

public class ConcreteReportTxt implements Report {


    public ConcreteReportTxt() throws FileNotFoundException, UnsupportedEncodingException {

        boughtCookiesEvidenceTxt();
    }

    public  void boughtCookiesEvidenceTxt() throws FileNotFoundException, UnsupportedEncodingException {

        PrintWriter writer = new PrintWriter("report.txt", String.valueOf(StandardCharsets.UTF_8));

        Date date = new Date(System.currentTimeMillis());

        for(int i=0; i<ConcreteGenerator.reports.size(); i++){//ar fi validare nr zile

            writer.println(ConcreteGenerator.reports.get(i));
            System.out.println(ConcreteGenerator.reports.get(i));

        }
        writer.println("\n");
        writer.close();
    }

}

