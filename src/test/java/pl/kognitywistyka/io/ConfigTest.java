package pl.kognitywistyka.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Created by pwilkin on 10.03.2022.
 */
public class ConfigTest {

    @Test
    public void testSaveAndLoadConfig() {
        File file = FileUtils.getUserDirectory();
        Path path = file.toPath();
        Path configDir = path.resolve(".iotest");
        if (!Files.exists(configDir)) {
            try {
                Files.createDirectory(configDir);
            } catch (IOException e) {
                Assertions.fail(e);
            }
        }
        Path configFile = configDir.resolve("config.ini");
        long timeSaved = System.currentTimeMillis();
        try (BufferedWriter bw = Files.newBufferedWriter(configFile)) {
            Properties pr = new Properties();
            pr.setProperty("LastEdit", String.valueOf(timeSaved));
            pr.store(bw, "Config saved on " + new Date());
        } catch (IOException e) {
            Assertions.fail(e);
        }
        try (BufferedReader br = Files.newBufferedReader(configFile)) {
            Properties pr = new Properties();
            pr.load(br);
            long timeLoaded = Long.parseLong(pr.getProperty("LastEdit"));
            Assertions.assertEquals(timeSaved, timeLoaded);
        } catch (IOException e) {
            Assertions.fail(e);
        }
    }

}
