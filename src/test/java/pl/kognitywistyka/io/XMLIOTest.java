
package pl.kognitywistyka.io;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 * Created by pwilkin on 03.03.2022.
 */
public class XMLIOTest {

    @BeforeAll
    public static void testWriteSimpleXML() {
        File file = new File("test.xml");
        try (FileOutputStream fos = new FileOutputStream(file)) {
            try (PrintWriter pw = new PrintWriter(fos)) {
                pw.println("<start>");
                pw.print("\t<bool>");
                pw.print(true);
                pw.print("</bool>\n\t<bool>");
                pw.print(false);
                pw.println("</bool>");
                pw.println("</start>");
            }
        } catch (IOException e) {
            Assertions.fail(e);
        }
    }

    @Test
    public void testReadSimpleData() {
        File file = new File("test.xml");
        try (FileInputStream fis = new FileInputStream(file)) {
            try (BufferedReader br = new BufferedReader(new InputStreamReader(fis))) {
                String line = br.readLine();
                Matcher matcher = Pattern.compile("<([a-zA-Z]+)>").matcher(line);
                Assertions.assertTrue(matcher.matches());
                String grp1 = matcher.group(1);
                Assertions.assertEquals("start", grp1);
            }
        } catch (IOException e) {
            Assertions.fail(e);
        }
    }

}
