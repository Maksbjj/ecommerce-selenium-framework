package com.ecommerce.qa.util;

import com.opencsv.CSVReader;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.ecommerce.qa.base.FrameworkInitialize.envConfig;
import static com.ecommerce.qa.util.TestDataProvider.pageTitles;

public class CsvUtil {

    public static void readPageTitles() {
        pageTitles = new HashMap<>();
        List<String[]> strings;
        try (CSVReader reader = new CSVReader(
                new FileReader(envConfig.getPageTitlesPath()))) {

            strings = reader.readAll();
            for (String[] string : strings) {
                pageTitles.put(string[0], string[1]);
            }
        } catch (IOException e) {
            Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
            logger.log(Level.INFO, "Impossible to read the file");
        }
    }

    public static String getPageTitle(String pageName) {
        List<String> strings = pageTitles
                .entrySet()
                .stream()
                .filter(x -> x.getKey().equals(pageName))
                .map(Map.Entry::getValue).toList();
        return strings.get(0);
    }
}
