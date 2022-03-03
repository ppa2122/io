package pl.kognitywistyka.io;

import java.io.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 * Created by pwilkin on 03.03.2022.
 */
public class SimpleIOTest {

    @BeforeAll
    public static void testWriteSimpleData() {
        File file = new File("test.txt");
        try (FileOutputStream fos = new FileOutputStream(file)) {
            try (PrintWriter pw = new PrintWriter(fos)) {
                pw.println("Hello world!");
                pw.println(125);
                pw.print(true);
                pw.print(" ");
                pw.print(false);
                pw.println();
            }
        } catch (IOException e) {
            Assertions.fail(e);
        }
    }

    @Test
    public void testReadSimpleData() {
        File file = new File("test.txt");
        try (FileInputStream fis = new FileInputStream(file)) {
            try (BufferedReader br = new BufferedReader(new InputStreamReader(fis))) {
                String line = br.readLine();
                line = br.readLine();
                line = br.readLine();
                Assertions.assertEquals("true false", line);
            }
        } catch (IOException e) {
            Assertions.fail(e);
        }
    }

}
