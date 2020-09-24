package businessLayer.generateReports;

import com.itextpdf.text.DocumentException;
import java.io.IOException;

public abstract class Generator {

        abstract Report factoryMethod(String type) throws IOException, DocumentException;
}
