package pl.kognitywistyka.io;

import java.io.FileNotFoundException;
import java.io.IOException;

import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.List;
import com.itextpdf.layout.element.ListItem;
import com.itextpdf.layout.element.Paragraph;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Created by pwilkin on 17.03.2022.
 */
public class PDFGenTest {

    @Test
    public void testPublishPDF() {
        try (PdfWriter writer = new PdfWriter("out.pdf")) {
            try (PdfDocument pdf = new PdfDocument(writer)) {
                try (Document document = new Document(pdf)) {
                    // Create a PdfFont
                    PdfFont font = PdfFontFactory.createFont(StandardFonts.TIMES_ROMAN);
                    // Add a Paragraph
                    document.add(new Paragraph("iText is:").setFont(font));
                    // Create a List
                    List list = new List()
                            .setSymbolIndent(12)
                            .setListSymbol("\u2022")
                            .setFont(font);
                    // Add ListItem objects
                    list.add(new ListItem("Never gonna give you up"))
                            .add(new ListItem("Never gonna let you down"))
                            .add(new ListItem("Never gonna run around and desert you"))
                            .add(new ListItem("Never gonna make you cry"))
                            .add(new ListItem("Never gonna say goodbye"))
                            .add(new ListItem("Never gonna tell a lie and hurt you"));
                    // Add the list
                    document.add(list);
                }
            }
        } catch (IOException e) {
            Assertions.fail(e);
        }
    }

}
