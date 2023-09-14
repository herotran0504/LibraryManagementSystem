package librarysystem.util;

import java.io.*;
import java.util.Properties;

public class IdManager {

    public static final String OUTPUT_DIR = System.getProperty("user.dir") + "\\storage\\id.properties";

    public static String getNextID(String key) {
        Properties prop = new Properties();
        InputStream input = null;
        String newValue = null;
        try {
            input = new FileInputStream(OUTPUT_DIR);

            // load a properties file
            prop.load(input);

            // get the property value and print it out
            newValue = String.valueOf((Integer.valueOf(prop.getProperty(key)) + 1));
            updateID("librarymember_id", (Integer.valueOf(prop.getProperty(key)) + 1));

        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return newValue;
    }

    public static void updateID(String key, int newValue) {
        Properties prop = new Properties();
        OutputStream output = null;

        try {

            output = new FileOutputStream(OUTPUT_DIR);

            // set the properties value
            prop.setProperty(key, String.valueOf(newValue));

            // save properties to project root folder
            prop.store(output, null);

        } catch (IOException io) {
            io.printStackTrace();
        } finally {
            if (output != null) {
                try {
                    output.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
        System.out.println("New::" + prop.getProperty("librarymember_id"));

    }
}
